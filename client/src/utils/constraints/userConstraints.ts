import { HAS_ONLY_ALL_LETTER, HAS_ONLY_PERIOD, HAS_ONLY_SIMPLE_SPACE, HAS_ONLY_SIMPLE_LETTER, HAS_ONLY_NUMBER, HAS_ONLY_UNDERLINE, HAS_SIMPLE_LETTER, HAS_NUMBER, HAS_SYMBOL } from './regex'

export const nameMaxLength = 50
export const nameRegex = new RegExp(
  `^[${HAS_ONLY_ALL_LETTER.source}${HAS_ONLY_PERIOD.source}${HAS_ONLY_SIMPLE_SPACE.source}]+$`
)

export const nicknameMinLength = 3
export const nicknameMaxLength = 15
export const nicknameRegex = new RegExp(
  `^[${HAS_ONLY_ALL_LETTER.source}${HAS_ONLY_PERIOD.source}${HAS_ONLY_UNDERLINE.source}]+$`
)

export const emailMaxLength = 254
export const emailRegex = new RegExp(
  `^[${HAS_ONLY_SIMPLE_LETTER.source}${HAS_ONLY_NUMBER.source}._%+-]+@[` +
  `${HAS_ONLY_SIMPLE_LETTER.source}${HAS_ONLY_NUMBER.source}.-]+\\.[` +
  `${HAS_ONLY_SIMPLE_LETTER.source}]{2,}$`
)

export const passwordMinLength = 8
export const passwordMaxLength = 20
export const passwordRegex = new RegExp(
  `^${HAS_SIMPLE_LETTER.source}${HAS_NUMBER.source}${HAS_SYMBOL.source}[^\\s]+$`
)

export const minAge = 12
