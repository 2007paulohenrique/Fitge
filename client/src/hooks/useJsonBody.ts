import { useMemo } from 'react'
import type { ApiRequestDataMap } from '../utils/requests/requestDataTypes'

export default function useJsonBody<T extends keyof ApiRequestDataMap>(_route: T) {
    const body = useMemo(() => ({} as Partial<ApiRequestDataMap[T]>), [])

    function appendBodyValue<K extends keyof ApiRequestDataMap[T]>(
        key: K,
        value: ApiRequestDataMap[T][K]
    ) {
        if (value === undefined || value === null) return
        
        body[key] = value
    }

    return { body, appendBodyValue }
}
