import api from "./api";
import fileapi from "./fileapi";

// POST : 제품데이터 등록
export const productRegisterApi = async (product, success, fail) => {
  return await api.post("/product", product).then(success).catch(fail);
};

// POST : 제품 사진 등록
export const productImgRegisterApi = async (productImg, success, fail) => {
  return await fileapi
    .post("/file/product", productImg)
    .then(success)
    .catch(fail);
};

// GET : List : ProductList.js : 모든 제품군 '이름'을 가져옴
export const productGroupListApi = async (success, fail) => {
  // console.log("api 객체 : " + api)
  return await api.get("/product-group/list").then(success).catch(fail);
};

// GET : List : ProductOpion.js : 해당 제품군의 제품들 조회
export const productGroupItemsApi = async (group_name, success, fail) => {
  return await api.get(`/product/list/${group_name}`).then(success).catch(fail);
};

// GET : ProductDetail.js : 제품 상세 정보 조회
export const productDetailApi = async (product_seq, success, fail) => {
  // console.log("in productDetail API: " + product_seq);
  return await api.get(`/product/${product_seq}`).then(success).catch(fail);
};

// GET : WatingPage.js : 해당 제품의 대기화면 이미지
export const waitingPageApi = async (product_seq, success, fail) => {
  // console.log("in waitingPageAPi ; "+ product_seq)
  return await api
    .get(`/waiting-page/${product_seq}`)
    .then(success)
    .catch(fail);
};

// PUT : ProductEdit.js
export const productEditApi = async (product, success, fail) => {
  // console.log("in productEdit API: " + product.productSeq);
  // console.log("in productEdit API: " + JSON.stringify(product));

return await api
.put(`/product/${product.productSeq}`, product)
.then(success)
.catch(fail);
};


// POST : WaitingPage.js : 해당 제품의 대기화면 등록하기
export const registerWaitingPageApi = async (waitingPage, success, fail) => {
  return await api.post("/waiting-page", waitingPage).then(success).catch(fail);
};

// GET : ProductList.js
export const productSearchApi = async (productName, success, fail) => {
  return await api
    .get(`/product/name/${productName}`)
    .then(success)
    .catch(fail);
};

// Del : ProductDetail.js
export const productDelApi = async (productSeq, success, fail) => {
  return await api.delete(`/product/${productSeq}`).then(success).catch(fail);
};

// GET : ProductList.js : 처음 접속 시, 해당 브랜드의 모든 제품 불러오기
export const brandProductListApi = async (brandSeq, success, fail) => {
  // console.log("brandProductListAPi : " + brandSeq)
  return await api
    .get(`/product/list/brand/${brandSeq}`)
    .then(success)
    .catch(fail);
};
