import { createSlice, type PayloadAction } from '@reduxjs/toolkit'
import type { UserSliceState } from './userStates'

const initialState = null as UserSliceState

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    setUser: (_state: UserSliceState, action: PayloadAction<UserSliceState>) => action.payload,
    updateUser: (state: UserSliceState, action: PayloadAction<Partial<UserSliceState>>) => {
      if (!state) return state
      return {
        ...state,
        ...action.payload
      }
    },
    resetUser: () => initialState
  }
})

export const { setUser, updateUser, resetUser } = userSlice.actions
export default userSlice.reducer
