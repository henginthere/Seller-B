import { createSlice } from "@reduxjs/toolkit";


// accessToken 만료시간 처리 
export const TOKEN_TIME_OUT = 600*1000; // 60초 ㄴ

const initialState = {
    authenticated: false,
    accessToken: null,
    expireTime: null,
    isAdmin: false, 
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
            state.expireTime = null;
        },   
        CHECK_ADMIN: (state) =>{
            state.isAdmin = true;
        }    
    }
})

export const { SET_TOKEN, DELETE_TOKEN, CHECK_ADMIN } = tokenSlice.actions;

export default tokenSlice.reducer;