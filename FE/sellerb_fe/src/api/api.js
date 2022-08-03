import axios from 'axios';

// init base API 
export const api = axios.create({
    baseURL : "https://i7d105.p.ssafy.io/", 

    headers : {
        "Content-Type" : `application/json`, 
        // Authorization: `Bearer ${
        //     sessionStorage.getItem("access-token") ||
        //     localStorage.getItem("access-token")
        // }`,
    },
});