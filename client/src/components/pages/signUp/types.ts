export interface SignUpUser {
    name: string
    nickname: string
    email: string
    password: string
}

export interface SignUpErrors {
    name: boolean
    nickname: boolean
    email: boolean
    password: boolean
    [key: string]: boolean
}

export const signUpUserInitialState: SignUpUser = {
    email: '',
    password: '',
    name: '',
    nickname: ''
}

export const signUpErrorsInitialState: SignUpErrors = {
    name: false,
    nickname: false,
    email: false,
    password: false
}
