import React from 'react'
import styles from './PrimaryButton.module.css'
import { formatVariableName } from 'src/utils/formatters/style/cssVariables'

type PrimaryButtonProps = {
    text: string
    initialVarBackgroundColor?: string
    transitionVarBackgroundColor?: string
    initialVarColor?: string
    transitionVarColor?: string
    onClick?: () => void
    isSubmit?: boolean
}

const PrimaryButton: React.FC<PrimaryButtonProps> = ({ 
    text, 
    initialVarBackgroundColor = '--theme-color',
    transitionVarBackgroundColor = '--light-theme-color',
    initialVarColor = '--white-color',
    transitionVarColor = '--black-color',
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