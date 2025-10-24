import type React from 'react'
import FlexContainer from '../../../containers/FlexContainer'
import IconIllustration from '../../illustrations/IconIllustration'
import Description from '../../text/Description'
import { cssVarColors, cssVarTextSizes } from '../../../../utils/consts/cssVariables'
import { formatVariableName } from '../../../../utils/formatters/style/cssVariables'
import styles from './BaseInput.module.css'

export type BaseInputProps = {
    type?: React.HTMLInputTypeAttribute
    name: string
    placeholder: string
    label?: string
    value: string | number
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void
    icon?: React.FC<React.SVGProps<SVGSVGElement>>
    alertMessage?: string
    hasError?: boolean
    maxLength?: number 
    isRequired?: boolean
}

const BaseInput: React.FC<BaseInputProps> = ({
    type = 'text',
    name,
    placeholder,
    label,
    value = '',
    onChange,
    icon,
    alertMessage,
    hasError = false,
    maxLength,
    isRequired = false
}) => {
    return (
        <FlexContainer
            gap='0.5em'
        >
            {(label || icon) && (
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
                    {icon && (
                        <IconIllustration 
                            icon={icon} 
                            size='tiny'
                            varColor={cssVarColors.textColor}
                        />
                    )}
                    
                    {label && (
                        <Description
                            text={
                                <>
                                    {label}

                                    {isRequired && (
                                        <span
                                            style={{
                                                color: formatVariableName(cssVarColors.themeColor),
                                            }}
                                        >
                                            *
                                        </span>
                                    )}
                                </>
                            }
                            textAlign='left'
                            varSize={cssVarTextSizes.largeTextSize}
                            isBold
                        />
                    )}
                </FlexContainer>
            )}

            <FlexContainer
                className={styles.base_input_input_container}
                alignItems='start'
                gap='0.2em'
            >
                <input 
                    type={type}
                    id={name}
                    name={name}
                    placeholder={placeholder}
                    value={value}
                    onChange={(e) => {
                        e.target.value = e.target.value.replace(/\s{2,}/g, ' ')
                        onChange(e)
                    }}
                    maxLength={maxLength}
                    readOnly={!onChange}
                    required={isRequired}
                    className={(!!value && hasError) ? styles.error : ''}
                />

                {alertMessage && (
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
                )}
            </FlexContainer>
        </FlexContainer>
    )
}

export default BaseInput
