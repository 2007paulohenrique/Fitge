import { EMAIL_MAX_LENGTH, EMAIL_REGEX, NAME_MAX_LENGTH, NAME_REGEX, NICKNAME_MAX_LENGTH, NICKNAME_MIN_LENGTH, NICKNAME_REGEX, PASSWORD_MAX_LENGTH, PASSWORD_MIN_LENGTH, PASSWORD_REGEX } from '../constraints/userConstraints'

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
        NAME_REGEX.test(name) && 
        name.length <= NAME_MAX_LENGTH
    )
}

export function validateUserNickname(nickname: string, isRequired: boolean = false): boolean {
    return (
        isRequiredValid(isRequired, nickname) &&
        NICKNAME_REGEX.test(nickname) && 
        nickname.length <= NICKNAME_MAX_LENGTH && 
        nickname.length >= NICKNAME_MIN_LENGTH
    )
}

export function validateUserEmail(email: string, isRequired: boolean = false): boolean {
    return (
        isRequiredValid(isRequired, email) &&
        EMAIL_REGEX.test(email) && 
        email.length <= EMAIL_MAX_LENGTH
    )
}

export function validateUserPassword(password: string, isRequired: boolean = false): boolean {
    return (
        isRequiredValid(isRequired, password) &&
        PASSWORD_REGEX.test(password) && 
        !/\s/.test(password) && 
        password.length <= PASSWORD_MAX_LENGTH && 
        password.length >= PASSWORD_MIN_LENGTH
    )
}