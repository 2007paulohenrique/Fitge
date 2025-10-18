import { createSlice, type PayloadAction } from '@reduxjs/toolkit'
import initialState, { type UserState } from './userStates'

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    setUser: (_state, action: PayloadAction<UserState>) => action.payload,
    updateUser: (state, action: PayloadAction<Partial<UserState>>) => {
      return {
        ...state, 
        ...action.payload,
        config: {
          ...state.config,
          ...(action.payload.config || {}),
        }
      }
    },
    resetUser: () => initialState
  }
})

export const { setUser, updateUser, resetUser } = userSlice.actions
export default userSlice.reducer
