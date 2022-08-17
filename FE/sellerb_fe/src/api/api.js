import baseAxios from "axios";

const api = baseAxios.create({
  baseURL: "https://i7d105.p.ssafy.io/api",
  headers: {
    Authorization: "Bearer" + sessionStorage.getItem("accessToken"),
  },
});

api.interceptors.request.use((config) => {
  config.headers.Authorization = `Bearer ${sessionStorage.getItem(
    "accessToken",
  )}`;
  return config;
});

export default api;
