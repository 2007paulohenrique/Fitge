import { createContext, type ReactNode, useState } from 'react'
import type { CreatingUserType } from './types'

export type CreatingUserContextType = {
    creatingUser: CreatingUserType
    setCreatingUser: React.Dispatch<React.SetStateAction<CreatingUserType>>
}

export const CreatingUserContext = createContext<CreatingUserContextType | null>(null)

type CreatingUserProviderProps = {
    children: ReactNode
}

export function CreatingUserProvider({ 
    children 
}: CreatingUserProviderProps) {
    const [creatingUser, setCreatingUser] = useState<CreatingUserType>(null)

    return (
        <CreatingUserContext.Provider 
            value={{ creatingUser, setCreatingUser }}
        >
            {children}
        </CreatingUserContext.Provider>
    )
}
