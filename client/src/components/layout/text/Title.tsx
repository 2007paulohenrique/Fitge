import React, { type JSX } from 'react'

type TitleProps = {
    text: string
    headingNumber?: 1 | 2 | 3 | 4 | 5 | 6
    varColor?: string
    varSize?: string
    textAlign?: React.CSSProperties['textAlign']
    style?: React.CSSProperties
    className?: string
}

const Title: React.FC<TitleProps> = ({
    text,
    headingNumber = 1,
    varColor,
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

    const fontSize = `var(${varSize || defaultSizeMap[headingNumber]})`
    const color = `var(${varColor})`

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
