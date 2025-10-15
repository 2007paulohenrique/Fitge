import React from 'react'
import styles from './TextButton.module.css'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'

type TextButtonProps = {
    text: string
    varColor?: string
    onClick: () => void
}

const TextButton: React.FC<TextButtonProps> = ({ 
    text, 
    varColor = '--theme-color',
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