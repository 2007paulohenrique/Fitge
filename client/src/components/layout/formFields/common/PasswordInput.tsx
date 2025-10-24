import type React from 'react'
import FlexContainer from '../../../containers/FlexContainer'
import IconIllustration from '../../illustrations/IconIllustration'
import Description from '../../text/Description'
import { cssVarColors, cssVarTextSizes } from '../../../../utils/consts/cssVariables'
import { formatVariableName } from '../../../../utils/formatters/style/cssVariables'
import ClickableIcon from '../../buttons/ClickableIcon'
import { passwordMaxLength } from '../../../../utils/consts/dataLimits'
import type { BaseInputProps } from './BaseInput'
import PasswordIcon from '../../../../assets/icons/password.svg?react'
import VisiblePasswordIcon from '../../../../assets/icons/visible-password.svg?react'
import HiddenPasswordIcon from '../../../../assets/icons/hidden-password.svg?react'
import styles from './BaseInput.module.css'
import { useState } from 'react'
import PasswordInputValidators from './PasswordInputValidators'

type PasswordInputProps = Omit<BaseInputProps, 'type' | 'icon' | 'maxLength' | 'isRequired'> & {
    value: string
}

const PasswordInput: React.FC<PasswordInputProps> = ({
    name,
    placeholder,
    label,
    value = '',
    onChange,
    alertMessage,
    hasError
}) => {
    const [isVisible, setIsVisible] = useState<boolean>(false)

    return (
        <FlexContainer
            gap='0.5em'
        >
            <FlexContainer 
                tag='label'
                flexDirection='row'
                justifyContent='start'
                htmlFor={name}
                gap='0.5em'
                style={{
                    padding: '0 1em'
                }}
            >
                <IconIllustration 
                    icon={PasswordIcon} 
                    size='tiny'
                    varColor={cssVarColors.textColor}
                />
                
                <Description
                    text={
                        <>
                            {label}

                            <span
                                style={{
                                    color: formatVariableName(cssVarColors.themeColor),
                                }}
                            >
                                *
                            </span>
                        </>
                    }
                    textAlign='left'
                    varSize={cssVarTextSizes.largeTextSize}
                    isBold
                />
            </FlexContainer>

            <FlexContainer
                className={styles.base_input_input_container}
                alignItems='start'
                gap='0.2em'
            >
                <div
                    className={styles.password_input_container}
                >
                    <input 
                        type={isVisible ? 'text' : 'password'}
                        id={name}
                        name={name}
                        placeholder={placeholder}
                        value={value}
                        onChange={(e) => {
                            e.target.value = e.target.value.replace(/\s/g, '')
                            onChange(e)
                        }}
                        maxLength={passwordMaxLength}
                        readOnly={!onChange}
                        required
                    />

                    <ClickableIcon
                        icon={isVisible ? VisiblePasswordIcon : HiddenPasswordIcon}
                        onClick={() => setIsVisible(prev => !prev)}
                        size='small'
                        hasTransition={false}
                    />
                </div>

                {alertMessage ? (
                    <Description 
                        text={alertMessage}
                        textAlign='left'
                        varSize={cssVarTextSizes.smallTextSize}
                        varColor={cssVarColors.alertColor}
                        isVisible={!!value && hasError}   
                        style={{
                            padding: '0 1em'
                        }}                
                    />
                ) : (
                    <PasswordInputValidators
                        password={value}
                    />
                )}
            </FlexContainer>
        </FlexContainer>
    )
}

export default PasswordInput
