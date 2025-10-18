import React, { useEffect, useRef } from 'react'
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
    children?: React.ReactNode
}

const BasePage: React.FC<BasePageProps> = ({
    title,
    isUserRequired = true,
    userTypeRequired,
    fetchInitialData,
    children
}) => {
    const hasRun = useRef<boolean>(false)

    const navigate = useNavigate()

    const user = useAppSelector(state => state.user)

    const { request: getUserTypeRequest } = useRequest()
    
    const { notify } = useSystemMessage()
    const { t } = useTranslation()

    useEffect(() => {
        if (hasRun.current) return

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

            hasRun.current = true
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
        <main>
            {hasRun.current ? (
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