import React from 'react'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'
import { cssVarColors, cssVarTextSizes, type CssVarColors, type CssVarTextSizes } from '../../../utils/consts/cssVariables'

type DescriptionProps = {
    text: React.ReactNode
    varColor?: CssVarColors
    varSize?: CssVarTextSizes
    textAlign?: React.CSSProperties['textAlign']
    hyphens?: React.CSSProperties['hyphens']
    maxLines?: number
    isBold?: boolean
    isVisible?: boolean
    style?: React.CSSProperties
    className?: string
}

const Description: React.FC<DescriptionProps> = ({
    text,
    varColor = cssVarColors.textColor,
    varSize = cssVarTextSizes.textSize,
    textAlign = 'center',
    hyphens,
    maxLines,
    isBold = false,
    isVisible = true,
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

    const fontWeight = 
        isBold 
        ? 'bold' 
        : 'normal'
    
    const visibility = 
        isVisible 
        ? 'visible' 
        : 'hidden'

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
                fontWeight,
                visibility,
                ...cssLineClamp,
                ...style
            }}
        >
            {text}
        </p>
    )
}

export default Description
