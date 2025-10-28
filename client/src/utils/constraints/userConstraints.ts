import { hasOnlyAllLetter, hasOnlyPeriod, hasOnlySimpleSpace, hasOnlySimpleLetter, hasOnlyNumber, hasOnlyUnderline, hasSimpleLetter, hasNumber, hasSymbol } from './regex'

export const nameMaxLength = 50
export const nameRegex = new RegExp(
  `^[${hasOnlyAllLetter.source}${hasOnlyPeriod.source}${hasOnlySimpleSpace.source}]+$`
)

export const nicknameMinLength = 3
export const nicknameMaxLength = 15
export const nicknameRegex = new RegExp(
  `^[${hasOnlyAllLetter.source}${hasOnlyPeriod.source}${hasOnlyUnderline.source}]+$`
)

export const emailMaxLength = 254
export const emailRegex = new RegExp(
  `^[${hasOnlySimpleLetter.source}${hasOnlyNumber.source}._%+-]+@[` +
  `${hasOnlySimpleLetter.source}${hasOnlyNumber.source}.-]+\\.[` +
  `${hasOnlySimpleLetter.source}]{2,}$`
)

export const passwordMinLength = 8
export const passwordMaxLength = 20
export const passwordRegex = new RegExp(
  `^${hasSimpleLetter.source}${hasNumber.source}${hasSymbol.source}[^\\s]+$`
)

export const minAge = 12
