import React from 'react'
import { formatVariableName } from 'src/utils/formatters/style/cssVariables'

type DescriptionProps = {
    text: string
    varColor?: string
    varSize?: '--large-text-size' | '--text-size' | '--small-text-size'
    textAlign?: React.CSSProperties['textAlign']
    hyphens?: 'none' | 'manual' | 'auto'
    maxLines?: number
    style?: React.CSSProperties
    className?: string
}

const Description: React.FC<DescriptionProps> = ({
    text,
    varColor = '--text-color',
    varSize = '--text-size',
    textAlign = 'center',
    hyphens,
    maxLines,
    style,
    className
}) => {
    const defaultHyphens =
        textAlign === 'justify' 
        ? 'auto' 
        : 'none'

    const cssLineClamp =
        maxLines != undefined
        ? { 
            display: '-webkit-box' as any,
            WebkitBoxOrient: 'vertical' as any,
            WebkitLineClamp: maxLines,
            overflow: 'hidden',
            textOverflow: 'ellipsis'
        }
        : {}

    const fontSize = formatVariableName(varSize)
    const color = formatVariableName(varColor)

    return (
        <p
            className={className}
            style={{
                color,
                fontSize,
                textAlign,
                hyphens: hyphens || defaultHyphens,
                ...cssLineClamp,
                ...style
            }}
        >
            {text}
        </p>
    )
}

export default Description
