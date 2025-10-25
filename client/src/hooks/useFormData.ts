import { useMemo } from 'react';
import type { ApiFormDataMap } from '../utils/requests/formDataTypes';

export default function useFormData<T extends keyof ApiFormDataMap>(_route: T) {
    const formData = useMemo(() => new FormData(), [])

    function appendFormDataValue<K extends keyof ApiFormDataMap[T]>(
        key: K,
        value: ApiFormDataMap[T][K]
    ) {
        if (value === undefined || value === null) return
        
        formData.append(key as string, value instanceof Blob ? value : String(value))
    }

    return { formData, appendFormDataValue }
}