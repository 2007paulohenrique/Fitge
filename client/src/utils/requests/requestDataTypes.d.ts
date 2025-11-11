import type { IDENTITY_CONFIRMATION_ENDPOINT, LOGIN_ENDPOINT, SIGN_UP_ENDPOINT } from '../consts/apiEndPoints'

export type SignUpRequestData = {
    name: string
    nickname: string
    email: string
    password: string
}

export type LoginRequestData = {
    email: string
    password: string
}

export type IdentityConfirmationRequestData = {
    code: string
}

export type ApiRequestDataMap = {
    [SIGN_UP_ENDPOINT]: SignUpRequestData
    [LOGIN_ENDPOINT]: LoginRequestData
    [IDENTITY_CONFIRMATION_ENDPOINT]: IdentityConfirmationRequestData
}