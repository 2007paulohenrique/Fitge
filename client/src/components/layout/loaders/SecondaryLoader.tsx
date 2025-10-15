import React from 'react'
import MoonLoader from 'react-spinners/MoonLoader'
import BaseLoader from './BaseLoader'

type SecondaryLoaderProps = {
    loading: boolean
    size?: 'small' | 'medium' | 'large'
}

const SecondaryLoader: React.FC<SecondaryLoaderProps> = ({ 
    loading, 
    size 
}) => {
    return (
        <BaseLoader 
            loading={loading} 
            loader={MoonLoader} 
            size={size} 
        />
    )
}

export default SecondaryLoader
