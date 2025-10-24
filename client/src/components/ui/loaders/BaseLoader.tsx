import React from 'react'
import { cssVarColors } from '../../../utils/consts/cssVariables'
import FlexContainer from '../../containers/FlexContainer'
import { formatVariableName } from '../../../utils/formatters/style/cssVariables'

type LoaderComponentType = React.ComponentType<{
    loading: boolean
    size: string | number
    color: string
}>

type BaseLoaderProps = {
    loading: boolean
    loader: LoaderComponentType
    size?: 'small' | 'medium' | 'large'
}

const BaseLoader: React.FC<BaseLoaderProps> = ({
    loading,
    loader: Loader,
    size = 'medium'
}) => {
    const defaultSizeMap: Record<string, number> = {
        'small': 14,
        'medium': 20,
        'large': 28
    }

    const loaderColor = formatVariableName(cssVarColors.themeColor)

    return (
        loading && (
            <FlexContainer
                minHeight='100%'
            >
                <Loader 
                    size={defaultSizeMap[size]}
                    color={loaderColor}
                    loading={loading}
                />
            </FlexContainer>
        )
    ) 
}

export default BaseLoader