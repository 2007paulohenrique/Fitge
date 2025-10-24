import { Trans, useTranslation } from "react-i18next"
import BaseForm from "../../../base/BaseForm"
import FlexContainer from "../../../containers/FlexContainer"
import AcceptCheckBox from "../../../ui/formFields/common/AcceptCheckBox"
import Title from "../../../ui/text/Title"
import Link from "../../../ui/text/Link"
import PrimaryButton from "../../../ui/buttons/PrimaryButton"
import { setObjectDataInForm } from "../../../../utils/reactState.ts/setData"
import type { SignUpErrors, SignUpUser } from "../types"
import { policiesRote, termsAndConditionsRote } from "../../../../utils/consts/rotes"
import { validateUserEmail, validateUserName, validateUserNickname, validateUserPassword } from "../../../../utils/validators/user"
import PasswordInput from "../../../ui/formFields/common/PasswordInput"
import UserNameInput from "../../../ui/formFields/specific/UserNameInput"
import NicknameInput from "../../../ui/formFields/specific/NicknameInput"
import EmailInput from "../../../ui/formFields/specific/EmailInput"
import IconIllustration from "../../../ui/illustrations/IconIllustration"
import FitgeIcon from '../../../../assets/fitgeIdentity/icon.svg?react'

type SignUpFormProps = {
    onSignUpSubmit: () => void
    signUpError: boolean
    setSignUpError: React.Dispatch<React.SetStateAction<boolean>>
    signUpDataErrors: SignUpErrors
    setSignUpDataErrors: React.Dispatch<React.SetStateAction<SignUpErrors>>
    user: SignUpUser
    setUser: React.Dispatch<React.SetStateAction<SignUpUser>>
    acceptTerms: boolean
    setAcceptTerms: React.Dispatch<React.SetStateAction<boolean>>
}

const SignUpForm: React.FC<SignUpFormProps> = ({
    onSignUpSubmit,
    signUpError,
    setSignUpError,
    signUpDataErrors,
    setSignUpDataErrors,
    user,
    setUser,
    acceptTerms,
    setAcceptTerms
}) => {
    const { t } = useTranslation();

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
                    text={t('pages.signUp')}
                />
            </FlexContainer>

            <BaseForm
                onSubmit={onSignUpSubmit}
                hasError={signUpError}
                acceptTerms={acceptTerms}
            >
                <FlexContainer
                    gap='3em'
                >
                    <FlexContainer
                        gap='2em'
                    >
                        <FlexContainer
                            gap='0.5em'
                        >
                            <UserNameInput
                                name='name'
                                value={user.name}
                                onChange={(e) => setObjectDataInForm<SignUpUser, SignUpErrors>(
                                    e,
                                    setUser, 
                                    true,
                                    undefined, 
                                    setSignUpError, 
                                    setSignUpDataErrors, 
                                    validateUserName
                                )}
                                hasError={signUpDataErrors.name}
                            />
                            
                            <NicknameInput
                                value={user.nickname}
                                onChange={(e) => setObjectDataInForm<SignUpUser, SignUpErrors>(
                                    e,
                                    setUser, 
                                    false,
                                    undefined, 
                                    setSignUpError, 
                                    setSignUpDataErrors, 
                                    validateUserNickname 
                                )}
                                hasError={signUpDataErrors.nickname}
                            />
                            
                            <EmailInput
                                value={user.email}
                                onChange={(e) => setObjectDataInForm<SignUpUser, SignUpErrors>(
                                    e,
                                    setUser, 
                                    true,
                                    undefined, 
                                    setSignUpError, 
                                    setSignUpDataErrors, 
                                    validateUserEmail 
                                )}
                                hasError={signUpDataErrors.email}
                            />
                            
                            <PasswordInput
                                name='password'
                                placeholder={t('forms.fields.password.placeholder')}
                                value={user.password}
                                onChange={(e) => setObjectDataInForm<SignUpUser, SignUpErrors>(
                                    e,
                                    setUser, 
                                    true,
                                    undefined, 
                                    setSignUpError, 
                                    setSignUpDataErrors, 
                                    validateUserPassword 
                                )}
                                label={t('forms.fields.password.label')}
                                hasError={signUpDataErrors.password}
                            />
                        </FlexContainer>

                        <AcceptCheckBox
                            isAccepted={acceptTerms}
                            setIsAccepted={setAcceptTerms}
                            description={
                                <Trans
                                    i18nKey='general.acceptTermsOfFitge'
                                    components={{ 
                                        termsLink: <Link
                                            destiny={termsAndConditionsRote} 
                                            text={t('pages.termsAndConditions')} 
                                        />,
                                        policiesLink: <Link
                                            destiny={policiesRote} 
                                            text={t('pages.policies')} 
                                        /> 
                                    }}
                                />
                            }
                        />
                    </FlexContainer>

                    <PrimaryButton
                        text={t('general.checkData')}
                    />
                </FlexContainer>
            </BaseForm>
        </FlexContainer>
    )
}

export default SignUpForm