import React from 'react'
import styles from './TextButton.module.css'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'
import { cssVarColors, type CssVarColors } from '../../../utils/consts/cssVariables'

type TextButtonProps = {
    text: string
    varColor?: CssVarColors
    onClick: () => void
}

const TextButton: React.FC<TextButtonProps> = ({ 
    text, 
    varColor = cssVarColors.themeColor,
    onClick
}) => {
    const color = formatVariableName(varColor)

    return (
        <button
            type='button'
            onClick={onClick}
            className={styles.text_button}
            style={{
                ['--button-text-color' as any]: color
            } as React.CSSProperties}
        >
            {text}
        </button>
    )
}

export default TextButton