import { HAS_ONLY_ALL_LETTER, HAS_ONLY_PERIOD, HAS_ONLY_SIMPLE_SPACE, HAS_ONLY_SIMPLE_LETTER, HAS_ONLY_NUMBER, HAS_ONLY_UNDERLINE, HAS_SIMPLE_LETTER, HAS_NUMBER, HAS_SYMBOL } from './regex'

export const NAME_MAX_LENGTH = 50
export const NAME_REGEX = new RegExp(
  `^[${HAS_ONLY_ALL_LETTER.source}${HAS_ONLY_PERIOD.source}${HAS_ONLY_SIMPLE_SPACE.source}]+$`
)

export const NICKNAME_MIN_LENGTH = 3
export const NICKNAME_MAX_LENGTH = 15
export const NICKNAME_REGEX = new RegExp(
  `^[${HAS_ONLY_ALL_LETTER.source}${HAS_ONLY_PERIOD.source}${HAS_ONLY_UNDERLINE.source}]+$`
)

export const EMAIL_MAX_LENGTH = 254
export const EMAIL_REGEX = new RegExp(
  `^[${HAS_ONLY_SIMPLE_LETTER.source}${HAS_ONLY_NUMBER.source}._%+-]+@[` +
  `${HAS_ONLY_SIMPLE_LETTER.source}${HAS_ONLY_NUMBER.source}.-]+\\.[` +
  `${HAS_ONLY_SIMPLE_LETTER.source}]{2,}$`
)

export const PASSWORD_MIN_LENGTH = 8
export const PASSWORD_MAX_LENGTH = 20
export const PASSWORD_REGEX = new RegExp(
  `^${HAS_SIMPLE_LETTER.source}${HAS_NUMBER.source}${HAS_SYMBOL.source}[^\\s]+$`
)

export const MIN_AGE = 12
