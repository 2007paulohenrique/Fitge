import React from 'react'
import FlexContainer from '../../containers/FlexContainer'

type BaseCardProps = React.ComponentProps<typeof FlexContainer> & {
    border?: string
    borderRadius?: string | number 
    boxShadow?: string
}

const BaseCard: React.FC<BaseCardProps> = ({
    border = '2px solid var(--text-color)',
    borderRadius = '20px',
    boxShadow,
    style,
    children,
    ...rest
}) => {
    return (
        <FlexContainer
            {...rest}
            style={{
                border,
                borderRadius,
                boxShadow,
                overflow: 'hidden',
                ...style
            }}
        >
            {children}
        </FlexContainer>
    )
}

export default BaseCard