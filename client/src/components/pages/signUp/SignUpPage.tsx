import { Trans, useTranslation } from 'react-i18next'
import BasePage from '../../base/BasePage'
import FlexContainer from '../../containers/FlexContainer'
import { useCallback, useState } from 'react'
import { signUpErrorsInitialState, signUpUserInitialState, type SignUpErrors, type SignUpUser } from './types'
import SignUpForm from './forms/SignUpForm'
import BaseCard from '../../ui/cards/BaseCard'
import IconIllustration from '../../ui/illustrations/IconIllustration'
import FitgeIcon from '../../../assets/fitgeIdentity/icon.svg?react'
import FitgeName from '../../../assets/fitgeIdentity/name.svg?react'
import CardsIllustration from '../../../assets/illustrations/cards.webp'
import Description from '../../ui/text/Description'
import useWindowSize from '../../../hooks/useWindowSize'
import TextButton from '../../ui/buttons/TextButton'
import Link from '../../ui/text/Link'
import { identityConfirmationRoute, introductionRoute, loginRoute, setupSettingsRoute } from '../../../utils/consts/routes'
import { cssVarTextSizes } from '../../../utils/consts/cssVariables'
import SecondaryFooter from '../../layout/footers/SecondaryFooter'
import useRequest, { type ApiResponseData } from '../../../hooks/useRequest'
import api from '../../../api/axios'
import useStatedNavigate from '../../../hooks/useStatedNavigate'
import { useNavigate } from 'react-router-dom'
import { setSessionStorageItem } from '../../../utils/cache/sessionStorage'
import { createAccounSecureIdKey } from '../../../utils/consts/cacheKeys'
import useFormData from '../../../hooks/useFormData'
import { useConfirmIdentityCallback } from '../../../app/contexts/confirmIdentityCallbackContext/useConfirmIdentityCallback'
import { SIGN_UP_ENDPOINT } from '../../../utils/consts/apiEndPoints'

const SignUpPage = () => {
    const { t } = useTranslation()

    const statedNavigate = useStatedNavigate()
    const navigate = useNavigate()

    const { request } = useRequest()

    const { formData, appendFormDataValue } = useFormData(SIGN_UP_ENDPOINT)

    const { setOnConfirmIdentity } = useConfirmIdentityCallback()

    const { isMobile, isSmallMobile, isTablet } = useWindowSize()

    const [signUpFormError, setSignUpFormError] = useState<boolean>(false)
    const [signUpErrors, setSignUpErrors] = useState<SignUpErrors>(signUpErrorsInitialState)
    const [signUpUser, setSignUpUser] = useState<SignUpUser>(signUpUserInitialState)
    const [acceptTerms, setAcceptTerms] = useState<boolean>(false)

    const onSignUpSubmit = useCallback(() => {
        const onSignUpSuccess = (data: ApiResponseData) => {
            setSessionStorageItem(createAccounSecureIdKey, data.result?.secureId)

            setOnConfirmIdentity(() => navigate(setupSettingsRoute))

            statedNavigate(identityConfirmationRoute, { 
                state: {
                    user: {
                        email: signUpUser.email
                    }
                } 
            })
        }

        appendFormDataValue('name', signUpUser.name)
        appendFormDataValue('nickname', signUpUser.nickname)
        appendFormDataValue('email', signUpUser.email)
        appendFormDataValue('password', signUpUser.password)

        request(
            () => api.post(SIGN_UP_ENDPOINT, formData),
            onSignUpSuccess,
            undefined,
            t('requests.signUp.loading'),
            false
        )
    }, [
        request,
        t,
        appendFormDataValue,
        navigate,
        statedNavigate,
        signUpUser
    ])

    return (
        <BasePage
            title={t('pages.signUp')}
            userTypeRequired='none'
            padding='3em 1em'
            isUserRequired={false}
            centralize
        >
            <FlexContainer
                gap='5em'
            >
                <FlexContainer
                    flexDirection='row'
                >
                    <FlexContainer
                        width={isMobile ? '50%' : ''}
                        minWidth={isMobile ? (isSmallMobile ? '90%' : '80%') : '50%'}
                    >
                        <BaseCard
                            padding='1em'
                        >
                            <SignUpForm
                                onSignUpSubmit={onSignUpSubmit}
                                signUpError={signUpFormError}
                                setSignUpError={setSignUpFormError}
                                signUpDataErrors={signUpErrors}
                                setSignUpDataErrors={setSignUpErrors}
                                user={signUpUser}
                                setUser={setSignUpUser}
                                acceptTerms={acceptTerms}
                                setAcceptTerms={setAcceptTerms}
                            />
                        </BaseCard>

                        <BaseCard
                            padding='1em'
                            flexDirection='row'
                            gap='0.5em'
                            minWidth='max-content'
                        >
                            <Description
                                text={t('general.alreadyHaveAnAccount')}
                            />

                            <TextButton
                                text={t('pages.login')}
                                onClick={() => navigate(loginRoute)}
                            />
                        </BaseCard>
                    </FlexContainer>

                    {!isMobile && (
                        <FlexContainer
                            padding={isTablet ? '1em' : '1em 7%'}
                            gap={isTablet ? '5em' : '3em'}
                        >
                            <FlexContainer
                                gap='5em'
                            >
                                <FlexContainer>
                                    <FlexContainer
                                        width='50%'
                                    >
                                        <IconIllustration
                                            icon={FitgeIcon}
                                            size='large'
                                        />
                                        
                                        <IconIllustration
                                            icon={FitgeName}
                                            varColor='--text-color'
                                            size='fill'
                                        />
                                    </FlexContainer>

                                    <Description
                                        text={t('identity.slogan')}
                                    />
                                </FlexContainer>

                                <FlexContainer>
                                    <IconIllustration
                                        src={CardsIllustration}
                                        size='fill'
                                    />
                                </FlexContainer>
                            </FlexContainer>

                            <Description
                                text={
                                    <Trans
                                        i18nKey='identity.shortIntroductionToFitge'
                                        components={{ 
                                            introductionLink: <Link
                                                destiny={introductionRoute} 
                                                text={t('pages.introduction')} 
                                            />
                                        }}
                                    />
                                }
                                varSize={cssVarTextSizes.smallTextSize}
                            />
                        </FlexContainer>
                    )}
                </FlexContainer>

                <SecondaryFooter/>
            </FlexContainer>
        </BasePage>
    )
}

export default SignUpPage