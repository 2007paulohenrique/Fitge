export const hasLetterRegex = /[A-Za-z]/
export const hasNumberRegex = /\d/
export const hasSymbolRegex = /[!@#$%^&*()_\-+=\[\]{};:'",.<>/?\\|`~]/

export const nameMaxLength = 50
export const nameRegex = /^[A-Za-zÀ-ÖØ-öø-ÿçÇ. ]+$/u

export const nicknameMinLength = 3
export const nicknameMaxLength = 15
export const nicknameRegex = /^[A-Za-zÀ-ÖØ-öø-ÿçÇ0-9._]+$/u

export const emailMaxLength = 254
export const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/

export const passwordMinLength = 8
export const passwordMaxLength = 20
export const passwordRegex = new RegExp(`^(?=.*${hasLetterRegex.source})(?=.*${hasNumberRegex.source})(?=.*${hasSymbolRegex.source})[^\\s]+$`)
