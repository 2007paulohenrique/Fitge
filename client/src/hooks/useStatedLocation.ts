import { useLocation } from "react-router-dom"
import type { RouterStateMap } from "../utils/reactState.ts/routerStates"

export default function useStatedLocation<T extends keyof RouterStateMap>() {
    return useLocation() as unknown as Location & { state: RouterStateMap[T] }
}
