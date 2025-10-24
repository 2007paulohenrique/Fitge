import React from 'react'
import BeatLoader from 'react-spinners/BeatLoader'
import BaseLoader from './BaseLoader'

type PrimaryLoaderProps = {
    loading: boolean
    size?: 'small' | 'medium' | 'large'
}

const PrimaryLoader: React.FC<PrimaryLoaderProps> = ({ 
    loading, 
    size 
}) => {
    return (
        <BaseLoader 
            loading={loading} 
            loader={BeatLoader} 
            size={size} 
        />
    )
}

export default PrimaryLoader
