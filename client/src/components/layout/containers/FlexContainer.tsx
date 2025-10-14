import React, { type JSX } from 'react'

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
    className?: string
    padding?: string | number
    margin?: string | number
    style?: React.CSSProperties
    backgroundColor?: React.CSSProperties['backgroundColor']
    onClick?: () => void
    children?: React.ReactNode
}

const FlexContainer: React.FC<FlexContainerProps> = ({ 
    tag: Tag = 'div',
    flexDirection = 'column',
    gap = '1em',
    alignItems = 'center',
    justifyContent = 'center',
    height = 'max-content',
    width = '100%',
    maxHeight,
    maxWidth,
    className,
    padding = '1em',
    margin,
    style,
    backgroundColor,
    onClick,
    children
}) => {
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
                padding,
                margin,
                backgroundColor,
                ...style
            }}
        >
            {children}
        </Tag>
    )
}

export default FlexContainer
