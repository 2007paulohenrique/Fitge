import { useNavigate, type To } from 'react-router-dom'
import type { RouterStateMap } from '../utils/reactState.ts/routerStates'

type Exact<T> = T & Record<never, never>

export default function useTypedNavigate() {
    const navigate = useNavigate()

    function typedNavigate<T extends keyof RouterStateMap>(
        to: T,
        options: { state: Exact<RouterStateMap[T]>; replace?: boolean }
    ) {
        navigate(to as To, options as any)
    }

    return typedNavigate
}