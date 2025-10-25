import type { identityConfirmationRoute } from "../consts/routes"

export type IdentityConfirmationState = {
    user: { 
        email: string 
    }
}

export type RouterStateMap = {
    [identityConfirmationRoute]: IdentityConfirmationState
}
