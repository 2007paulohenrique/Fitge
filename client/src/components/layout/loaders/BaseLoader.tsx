import React from 'react'
import { themeColor } from '../../../utils/consts/cssVariables'
import FlexContainer from '../../containers/FlexContainer'

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

    return (
        loading && (
            <FlexContainer
                height='100%'
            >
                <Loader 
                    size={defaultSizeMap[size]}
                    color={themeColor}
                    loading={loading}
                />
            </FlexContainer>
        )
    ) 
}

export default BaseLoader