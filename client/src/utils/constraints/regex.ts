export const hasSimpleLetter = /(?=.*[A-Za-z])/
export const hasNumber = /(?=.*\d)/
export const hasSymbol = /(?=.*[!@#$%^&*()_+\-=\[\]{}':"\\|,.<>/?])/

export const hasNotLineBreaks = /(?!.*(\r|\n))/
export const hasNotDoubleSpaces = /(?!.*(  ))/

export const hasOnlySimpleLetter = /[A-Za-z]/
export const hasOnlyNumber = /[0-9]/
export const hasOnlyAllLetter = /[A-Za-zÀ-ÖØ-öø-ÿçÇ]/
export const hasOnlySimpleSpace = / /
export const hasOnlyPeriod = /\./
export const hasOnlyUnderline = /_/
