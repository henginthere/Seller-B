import baseAxios from "axios";

// init base API 
const fileapi = baseAxios.create({
    baseURL: "https://i7d105.p.ssafy.io/api/",
    headers: {
      "Content-Type": `multipart/form-data`,
    },
  });
  
  fileapi.interceptors.request.use((config) => {
    config.headers.Authorization = `Bearer ${sessionStorage.getItem(
      "accessToken",
    )}`;
    return config;
  });
  
  export default fileapi;