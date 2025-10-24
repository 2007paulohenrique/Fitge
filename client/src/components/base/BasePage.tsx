import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import PrimaryLoader from '../layout/loaders/PrimaryLoader'
import { useAppSelector } from '../../hooks/useAppSelector'
import { getUserType } from '../../utils/requests/userRequests'
import useRequest from '../../hooks/useRequest'
import { useTranslation } from 'react-i18next'
import { useSystemMessage } from '../../app/contexts/systemMessageContext/useSystemMessage'

type BasePageProps = {
    title: string
    isUserRequired?: boolean
    userTypeRequired: 'client' | 'trainer' | 'none'
    fetchInitialData?: () => void
    padding?: string | number
    centralize?: boolean
    children?: React.ReactNode
}

const BasePage: React.FC<BasePageProps> = ({
    title,
    isUserRequired = true,
    userTypeRequired,
    fetchInitialData,
    padding = '2em',
    centralize = false,
    children
}) => {
    const navigate = useNavigate()

    const user = useAppSelector(state => state.user)

    const { request: getUserTypeRequest } = useRequest()
    
    const { notify } = useSystemMessage()
    const { t } = useTranslation()

    const [hasRun, setHasRun] = useState<boolean>(false)

    const centralizeStyle = 
        centralize 
        ? {
            justifyContent: 'center',
            alignItems: 'center'
        } 
        : {}

    useEffect(() => {
        if (hasRun) return

        if (isUserRequired && !user.id) return

        const init = async () => {
            if (userTypeRequired !== 'none') {
                const userType = await getUserType(getUserTypeRequest)

                if (userTypeRequired !== userType) {
                    navigate('/')
                    
                    notify(t('responses.error.PAGE_ACCESS_ERROR'), 'error')

                    return
                }
            }

            fetchInitialData?.()

            setHasRun(true)
        }

        void init()
    }, [
        isUserRequired,
        user.id,
        userTypeRequired,
        fetchInitialData,
        getUserTypeRequest,
        navigate,
        notify,
        t
    ])

    useEffect(() => {
        document.title = title
    }, [
        title
    ])

    return (
        <main
            style={{
                display: 'flex',
                padding,
                ...centralizeStyle
            }}
        >
            {hasRun ? (
                children
            ) : (
                <PrimaryLoader
                    loading
                />
            )}
        </main>
    )
}

export default BasePage