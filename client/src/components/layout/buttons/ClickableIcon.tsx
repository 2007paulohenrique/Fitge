import React from 'react'
import styles from './ClickableIcon.module.css'
import { formatVariableName } from 'src/utils/formatters/style/cssVariables'

type ClickableIconProps = {
    icon: React.FC<React.SVGProps<SVGSVGElement>>
    name?: string
    size?: 'tiny' | 'small' | 'medium' | 'large'
    hasTransition?: boolean
    varColor?: string
    onClick?: () => void
    isSubmit?: boolean
}

const ClickableIcon: React.FC<ClickableIconProps> = ({
    icon: Icon,
    name,
    size = 'medium',
    hasTransition = true,
    varColor = '--text-color',
    onClick,
    isSubmit = false
}) => { 
    const color = formatVariableName(varColor)

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