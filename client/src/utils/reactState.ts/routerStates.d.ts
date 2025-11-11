import type { IDENTITY_CONFIRMATION_ROUTE, routesType } from '../consts/routes'

export type IdentityConfirmationState = {
    user: { 
        email: string 
    },
    origin: routesType
}

export type RouterStateMap = {
    [IDENTITY_CONFIRMATION_ROUTE]: IdentityConfirmationState
}
