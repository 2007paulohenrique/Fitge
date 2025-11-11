import { useMemo } from 'react';
import type { ApiRequestDataMap } from '../utils/requests/requestDataTypes'

export default function useFormData<T extends keyof ApiRequestDataMap>(_route: T) {
    const formData = useMemo(() => new FormData(), [])

    function appendFormDataValue<K extends keyof ApiRequestDataMap[T]>(
        key: K,
        value: ApiRequestDataMap[T][K]
    ) {
        if (value === undefined || value === null) return
        
        formData.append(key as string, value instanceof Blob ? value : String(value))
    }

    return { formData, appendFormDataValue }
}