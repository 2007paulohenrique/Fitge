import React from 'react'
import styles from './ClickableIcon.module.css'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'
import { cssVarColors, type CssVarColors } from '../../../utils/consts/cssVariables'

type ClickableIconProps = {
    icon: React.FC<React.SVGProps<SVGSVGElement>>
    name?: string
    size?: 'tiny' | 'small' | 'medium' | 'large'
    hasTransition?: boolean
    varColor?: CssVarColors | 'none'
    onClick?: () => void
    isSubmit?: boolean
}

const ClickableIcon: React.FC<ClickableIconProps> = ({
    icon: Icon,
    name,
    size = 'medium',
    hasTransition = true,
    varColor = cssVarColors.textColor,
    onClick,
    isSubmit = false
}) => { 
    const color = varColor !== 'none' ? formatVariableName(varColor) : undefined

    return (
        <button
            type={isSubmit ? 'submit' : 'button'}
            onClick={onClick}
            title={name}
            className={`
                ${styles.clickable_icon} 
                ${styles[size]} 
                ${hasTransition ? styles.transition : undefined} 
            `}
        >
            <Icon
                width='100%'
                height='100%'
                style={{
                    color
                }}
                role='img'
                aria-label={name}
            />
        </button>
    )
}

export default ClickableIcon