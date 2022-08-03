import axios from 'axios';
import { getCookieToken } from '../storage/Cookie';

const cookieToken = getCookieToken();

// init base API 
export const api = axios.create({
    baseURL : "https://i7d105.p.ssafy.io/api/", 

    headers : {
        "Content-Type" : `application/json`, 

    },
});