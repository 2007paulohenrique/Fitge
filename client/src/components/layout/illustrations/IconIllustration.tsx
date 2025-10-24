import React from 'react'
import styles from './IconIllustration.module.css'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'
import { cssVarColors, type CssVarColors } from '../../../utils/consts/cssVariables'

type IconIllustrationProps = {
    icon?: React.FC<React.SVGProps<SVGSVGElement>>
    src?: string
    name?: string
    size?: 'tiny' | 'small' | 'medium' | 'large' | 'fill'
    varColor?: CssVarColors | 'none'
}

const IconIllustration: React.FC<IconIllustrationProps> = ({
    icon: Icon,
    src,
    name,
    size = 'medium',
    varColor = cssVarColors.themeColor
}) => { 
    const color = varColor !== 'none' ? formatVariableName(varColor) : undefined

    return (
        <div
            className={`
                ${styles.icon_illustration} 
                ${styles[size]} 
            `}
        >
            {Icon ? (
                <Icon
                    width='100%'
                    height='100%'
                    style={{ 
                        color 
                    }}
                    role='img'
                    aria-label={name}
                />
            ) : src ? (
                <img
                    src={src}
                    alt={name}
                    loading="lazy"
                />
            ) : null}
        </div>
    )
}

export default IconIllustration