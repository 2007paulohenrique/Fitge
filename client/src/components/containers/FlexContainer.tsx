import { forwardRef } from 'react'
import React, { type JSX } from 'react'
import { formatVariableName } from '../../utils/formatters/style/cssVariables'

type FlexContainerProps = {
    tag?: keyof JSX.IntrinsicElements
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
    varBackgroundColor?: string
    onClick?: () => void
    children?: React.ReactNode
} & any

const FlexContainer = forwardRef<HTMLElement, FlexContainerProps>(({
    tag: Tag = 'div',
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
    padding = '0',
    margin,
    style,
    varBackgroundColor = '--bg-color',
    onClick,
    children,
    ...rest
}, ref) => {
    const backgroundColor = formatVariableName(varBackgroundColor)

    return (
        <Tag
            ref={ref}
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
})

FlexContainer.displayName = 'FlexContainer'

export default FlexContainer
