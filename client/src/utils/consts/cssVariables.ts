export const cssVarColors = {
    themeColor: '--theme-color',
    lightThemeColor: '--light-theme-color',
    darkThemeColor: '--dark-theme-color',
    textColor: '--text-color',
    bgColor: '--bg-color',
    grayColor: '--gray-color',
    blackColor: '--black-color',
    whiteColor: '--white-color',
    alertColor: '--alert-color',
    successColor: '--success-color'
} as const

export type CssVarColors = (typeof cssVarColors)[keyof typeof cssVarColors]

export const cssVarSizes = {
    largeTitleSize: '--large-title-size',
    titleSize: '--title-size',
    smallTitleSize: '--small-title-size',
    largeTextSize: '--large-text-size',
    textSize: '--text-size',
    smallTextSize: '--small-text-size'
} as const

export type CssVarSizes = (typeof cssVarSizes)[keyof typeof cssVarSizes]

export const cssVarTitleSizes = {
    largeTitleSize: '--large-title-size',
    titleSize: '--title-size',
    smallTitleSize: '--small-title-size'
} as const

export type CssVarTitleSizes = (typeof cssVarTitleSizes)[keyof typeof cssVarTitleSizes]

export const cssVarTextSizes = {
    largeTextSize: '--large-text-size',
    textSize: '--text-size',
    smallTextSize: '--small-text-size'
} as const

export type CssVarTextSizes = (typeof cssVarTextSizes)[keyof typeof cssVarTextSizes]
