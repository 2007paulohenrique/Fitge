import type { NavigateFunction } from 'react-router-dom'
import api from '../../api/axios'
import { setUser } from '../../app/store/slices/user/userSlice'
import type { UserState } from '../../app/store/slices/user/userStates'
import type { AppDispatch } from '../../app/store/store'
import type { ErrorHandler, RequestFunction, RequestInUseRequest, SuccessHandler } from '../../hooks/useRequest'
import { authEndPoint, getUserMeTypeEndPoint } from '../consts/apiEndPoints'
import { loginRoute } from '../consts/routes'

export async function getUserType(request: RequestInUseRequest): Promise<string | null> {
    let userType: string | null = null

    const getRequest: RequestFunction = () => api.get(getUserMeTypeEndPoint)

    const onSuccess: SuccessHandler = (data) => {
        userType = data.result?.type
    }

    const onError: ErrorHandler = () => {
        userType = null
    }

    await request(getRequest, onSuccess, onError)

    return userType
}

export async function userAuth(request: RequestInUseRequest, dispatch: AppDispatch, navigate: NavigateFunction, formData: FormData): Promise<void> {
    const postRequest: RequestFunction = () => api.post(authEndPoint, formData)

    const onSuccess: SuccessHandler = (data) => {
        dispatch(setUser(data.result as UserState))

        navigate('/')
    }

    const onError: ErrorHandler = () => {
        navigate(loginRoute)
    }

    await request(postRequest, onSuccess, onError)
}
