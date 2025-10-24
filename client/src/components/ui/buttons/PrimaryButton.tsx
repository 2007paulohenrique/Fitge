import React from 'react'
import styles from './PrimaryButton.module.css'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'
import { cssVarColors, type CssVarColors } from '../../../utils/consts/cssVariables'

type PrimaryButtonProps = {
    text: string
    initialVarBackgroundColor?: CssVarColors
    transitionVarBackgroundColor?: CssVarColors
    initialVarColor?: CssVarColors
    transitionVarColor?: CssVarColors
    onClick?: () => void
    isSubmit?: boolean
}

const PrimaryButton: React.FC<PrimaryButtonProps> = ({ 
    text, 
    initialVarBackgroundColor = cssVarColors.themeColor,
    transitionVarBackgroundColor = cssVarColors.whiteColor,
    initialVarColor = cssVarColors.whiteColor,
    transitionVarColor = cssVarColors.blackColor,
    onClick,
    isSubmit = true
}) => {
    const backgroundColor = formatVariableName(initialVarBackgroundColor)
    const transitionBackgroundColor = formatVariableName(transitionVarBackgroundColor)
    const color = formatVariableName(initialVarColor)
    const transitionColor = formatVariableName(transitionVarColor)

    return (
        <button
            type={isSubmit ? 'submit' : 'button'}
            onClick={onClick}
            className={styles.primary_button}
            style={{
                ['--button-bg-color' as any]: backgroundColor,
                ['--hover-button-bg-color' as any]: transitionBackgroundColor,
                ['--button-text-color' as any]: color,
                ['--hover-button-text-color' as any]: transitionColor
            } as React.CSSProperties}
        >
            {text}
        </button>
    )
}

export default PrimaryButton