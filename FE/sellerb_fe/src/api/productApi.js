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

// GET : ProductDetail.js : 제품 상세 정보 조회
export const productDetailApi = async(product_seq, success, fail) => {
    return await api.get(`/product/${product_seq}`).then(success).catch(fail);
}

// GET : WatingPage.js : 해당 제품의 대기화면 이미지 
export const waitingPageApi = async(product_seq, success, fail)=>{
    return await api.get(`/waiting-page/${product_seq}`).then(success).catch(fail);
}

// PUT : ProductEdit.js
export const productEditApi = async (product_seq, success, fail) =>{
    return await api.put(`/product/${product_seq}`).then(success).catch(fail);
}


