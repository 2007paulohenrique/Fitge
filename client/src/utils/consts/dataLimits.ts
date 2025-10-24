export const hasLetterRegex: RegExp = /[A-Za-z]/
export const hasNumberRegex: RegExp = /\d/
export const hasSymbolRegex: RegExp = /[!@#$%^&*()_\-+=\[\]{};:'",.<>/?\\|`~]/

export const nameMaxLength: number = 50
export const nameRegex: RegExp = /^[A-Za-zÀ-ÖØ-öø-ÿçÇ. ]+$/u

export const nicknameMinLength: number = 3
export const nicknameMaxLength: number = 15
export const nicknameRegex: RegExp = /^[A-Za-zÀ-ÖØ-öø-ÿçÇ0-9._]+$/u

export const emailMaxLength: number = 254
export const emailRegex: RegExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/

export const passwordMinLength: number = 8
export const passwordMaxLength: number = 20
export const passwordRegex = new RegExp(`^(?=.*${hasLetterRegex.source})(?=.*${hasNumberRegex.source})(?=.*${hasSymbolRegex.source})[^\\s]+$`)
