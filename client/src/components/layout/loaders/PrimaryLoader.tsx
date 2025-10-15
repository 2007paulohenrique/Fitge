import BeatLoader from 'react-spinners/BeatLoader'
import React from 'react'
import { themeColor } from '../../../utils/consts/cssVariables'
import FlexContainer from '../containers/FlexContainer'

type PrimaryLoaderProps = {
    loading: boolean
    size?: 'small' | 'medium' | 'large'
}

const PrimaryLoader: React.FC<PrimaryLoaderProps> = ({
    loading,
    size = 'medium'
}) => {
    const defaultSizeMap: Record<string, string> = {
        'small': '1em',
        'medium': '1.5em',
        'large': '2em'
    }

    return (
        loading && (
            <FlexContainer
                height='100%'
            >
                <BeatLoader 
                    size={defaultSizeMap[size]}
                    color={themeColor}
                    loading={loading}
                />
            </FlexContainer>
        )
    ) 
}

export default PrimaryLoader