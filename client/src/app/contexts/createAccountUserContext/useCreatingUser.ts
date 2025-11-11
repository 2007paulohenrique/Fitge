import { useContext } from 'react'
import { CreatingUserContext, type CreatingUserContextType } from './CreatingUserContext'

export function useCreatingUser(): CreatingUserContextType {
    const context = useContext(CreatingUserContext)

    if (!context) throw new Error('useCreatingUser deve ser usado dentro de CreatingUserProvider')

    return context
}
