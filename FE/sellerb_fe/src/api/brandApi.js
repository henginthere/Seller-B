import { api } from "./api";

// GET : 브랜드 목록
export const listBrandApi = async(success, fail) => {
    return await api.get('/brand/list').then(success).catch(fail);
}