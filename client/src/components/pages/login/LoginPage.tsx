import { useTranslation } from 'react-i18next'
import BasePage from '../../base/BasePage'
import FlexContainer from '../../containers/FlexContainer'
import { useCallback, useState } from 'react'
import { loginUserInitialState, type LoginUser } from './types'
import LoginForm from './forms/LoginForm'
import BaseCard from '../../ui/cards/BaseCard'
import { cssVarColors } from '../../../utils/consts/cssVariables'
import TextButton from '../../ui/buttons/TextButton'
import { identityConfirmationRoute, recoverPasswordRoute } from '../../../utils/consts/routes'
import Description from '../../ui/text/Description'
import useWindowSize from '../../../hooks/useWindowSize'
import SecondaryFooter from '../../layout/footers/SecondaryFooter'
import type { ApiResponseData } from '../../../hooks/useRequest'
import { setSessionStorageItem } from '../../../utils/cache/sessionStorage'
import useRequest from '../../../hooks/useRequest'
import useFormData from '../../../hooks/useFormData'
import { useConfirmIdentityCallback } from '../../../app/contexts/confirmIdentityCallbackContext/useConfirmIdentityCallback'
import { loginSecureIdKey } from '../../../utils/consts/cacheKeys'
import useStatedNavigate from '../../../hooks/useStatedNavigate'
import api from '../../../api/axios'
import { userAuth } from '../../../utils/requests/userRequests'
import { authEndPoint, loginEndPoint } from '../../../utils/consts/apiEndPoints'
import { useAppDispatch } from '../../../hooks/useAppDispatch'
import { useNavigate } from 'react-router-dom'

const LoginPage = () => {
    const { t } = useTranslation()

    const statedNavigate = useStatedNavigate()
    const navigate = useNavigate()

    const { request: loginRequest } = useRequest()
    const { request: authRequest } = useRequest()

    const dispatch = useAppDispatch()
    
    const { formData: loginFormData, appendFormDataValue: appendLoginFormDataValue } = useFormData(loginEndPoint)
    const { formData: authFormData, appendFormDataValue: appendAuthFormDataValue } = useFormData(authEndPoint)

    const { setOnConfirmIdentity } = useConfirmIdentityCallback()

    const { isMobile, isSmallMobile } = useWindowSize()

    const [loginUser, setLoginUser] = useState<LoginUser>(loginUserInitialState)
    const [loginError, setLoginError] = useState<boolean>(false)

    const onLoginSubmit = useCallback(() => {
        const onLoginSuccess = (data: ApiResponseData) => {
            const secureId = data.result?.secureId

            setSessionStorageItem(loginSecureIdKey, secureId)

            setOnConfirmIdentity(() => {
                appendAuthFormDataValue('secureId', secureId)

                userAuth(authRequest, dispatch, navigate, authFormData)
            })

            statedNavigate(identityConfirmationRoute, { 
                state: {
                    user: {
                        email: loginUser.email
                    }
                } 
            })
        }

        appendLoginFormDataValue('email', loginUser.email)
        appendLoginFormDataValue('password', loginUser.password)

        loginRequest(
            () => api.post(loginEndPoint, loginFormData),
            onLoginSuccess,
            undefined,
            t('requests.login.loading'),
            false
        )
    }, [
        loginRequest,
        t,
        appendLoginFormDataValue,
        navigate,
        statedNavigate,
        appendAuthFormDataValue,
        authFormData,
        loginFormData,
        loginUser
    ])

    return (
        <BasePage
            title={t('pages.login')}
            userTypeRequired='none'
            padding='3em 1em'
            isUserRequired={false}
            centralize
        >
            <FlexContainer
                gap='5em'
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
                                onClick={() => navigate(recoverPasswordRoute)}
                                varColor={cssVarColors.alertColor}
                            />
                        </BaseCard>
                    </FlexContainer>
                </FlexContainer>

                <SecondaryFooter/>
            </FlexContainer>
        </BasePage>
    )
}

export default LoginPage