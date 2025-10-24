import { useTranslation } from 'react-i18next'
import BaseForm from '../../../base/BaseForm'
import FlexContainer from '../../../containers/FlexContainer'
import Title from '../../../ui/text/Title'
import PrimaryButton from '../../../ui/buttons/PrimaryButton'
import TextButton from '../../../ui/buttons/TextButton'
import { setObjectDataInForm } from '../../../../utils/reactState.ts/setData'
import type { LoginUser } from '../types'
import PasswordInput from '../../../ui/formFields/common/PasswordInput'
import EmailInput from '../../../ui/formFields/specific/EmailInput'
import { useNavigate } from 'react-router-dom'
import { signUpRote } from '../../../../utils/consts/rotes'
import FitgeIcon from '../../../../assets/fitgeIdentity/icon.svg?react'
import IconIllustration from '../../../ui/illustrations/IconIllustration'

type LoginFormProps = {
    onLoginSubmit: () => void
    loginError: boolean
    setLoginError: React.Dispatch<React.SetStateAction<boolean>>
    user: LoginUser
    setUser: React.Dispatch<React.SetStateAction<LoginUser>>
}

const LoginForm: React.FC<LoginFormProps> = ({
    onLoginSubmit,
    loginError,
    setLoginError,
    user,
    setUser
}) => {
    const { t } = useTranslation()

    const navigate = useNavigate()

    return (
        <FlexContainer
            gap='5em'
        >
            <FlexContainer
                gap={0}
            >
                <IconIllustration
                    icon={FitgeIcon}
                />

                <Title
                    text={t('pages.login')}
                />
            </FlexContainer>

            <BaseForm
                onSubmit={onLoginSubmit}
                hasError={loginError}
            >
                <FlexContainer
                    gap='3em'
                >
                    <FlexContainer
                        gap='0.5em'
                    >
                        <EmailInput
                            value={user.email}
                            onChange={(e) => setObjectDataInForm<LoginUser>(
                                e,
                                setUser,
                                true,
                                undefined,
                                setLoginError
                            )}
                        />
                        
                        <PasswordInput
                            name='password'
                            placeholder={t('forms.fields.password.placeholder')}
                            value={user.password}
                            onChange={(e) => setObjectDataInForm<LoginUser>(
                                e,
                                setUser,
                                true,
                                undefined,
                                setLoginError
    
                            )}
                            label={t('forms.fields.password.label')}
                            hasError={loginError}
                            alertMessage={t('responses.error.LOGIN_ERROR')}
                        />
                    </FlexContainer>

                    <FlexContainer
                        gap='1.5em'
                    >
                        <PrimaryButton
                            text={t('pages.login')}
                        />

                        <FlexContainer
                            flexDirection='row'
                        >
                            <hr/>

                            {t('general.or')}
                            
                            <hr/>
                        </FlexContainer>

                        <TextButton
                            text={t('pages.signUp')}
                            onClick={() => navigate(signUpRote)}
                        />
                    </FlexContainer>
                </FlexContainer>
            </BaseForm>
        </FlexContainer>
    )
}

export default LoginForm