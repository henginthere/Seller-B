import { api } from './api';

// POST : 
export const productRegisterApi = async (product, success, fail) => {
    return await api.get("/product-line").then(success).catch(fail);
}

// GET : List : ProductList.js : 모든 제품군 '이름'을 가져옴
export const productLineListApi = async (success, fail) =>{
    return await api.get("/product-line/list").then(success).catch(fail);
}

// GET : List : ProductOpion.js : 해당 제품군의 제품들 조회 
export const productLineItemsApi = async (line_name, success, fail) =>{
    return await api.get(`/product-line/${line_name}`).then(success).catch(fail);
}

// PUT : ProductEdit.js
// export const productEditApi = async ( )

// POST : ProductRegister.js
// export cont productRegisterApi = async ( )