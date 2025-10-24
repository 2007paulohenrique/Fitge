import { useCallback, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { useTranslation } from 'react-i18next'
import { formatResponseMessageForTranslation } from '../utils/formatters/responseMessages'
import { postRefreshToken } from '../utils/consts/apiEndPoints'
import api from '../api/axios'
import type { AxiosError, AxiosResponse } from 'axios'
import { useSystemMessage } from '../app/contexts/systemMessageContext/useSystemMessage'

export type ApiResponseData = {
    result?: Record<string, any> | any[]
    messageCode?: string
}

export type RequestFunction = () => Promise<AxiosResponse>
export type SuccessHandler = (data: ApiResponseData) => void
export type ErrorHandler = (error: AxiosError) => void

export type RequestInUseRequest = (
    requestFunction: RequestFunction,
    onSuccess?: SuccessHandler,
    onError?: ErrorHandler,
    loadingMessage?: string,
    showSuccessMessage?: boolean
) => Promise<boolean>

export default function useRequest() {
    const { t } = useTranslation()

    const navigate = useNavigate()
    
    const location = useLocation()

    const { notify } = useSystemMessage()

    const [loading, setLoading] = useState<boolean>(false)

    const refreshToken = useCallback(async (requestId: string): Promise<boolean> => {
        try {
            await api.post(postRefreshToken)

            return true

        } catch (err) {
            const error = err as AxiosError
            const responseData = (error.response?.data ?? {}) as ApiResponseData

            console.error(error)

            navigate('/login')

            const messageCode = responseData.messageCode ?? 'UNKNOWN_ERROR'
            const message = formatResponseMessageForTranslation(messageCode, 'error')
            
            notify(t(message), 'error', requestId)

            return false

        }
    }, [
        navigate, 
        notify,
        t
    ])

    const request: RequestInUseRequest = useCallback(async (
        requestFunction, 
        onSuccess, 
        onError, 
        loadingMessage, 
        showSuccessMessage = true
    ) => {
        if (loading) return false

        if (!navigator.onLine) {
            navigate('/error', { 
                state: {
                    messageCode: 'CONNECTION_ERROR'
                }
            })

            return false
        }

        const requestId = String(Math.random())
        
        setLoading(true)

        if (loadingMessage) notify(loadingMessage, 'loading', requestId)

        try {
            const response = await requestFunction()

            onSuccess?.(response.data)

            const message = formatResponseMessageForTranslation(response.data.messageCode, 'success')

            if (showSuccessMessage) notify(t(message), 'success', requestId)

            return true

        } catch (err) {
            const error = err as AxiosError

            console.error(error)

            if (error.code === 'ECONNABORTED') {
                const message = formatResponseMessageForTranslation('TIMEOUT_ERROR', 'error')

                onError?.(error)

                notify(t(message), 'error', requestId)

                return false
            }

            const response = error.response

            if (!response) {
                navigate('/error', { 
                    state: { 
                        messageCode: 'NO_RESPONSE_ERROR' 
                    }
                })
                
                return false
            } 
            
            const responseStatus = response.status
            const responseData = (response?.data ?? {}) as ApiResponseData

            const messageCode = responseData?.messageCode ?? 'UNKNOWN_ERROR'
            const message = formatResponseMessageForTranslation(messageCode, 'error')

            const errorConfig = (error.config ?? {}) as any

            if (responseStatus === 403) {
                if (location.pathname !== '/') navigate('/')
                
                notify(t(message), 'error', requestId)
                
                return false
            }

            if (responseStatus === 401 && messageCode === 'INVALID_TOKEN_ERROR' && !errorConfig._retry) {
                errorConfig._retry = true

                const refreshed = await refreshToken(requestId)

                if (refreshed) {
                    return await request(
                        requestFunction,
                        onSuccess,
                        onError,
                        loadingMessage,
                        showSuccessMessage
                    )
                }
            }
            
            onError?.(error)
            
            notify(t(message), 'error', requestId)

            return false

        } finally {            
            setLoading(false)

        }
    }, [
        loading, 
        navigate, 
        notify, 
        location.pathname, 
        refreshToken, 
        t
    ])

    return { request, loading }
}