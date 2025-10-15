import React from 'react'
import styles from './PrimaryButton.module.css'

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
    const backgroundColor = `var(${initialVarBackgroundColor})`
    const transitionBackgroundColor = `var(${transitionVarBackgroundColor})`
    const color = `var(${initialVarColor})`
    const transitionColor = `var(${transitionVarColor})`

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