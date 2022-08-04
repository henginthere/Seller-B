import { api } from "./api";
import axios from 'axios';

export const LoginApi = async (userInfo, success, fail) => {
    return await api.post("/auth/login").then(success).catch(fail);
}

export const managerMyPageApi = async (manager_seq, success, fail) => {
    return await api.get(`/manager/${manager_seq}`).then(success).catch(fail);
}