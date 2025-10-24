import React, { type JSX } from 'react'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'
import { cssVarColors, type CssVarColors, type CssVarSizes } from '../../../utils/consts/cssVariables'

type TitleProps = {
    text: string
    headingNumber?: 1 | 2 | 3 | 4 | 5 | 6
    varColor?: CssVarColors
    varSize?: CssVarSizes
    textAlign?: React.CSSProperties['textAlign']
    style?: React.CSSProperties
    className?: string
}

const Title: React.FC<TitleProps> = ({
    text,
    headingNumber = 1,
    varColor = cssVarColors.textColor,
    textAlign = 'center',
    varSize,
    style,
    className
}) => {
    const Heading = `h${headingNumber}` as keyof JSX.IntrinsicElements

    const defaultSizeMap: Record<number, string> = {
        1: '--large-title-size',
        2: '--title-size',
        3: '--small-title-size',
        4: '--large-text-size',
        5: '--text-size',
        6: '--text-size'
    }

    const fontSize = formatVariableName(varSize || defaultSizeMap[headingNumber])
    const color = formatVariableName(varColor)

    return (
        <Heading
            className={className}
            style={{
                color,
                textAlign,
                fontSize,
                ...style
            }}
        >
            {text}
        </Heading>
    )
}

export default Title
