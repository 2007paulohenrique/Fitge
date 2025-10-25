const DEFAULT_EXPIRATION_MINUTES = 15

interface StoredItem<T> {
    value: T
    expiry: number
}

export function setSessionStorageItem<T>(key: string, value: T, minutesToExpire: number = DEFAULT_EXPIRATION_MINUTES) {
    const now = Date.now()

    const item: StoredItem<T> = {
        value,
        expiry: now + minutesToExpire * 60 * 1000
    }

    sessionStorage.setItem(key, JSON.stringify(item))
}

export function getSessionStorageItem<T>(key: string): T | null {
    const item = sessionStorage.getItem(key)

    if (!item) return null

    try {
        const parsedItem: StoredItem<T> = JSON.parse(item)

        if (Date.now() > parsedItem.expiry) {
            removeSessionStorageItem(key)

            return null
        }

        return parsedItem.value
    
    } catch {
        removeSessionStorageItem(key)

        return null

    }
}

export function removeSessionStorageItem(key: string) {
    sessionStorage.removeItem(key)
}

export function clearSessionStorage() {
    sessionStorage.clear()
}
