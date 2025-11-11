import type React from 'react'
import { useTranslation } from 'react-i18next'
import FlexContainer from '../../../containers/FlexContainer'
import { formatVariableName } from '../../../../utils/formatters/style/cssVariables'
import { cssVarColors, cssVarTextSizes } from '../../../../utils/consts/cssVariables'
import { HAS_SIMPLE_LETTER, HAS_NUMBER, HAS_SYMBOL } from '../../../../utils/constraints/regex'
import { validateUserPassword } from '../../../../utils/validators/user'
import { useMemo } from 'react'
import { PASSWORD_MIN_LENGTH } from '../../../../utils/constraints/userConstraints'

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
                    hasValidated={password.length >= PASSWORD_MIN_LENGTH}
                    passwordValidationTranslationKey='lengthValidation'
                />
    
                <PasswordInputValidatorListElement
                    hasValidated={new RegExp(HAS_SIMPLE_LETTER).test(password)}
                    passwordValidationTranslationKey='letterValidation'
                />
    
                <PasswordInputValidatorListElement
                    hasValidated={new RegExp(HAS_NUMBER).test(password)}
                    passwordValidationTranslationKey='numberValidation'
                />
    
                <PasswordInputValidatorListElement
                    hasValidated={new RegExp(HAS_SYMBOL).test(password)}
                    passwordValidationTranslationKey='symbolValidation'
                />
            </FlexContainer>
        )
    )
}

export default PasswordInputValidators
