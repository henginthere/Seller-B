import { api } from './api';

// POST : 제품 등록
export const productRegisterApi = async (product, success, fail) => {
    return await api.post("/product", product).then(success).catch(fail);
}

// GET : List : ProductList.js : 모든 제품군 '이름'을 가져옴
export const productGroupListApi = async (success, fail) =>{
    return await api.get("/product-group/list").then(success).catch(fail);
}

// GET : List : ProductOpion.js : 해당 제품군의 제품들 조회 
export const productGroupItemsApi = async (group_name, success, fail) =>{
    return await api.get(`/product/list/${group_name}`).then(success).catch(fail);
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


