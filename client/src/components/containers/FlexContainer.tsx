import React from 'react'
import { formatVariableName } from '../../utils/formatters/style/cssVariables'
import { cssVarColors, type CssVarColors } from '../../utils/consts/cssVariables'

type BaseFlexProps = {
    flexDirection?: React.CSSProperties['flexDirection']
    gap?: string | number
    alignItems?: React.CSSProperties['alignItems']
    justifyContent?: React.CSSProperties['justifyContent']
    height?: string | number
    width?: string | number
    maxHeight?: string | number
    maxWidth?: string | number
    minHeight?: string | number
    minWidth?: string | number
    className?: string
    padding?: string | number
    margin?: string | number
    style?: React.CSSProperties
    varBackgroundColor?: CssVarColors
    onClick?: () => void
    children?: React.ReactNode
}

type HtmlTag = keyof React.JSX.IntrinsicElements

type FlexContainerProps<T extends HtmlTag = 'div'> = BaseFlexProps & {
    tag?: T
} & Omit<React.ComponentPropsWithoutRef<T>, keyof BaseFlexProps | 'tag'>

const FlexContainer = <T extends HtmlTag = 'div'>({
    tag,
    flexDirection = 'column',
    gap = '1em',
    alignItems = 'center',
    justifyContent = 'center',
    height = 'max-content',
    width = '100%',
    maxHeight,
    maxWidth,
    minHeight,
    minWidth = 'min-content',
    className,
    padding,
    margin,
    style,
    varBackgroundColor = cssVarColors.bgColor,
    onClick,
    children,
    ...rest
}: FlexContainerProps<T>) => {
    const Tag = (tag || 'div') as React.ElementType

    const backgroundColor = formatVariableName(varBackgroundColor)

    return (
        <Tag
            className={className}
            onClick={onClick}
            style={{
                display: 'flex',
                flexDirection,
                gap,
                alignItems,
                justifyContent,
                height,
                width,
                maxHeight,
                maxWidth,
                minHeight,
                minWidth,
                padding,
                margin,
                backgroundColor,
                ...style
            }}
            {...rest}
        >
            {children}
        </Tag>
    )
}

export default FlexContainer
