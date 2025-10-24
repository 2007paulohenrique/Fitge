import { emailMaxLength, emailRegex, nameMaxLength, nameRegex, nicknameMaxLength, nicknameMinLength, nicknameRegex, passwordMaxLength, passwordMinLength, passwordRegex } from '../consts/dataLimits'

function isRequiredValid(isRequired: boolean, value: any): boolean {
    return (
        isRequired
        ? !!value
        : true
    )
}

export function validateUserName(name: string, isRequired: boolean = false): boolean { 
    return (
        isRequiredValid(isRequired, name) &&
        nameRegex.test(name) && 
        name.length <= nameMaxLength
    )
}

export function validateUserNickname(nickname: string, isRequired: boolean = false): boolean {
    return (
        isRequiredValid(isRequired, nickname) &&
        nicknameRegex.test(nickname) && 
        nickname.length <= nicknameMaxLength && 
        nickname.length >= nicknameMinLength
    )
}

export function validateUserEmail(email: string, isRequired: boolean = false): boolean {
    return (
        isRequiredValid(isRequired, email) &&
        emailRegex.test(email) && 
        email.length <= emailMaxLength
    )
}

export function validateUserPassword(password: string, isRequired: boolean = false): boolean {
    return (
        isRequiredValid(isRequired, password) &&
        passwordRegex.test(password) && 
        !/\s/.test(password) && 
        password.length <= passwordMaxLength && 
        password.length >= passwordMinLength
    )
}