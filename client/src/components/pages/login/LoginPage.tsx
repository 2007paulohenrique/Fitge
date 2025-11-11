import { useTranslation } from 'react-i18next'
import BasePage from '../../base/BasePage'
import FlexContainer from '../../containers/FlexContainer'
import { useCallback, useState } from 'react'
import { loginUserInitialState, type LoginUser } from './types'
import LoginForm from './forms/LoginForm'
import BaseCard from '../../ui/cards/BaseCard'
import { cssVarColors } from '../../../utils/consts/cssVariables'
import TextButton from '../../ui/buttons/TextButton'
import { IDENTITY_CONFIRMATION_ROUTE, LOGIN_ROUTE, RECOVER_PASSWORD_ROUTE } from '../../../utils/consts/routes'
import Description from '../../ui/text/Description'
import useWindowSize from '../../../hooks/useWindowSize'
import useRequest from '../../../hooks/useRequest'
import useJsonBody from '../../../hooks/useJsonBody'
import { useConfirmIdentityCallback } from '../../../app/contexts/confirmIdentityCallbackContext/useConfirmIdentityCallback'
import useStatedNavigate from '../../../hooks/useStatedNavigate'
import api from '../../../api/axios'
import { userAuth } from '../../../utils/requests/userRequests'
import { LOGIN_ENDPOINT } from '../../../utils/consts/apiEndPoints'
import { useAppDispatch } from '../../../hooks/useAppDispatch'
import { useNavigate } from 'react-router-dom'

const LoginPage = () => {
    const { t } = useTranslation()

    const statedNavigate = useStatedNavigate()
    const navigate = useNavigate()

    const { request: loginRequest } = useRequest()
    const { request: authRequest } = useRequest()

    const dispatch = useAppDispatch()
    
    const { body, appendBodyValue } = useJsonBody(LOGIN_ENDPOINT)

    const { setOnConfirmIdentity } = useConfirmIdentityCallback()

    const { isMobile, isSmallMobile } = useWindowSize()

    const [loginUser, setLoginUser] = useState<LoginUser>(loginUserInitialState)
    const [loginError, setLoginError] = useState<boolean>(false)

    const onLoginSubmit = useCallback(() => {
        const onLoginSuccess = () => {
            setOnConfirmIdentity(() => {
                userAuth(authRequest, dispatch, navigate)
            })

            statedNavigate(IDENTITY_CONFIRMATION_ROUTE, { 
                state: {
                    user: {
                        email: loginUser.email
                    },
                    origin: LOGIN_ROUTE
                } 
            })
        }

        appendBodyValue('email', loginUser.email)
        appendBodyValue('password', loginUser.password)

        loginRequest(
            () => api.post(LOGIN_ENDPOINT, body),
            onLoginSuccess,
            undefined,
            t('requests.login.loading'),
            false
        )
    }, [
        loginRequest,
        t,
        appendBodyValue,
        navigate,
        statedNavigate,
        body,
        loginUser
    ])

    return (
        <BasePage
            title={t('pages.login')}
            userTypeRequired='none'
            padding='3em 1em'
            isUserRequired={false}
            centralize
            hasSecondaryFooter
        >
            <FlexContainer>
                <FlexContainer
                    width='50%'
                    minWidth={isMobile ? (isSmallMobile ? '90%' : '80%') : '50%'}
                >
                    <BaseCard
                        padding='1em'
                    >
                        <LoginForm
                            onLoginSubmit={onLoginSubmit}
                            loginError={loginError}
                            setLoginError={setLoginError}
                            user={loginUser}
                            setUser={setLoginUser}
                        />
                    </BaseCard>

                    <BaseCard
                        padding='1em'
                        flexDirection='row'
                        gap='0.5em'
                        minWidth='max-content'
                    >
                        <Description
                            text={t('general.forgotPassword')}
                        />

                        <TextButton
                            text={t('pages.recoverPassword')}
                            onClick={() => navigate(RECOVER_PASSWORD_ROUTE)}
                            varColor={cssVarColors.alertColor}
                        />
                    </BaseCard>
                </FlexContainer>
            </FlexContainer>
        </BasePage>
    )
}

export default LoginPage