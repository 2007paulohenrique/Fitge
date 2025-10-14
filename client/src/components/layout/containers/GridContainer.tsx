import React from 'react'
import FlexContainer from './FlexContainer'

type GridContainerProps = React.ComponentProps<typeof FlexContainer> & {
    columns?: number
    columnGap?: string | number
    rowGap?: string | number
    autoFill?: boolean
    minColumnWidth?: string
}

const GridContainer: React.FC<GridContainerProps> = ({
    columns = 4,
    columnGap = '1em',
    rowGap = '1em',
    autoFill = true,
    minColumnWidth = "200px",
    children,
    style,
    ...rest
}) => {
    const gridTemplateColumns = 
        autoFill
        ? `repeat(auto-fill, minmax(${minColumnWidth}, 1fr))`
        : `repeat(${columns}, 1fr)`

    return (
        <FlexContainer
            {...rest}
            style={{
                display: 'grid',
                gridTemplateColumns,
                columnGap,
                rowGap,
                ...style
            }}
        >
            {children}
        </FlexContainer>
    )
}

export default GridContainer
