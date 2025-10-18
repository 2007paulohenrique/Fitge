export function formatResponseMessageForTranslation(messageCode: string, requestResult: 'success' | 'error'): string {
    return `responses.${requestResult}.${messageCode}`
}