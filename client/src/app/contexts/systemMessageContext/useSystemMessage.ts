import { useContext } from 'react'
import { SystemMessageContext, type SystemMessageContextType } from './SystemMessageContext'

export function useSystemMessage(): SystemMessageContextType {
    const context = useContext(SystemMessageContext)
    
    if (!context) throw new Error('O hook useSystemMessage deve ser usado dentro de provider SystemMessageProvider.')

    return context
}
