import { createContext, type ReactNode, useState } from 'react'

export type ConfirmIdentityCallbackContextType = {
    onConfirmIdentity: () => void
    setOnConfirmIdentity: (callback: () => void) => void
}

export const ConfirmIdentityCallbackContext = createContext<ConfirmIdentityCallbackContextType | null>(null)

type ConfirmIdentityCallbackProviderProps = {
    children: ReactNode
}

export function ConfirmIdentityCallbackProvider({ 
    children 
}: ConfirmIdentityCallbackProviderProps) {
    const [calback, setCalback] = useState<() => void>()

    const onConfirmIdentity = () => {
        calback?.()

        setCalback(undefined)
    }
    
    const setOnConfirmIdentity = (cb: () => void) => {
        setCalback(() => cb)
    }

    return (
        <ConfirmIdentityCallbackContext.Provider 
            value={{ onConfirmIdentity, setOnConfirmIdentity }}
        >
            {children}
        </ConfirmIdentityCallbackContext.Provider>
    )
}
