import { useContext } from 'react'
import { ConfirmIdentityCallbackContext, type ConfirmIdentityCallbackContextType } from './ConfirmIdentityCallbackContext'

export function useConfirmIdentityCallback(): ConfirmIdentityCallbackContextType {
    const context = useContext(ConfirmIdentityCallbackContext)

    if (!context) throw new Error('O hook useConfirmIdentityCallback deve ser usado dentro de provider ConfirmIdentityCallbackProvider')

    return context
}
