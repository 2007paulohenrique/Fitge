import type React from 'react'
import type { Code } from '../../components/pages/identityConfirmation/types'

type SetObjectDataInFormType = <
    T extends Record<string, any>, 
    E extends Record<string, boolean> = Record<string, boolean>
>(
    e: React.ChangeEvent<HTMLInputElement>,
    setData: React.Dispatch<React.SetStateAction<T>>, 
    isRequired: boolean,
    fieldName?: string,
    setFormError?: React.Dispatch<React.SetStateAction<boolean>>,
    setFormDataErrors?: React.Dispatch<React.SetStateAction<E>>,
    validateFunction?: (value: any, isRequired: boolean) => boolean
) => void

export const setObjectDataInForm: SetObjectDataInFormType = (
    e,
    setData,
    isRequired,
    fieldName,
    setFormError,
    setFormDataErrors,
    validateFunction
) => {
    const target = e.target
    const name = target.name
    const value = target.value

    setFormError?.(false)

    setData(prevData => ({
        ...prevData,
        [fieldName ?? name]: value
    }))

    if (validateFunction) {
        const isValid = validateFunction(value, isRequired)
    
        setFormError?.(!isValid)
    
        setFormDataErrors?.(prevErrors => ({
            ...prevErrors,
            [fieldName ?? name]: !isValid
        }))

    }
}

type setCodeObjectInIdentityConfirmationFormType = (
    e: React.ChangeEvent<HTMLInputElement>,
    code: Code,
    setCode: React.Dispatch<React.SetStateAction<Code>>, 
    setCodeError: React.Dispatch<React.SetStateAction<boolean>>,
    focusNext: () => void
) => void

export const setCodeObjectInIdentityConfirmationForm: setCodeObjectInIdentityConfirmationFormType = (
    e,
    code,
    setCode,
    setCodeError,
    focusNext
) => {
    const target = e.target
    const name = target.name
    const value = target.value
    
    const newCode: Code = {
        ...code,
        [name]: value
    }

    setCodeError(Object.values(newCode).some(value => !value))

    setCode(newCode)

    if (value) focusNext()
}