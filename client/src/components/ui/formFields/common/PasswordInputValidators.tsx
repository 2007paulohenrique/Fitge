import type React from 'react'
import { useTranslation } from 'react-i18next'
import FlexContainer from '../../../containers/FlexContainer'
import { formatVariableName } from '../../../../utils/formatters/style/cssVariables'
import { cssVarColors, cssVarTextSizes } from '../../../../utils/consts/cssVariables'
import { hasSimpleLetter, hasNumber, hasSymbol } from '../../../../utils/constraints/regex'
import { validateUserPassword } from '../../../../utils/validators/user'
import { useMemo } from 'react'
import { passwordMinLength } from '../../../../utils/constraints/userConstraints'

type PasswordInputValidatorsProps = {
    password: string
}

type PasswordInputValidatorListElementProps = {
    hasValidated: boolean
    passwordValidationTranslationKey: string
}

const PasswordInputValidators: React.FC<PasswordInputValidatorsProps> = ({
    password
}) => {
    const { t } = useTranslation()

    const isPasswordValid = useMemo<boolean>(() => {
        return validateUserPassword(password)
    }, [
        password
    ])

    const PasswordInputValidatorListElement: React.FC<PasswordInputValidatorListElementProps> = ({
        hasValidated,
        passwordValidationTranslationKey
    }) => {
        return (
            <li
                style={{
                    color: formatVariableName(hasValidated ? cssVarColors.themeColor : cssVarColors.alertColor)
                }}
            >
                {t(`forms.fields.password.${passwordValidationTranslationKey}`)}
            </li>
        )
    }

    return (
        !isPasswordValid && (
            <FlexContainer
                tag='ul'
                gap='0.3em'
                alignItems='start'
                padding='0.5em 1em 0'
                style={{
                    fontSize: formatVariableName(cssVarTextSizes.smallTextSize)
                }}
            >
                <PasswordInputValidatorListElement
                    hasValidated={password.length >= passwordMinLength}
                    passwordValidationTranslationKey='lengthValidation'
                />
    
                <PasswordInputValidatorListElement
                    hasValidated={hasSimpleLetter.test(password)}
                    passwordValidationTranslationKey='letterValidation'
                />
    
                <PasswordInputValidatorListElement
                    hasValidated={hasNumber.test(password)}
                    passwordValidationTranslationKey='numberValidation'
                />
    
                <PasswordInputValidatorListElement
                    hasValidated={hasSymbol.test(password)}
                    passwordValidationTranslationKey='symbolValidation'
                />
            </FlexContainer>
        )
    )
}

export default PasswordInputValidators
