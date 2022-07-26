import axios from 'axios';

// init base API 
export const api = axios.create({
    baseURL : "https://localhost:8080/", 
    headers : {
        "Content-Type" : `application/json`, 
        Authorization: `Bearer ${
            sessionStorage.getItem("access-token") ||
            localStorage.getItem("access-token")
        }`,
    },
});