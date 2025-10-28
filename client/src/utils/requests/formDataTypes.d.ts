import type { AUTH_ENDPOINT, IDENTITY_CONFIRMATION_ENDPOINT, LOGIN_ENDPOINT, SIGN_UP_ENDPOINT } from '../consts/apiEndPoints'

export type SignUpFormData = {
    name: string
    nickname: string
    email: string
    password: string
}

export type LoginFormData = {
    email: string
    password: string
}

export type AuthFormData = {
    secureId: string
}

export type IdentityConfirmationFormData = {
    code: string
}

export type ApiFormDataMap = {
    [SIGN_UP_ENDPOINT]: SignUpFormData
    [LOGIN_ENDPOINT]: LoginFormData
    [AUTH_ENDPOINT]: AuthFormData
    [IDENTITY_CONFIRMATION_ENDPOINT]: IdentityConfirmationFormData
}