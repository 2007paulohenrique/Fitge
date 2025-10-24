import React, { useCallback } from 'react'
import FlexContainer from '../containers/FlexContainer'

type BaseFormProps = {
    onSubmit: () => void
    hasError: boolean
    acceptTerms?: boolean
    children?: React.ReactNode
}

const BaseForm: React.FC<BaseFormProps> = ({
    onSubmit,
    hasError,
    acceptTerms,
    children
}) => {
    const handleOnError = useCallback((e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        if (hasError) return

        if (acceptTerms !== undefined && !acceptTerms) return

        onSubmit()
    }, [
        hasError, 
        onSubmit
    ])

    return (
        <FlexContainer
            tag='form'
            onSubmit={handleOnError}
        >
            {children}
        </FlexContainer>
    )
}

export default BaseForm