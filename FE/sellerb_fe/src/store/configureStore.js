import { configureStore } from '@reduxjs/toolkit'
import userReducer from '../slices/userSlice'
import tokenReducer from '../slices/authSlice'

export const store = configureStore({
  reducer: {
    user: userReducer,
    authToken: tokenReducer,
  },
})