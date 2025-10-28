import type { IDENTITY_CONFIRMATION_ROUTE } from '../consts/routes'

export type IdentityConfirmationState = {
    user: { 
        email: string 
    }
}

export type RouterStateMap = {
    [IDENTITY_CONFIRMATION_ROUTE]: IdentityConfirmationState
}
