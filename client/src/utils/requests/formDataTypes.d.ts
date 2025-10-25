import type { authEndPoint, identityConfirmationEndPoint, loginEndPoint, signUpEndPoint } from '../consts/apiEndPoints'

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
    [signUpEndPoint]: SignUpFormData
    [loginEndPoint]: LoginFormData
    [authEndPoint]: AuthFormData
    [identityConfirmationEndPoint]: IdentityConfirmationFormData
}