import axios from "axios";
import { getCookieToken } from "../storage/Cookie";
import { useSelector } from "react-redux";

//
//export const RT = getCookieToken();
// const AT = () =>
//     useSelector((state)=> state.authToken.accessToken);

// init base API

// axios.defaults.headers.common['Authorization'] = useSelector((state)=> state.authToken.accessToken);

export const api = axios.create({
  baseURL: "https://i7d105.p.ssafy.io/api/",
  headers: {
    "Content-Type": `application/json`,
    Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
  },
});

// export const getToken = ()=>{
//     const a = useSelector((state)=>
//         state.authToken.accessToken);
// }

// export const api = apiInstance.interceptors.request.use(function (config) {
//     const token = useSelector((state)=>
//         state.authToken.accessToken
//     );

//     if(!token){
//         config.headers["accessToken"] = null;
//         config.headers["refreshToken"] = null;
//         return config;
//     }
//     if(config.headers && token) {
//         config.headers["Authorization"] = `Bearer ${token}`;

//         return config;
//     }
// });
