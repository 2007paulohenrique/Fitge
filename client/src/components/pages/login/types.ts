export interface LoginUser {
    email: string
    password: string
}

export const loginUserInitialState: LoginUser = {
    email: '',
    password: ''
}
