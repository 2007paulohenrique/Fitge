import React from 'react'
import styles from './IconIllustration.module.css'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'

type IconIllustrationProps = {
    icon: React.FC<React.SVGProps<SVGSVGElement>>
    name?: string
    size?: 'tiny' | 'small' | 'medium' | 'large' | 'fill'
    varColor?: string
}

const IconIllustration: React.FC<IconIllustrationProps> = ({
    icon: Icon,
    name,
    size = 'medium',
    varColor = '--theme-color'
}) => { 
    const color = formatVariableName(varColor)

    return (
        <div
            className={`
                ${styles.icon_illustration} 
                ${styles[size]} 
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
        </div>
    )
}

export default IconIllustration