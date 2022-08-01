import axios from 'axios';

// init base API 
export const fileapi = axios.create({
    // baseURL : "https://localhost:8080/", 
    baseURL : "https://i7d105t.p.ssafy.io/", 
    headers : {
        "Content-Type" : `multipart/form-data`, 
        // Authorization: `Bearer ${
        //     sessionStorage.getItem("access-token") ||
        //     localStorage.getItem("access-token")
        // }`,
    },
});