import baseAxios from "axios";
import { getCookieToken } from "../storage/Cookie";
import { useSelector } from "react-redux";
import axios from "axios";


const api = baseAxios.create({
  baseURL: 'https://i7d105.p.ssafy.io/api/',
  headers: {
    'Content-Type': 'application/json',
    // 'Authorization': 'Bearer' +  sessionStorage.getItem("accessToken"),
  },
});

api.interceptors.request.use((config)=> {
  config.headers.Authorization = `Bearer ${sessionStorage.getItem("accessToken")}`;
  return config; 
});

export default api;
// export const api = axios.create({
//   baseURL: 'https://i7d105.p.ssafy.io/api/',
//   headers: {
//     'Content-Type': 'application/json',
//     'Authorization': 'Bearer' +  sessionStorage.getItem("accessToken"),
//   },
// });
