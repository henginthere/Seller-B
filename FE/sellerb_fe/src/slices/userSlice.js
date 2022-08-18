import { createSlice } from '@reduxjs/toolkit';
import axios from 'axios';


const initialState = {
    isLoggedIn: false,
    isAdmin: false,
    id: '',
}

export const userSlice = createSlice({
    name: "user",
    initialState,
    reducers: {
        LOGIN: (state, action) => {

            // axios 
            axios({
                method: "post",
                url: "i7d105.p.ssafy.io",
                data: {
                    id: action.payload.id,
                    pass: action.payload.password,
                },
            })
            .then((res)=>{
                // 받아올 데이터 : accessToken, RefreshToken, isAdmin값, 
                // RT예상만료시간, 정책시간 
                // eX) 예상만료시간이 30분, 정책시간이 20분 -> 20분이후부터 refreshToken 갱신 가능
                console.log(res.data);
                const accessToken = res.data.accessToken;
                const refreshToken = res.data.refreshToken;

                
            })
        },
        
    },
});

export const { LOGIN } = userSlice.actions
export default userSlice.reducer;