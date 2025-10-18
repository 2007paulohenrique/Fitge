import api from '../../api/axios'
import type { ErrorHandler, RequestFunction, RequestInUseRequest, SuccessHandler } from '../../hooks/useRequest'
import { getUserMeType } from '../consts/apiEndPoints'

export async function getUserType(request: RequestInUseRequest): Promise<string | null> {
    let userType: string | null = null

    const getRequest: RequestFunction = () => api.get(getUserMeType)

    const onSuccess: SuccessHandler = (data) => {
        userType = !Array.isArray(data.result) ? data.result?.type : null
    }

    const onError: ErrorHandler = () => {
        userType = null
    }

    await request(getRequest, onSuccess, onError)

    return userType
}