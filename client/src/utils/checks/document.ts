export function checkIsDarkTheme(document: Document | undefined): boolean {
    return (
        typeof document !== 'undefined' &&
        document?.documentElement.getAttribute('data-theme') === 'dark'
    )
}
