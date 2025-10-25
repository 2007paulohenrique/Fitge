import type { identityConfirmationRote, setupSettingsRote, signUpRote } from "../consts/routes"

export type IdentityConfirmationState = {
    user: { 
        email: string 
    }
}

export type RouterStateMap = {
    [identityConfirmationRote]: IdentityConfirmationState
}
