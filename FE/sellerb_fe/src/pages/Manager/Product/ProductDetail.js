import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import { productDetailApi } from "../../../api/productApi";

function ProductDetail() {
  const { seq } = useParams();
  const [readOnly, setReadOnly] = useState(true)
  const [product, setProduct] = useState({
    productSeq:"",
    productId: "",
    productName: "",
    productPrice: "",
    // product_thumbnail : 서버에서 url로 받아옴
    productThumbnailUrl:"",
    productGroup: {
      productGroupName:"",
    }
  });

  /* 해당 seq에 맞는 Product 정보 먼저 가져오기 */
    useEffect(()=>{

      productDetailApi(seq)
      .then((res)=>{
          console.log(res.data);
          setProduct(res.data);
      })
      .catch((err)=>{
          console.log(err.data)
      })
    }, [])

  const navigate = useNavigate();
  const goWaitingPage = ()=>{
    navigate(`/manager/waitingPage/${product.productSeq}`); // 제품대기화면 페이지로 이동
  }

  const onEdit = ()=>{
    navigate(`/manager/productEdit/${product.productSeq}`);
  }

  return (
    <>
      <NavBar />
      <h4 className="page-title">제품 상세</h4>
      <div className="mainContent-wrapper">
        <div className="left-img">
          {/* img 자리 */}
          <img className="img-wrapper" alt="#" src={product.productThumbnail} />
        </div>


        <div className="right-input">
          <div className="input-ele">
            <p>품번</p>
            <input
              name="productId"
              value={product.productId}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>제품명</p>
            <input
              name="product_name"
              value={product.productName}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>가격</p>
            <input
              name="product_price"
              value={product.productPrice}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>제품군</p>
            <input
              name="product_line"
              value={product.productGroupName}
              variant="outlined"
              readOnly={readOnly ? false : true}
            />
          </div>
        </div>
      </div>

      {/* Btn */}
    <div className="bottomContent-wrapper">
    <button className="bottom-btn" onClick={()=>
      navigate(`/manager/productEdit/${seq}`)}>
            수정하기
        </button>
        <button className="bottom-btn" onClick={goWaitingPage}>
            제품 대기화면 보기
        </button>
    </div>

      <Footer />
    </>
  );
}

export default ProductDetail;
