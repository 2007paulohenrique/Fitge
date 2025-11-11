import type { NavigateFunction } from 'react-router-dom'
import api from '../../api/axios'
import { setUser } from '../../app/store/slices/user/userSlice'
import type { UserSliceState } from '../../app/store/slices/user/userStates'
import type { AppDispatch } from '../../app/store/store'
import type { ErrorHandler, RequestFunction, RequestInUseRequest, SuccessHandler } from '../../hooks/useRequest'
import { AUTH_ENDPOINT, GET_USER_ME_TYPE_ENDPOINT } from '../consts/apiEndPoints'
import { LOGIN_ROUTE } from '../consts/routes'

export async function getUserType(request: RequestInUseRequest): Promise<string | null> {
    let userType: string | null = null

    const getRequest: RequestFunction = () => api.get(GET_USER_ME_TYPE_ENDPOINT)

    const onSuccess: SuccessHandler = (data) => {
        userType = data.result?.type
    }

    const onError: ErrorHandler = () => {
        userType = null
    }

    await request(getRequest, onSuccess, onError)

    return userType
}

export async function userAuth(request: RequestInUseRequest, dispatch: AppDispatch, navigate: NavigateFunction): Promise<void> {
    const postRequest: RequestFunction = () => api.post(AUTH_ENDPOINT)

    const onSuccess: SuccessHandler = (data) => {
        dispatch(setUser(data.result as UserSliceState))

        navigate('/')
    }

    const onError: ErrorHandler = () => {
        navigate(LOGIN_ROUTE)
    }

    await request(postRequest, onSuccess, onError)
}
