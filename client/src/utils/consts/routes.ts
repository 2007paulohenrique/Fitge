export const LOGIN_ROUTE = '/login' as const
export const SIGN_UP_ROUTE = '/sign-up' as const
export const RECOVER_PASSWORD_ROUTE = '/recover-password' as const
export const IDENTITY_CONFIRMATION_ROUTE = '/identity-confirmation' as const
export const SETUP_SETTINGS_ROUTE = '/setup/settings' as const
export const ERROR_ROUTE = '/error' as const
export const TERMS_AND_CONDITIONS_ROUTE = '/app/terms-and-conditions' as const
export const POLICIES_ROUTE = '/app/policies' as const
export const INTRODUCTION_ROUTE = '/app/introduction' as const
export const ABOUT_ROUTE = '/app/about' as const
export const HELP_ROUTE = '/app/help' as const
export const PRIVACY_AND_SECURITY_POLICY_ROUTE = '/app/policies/privacy-and-security' as const
export const CONTENT_POLICY_ROUTE = '/app/policies/content' as const
export const COOKIES_POLICY_ROUTE = '/app/policies/cookies' as const

export type routesType = 
    typeof LOGIN_ROUTE |
    typeof SIGN_UP_ROUTE |
    typeof RECOVER_PASSWORD_ROUTE |
    typeof IDENTITY_CONFIRMATION_ROUTE |
    typeof SETUP_SETTINGS_ROUTE |
    typeof ERROR_ROUTE |
    typeof TERMS_AND_CONDITIONS_ROUTE |
    typeof POLICIES_ROUTE |
    typeof INTRODUCTION_ROUTE |
    typeof ABOUT_ROUTE |
    typeof HELP_ROUTE |
    typeof PRIVACY_AND_SECURITY_POLICY_ROUTE |
    typeof CONTENT_POLICY_ROUTE |
    typeof COOKIES_POLICY_ROUTE
