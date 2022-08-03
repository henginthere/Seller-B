import { api } from "./api";
import axios from 'axios';

export const LoginApi = async (userInfo, success, fail) => {
    return await api.post("/auth/login")
}
