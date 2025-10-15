import React from 'react'
import FlexContainer from './FlexContainer'

type FlexWrapContainerProps = React.ComponentProps<typeof FlexContainer> & {
    flexWrap?: React.CSSProperties['flexWrap']
    rowGap?: string | number
    columnGap?: string | number
}

const FlexWrapContainer: React.FC<FlexWrapContainerProps> = ({
    flexWrap = 'wrap',
    flexDirection = 'row',
    rowGap = '1em',
    columnGap = '1em',
    children,
    style,
    ...rest
}) => {
    return (
        <FlexContainer
            {...rest}
            flexDirection={flexDirection}
            style={{
                flexWrap,
                rowGap,
                columnGap,
                ...style
            }}
        >
            {children}
        </FlexContainer>
    )
}

export default FlexWrapContainer
