import { createSlice } from "@reduxjs/toolkit";

export const TOKEN_TIME_OUT = 600*1000; // 600,000


const initialState = {
    authenticated: false,
    accessToken: null,
    expireTime: null
};

export const tokenSlice = createSlice({
    name: 'authToken', 
    initialState,
    reducers : {
        SET_TOKEN: (state, action) => {
            state.authenticated = true;
            state.accessToken = action.payload;
            state.expireTime = new Date().getTime() + TOKEN_TIME_OUT;
        },
        DELETE_TOKEN: (state) => {
            state.authenticated = false;
            state.accessToken = null;
            state.expireTime = null
        },       
    }
})

export const { SET_TOKEN, DELETE_TOKEN } = tokenSlice.actions;

export default tokenSlice.reducer;