import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import { productDetailApi, productDelApi } from "../../../api/productApi";
import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";

function ProductDetail() {
  const { seq } = useParams();
  const [readOnly, setReadOnly] = useState(true);
  const [product, setProduct] = useState({
    productSeq: "",
    productId: "",
    productName: "",
    productPrice: "",
    // product_thumbnail : 서버에서 url로 받아옴
    productThumbnailUrl: "",
    productGroup: {
      productGroupName: "",
    },
  });

  /* 해당 seq에 맞는 Product 정보 먼저 가져오기 */
  useEffect(() => {
    productDetailApi(seq)
      .then((res) => {
        console.log(res.data);
        setProduct(res.data);
      })
      .catch((err) => {
        console.log(err.data);
      });
  }, []);

  const navigate = useNavigate();
  const goWaitingPage = () => {
    navigate(`/manager/waitingPage/${product.productSeq}`); // 제품대기화면 페이지로 이동
  };

  const onEditBtn = () => {
    navigate(`/manager/productEdit/${product.productSeq}`);
  };

  const onDeleteBtn = () =>{

    productDelApi(seq)
    .then((res)=>{
      console.log(res.data);
    })
    .catch((err)=>{
      console.log("Error");
    })

    navigate("/manager/productList");
  }

  return (
    <>
      <NavBar />
      {/* <h4 className="page-title">제품 상세</h4> */}

      <div className="register-main-wrapper">
        <div className="register-sub-wrapper">
          <div className="register-area-wrapper">
            <div className="left-img">
              <img
                className="product-register-img"
                alt="#"
                src={product.productThumbnail}
              />
            </div>

            {/* 오른쪽 영역 */}
            <div className="right-input">
              
              <div className="input-sub-content-wrapper">
                {/*  */}
                <div className="input-ele">
                  <p>품번</p>
                  <div className="product-id-input-wrapper">
                    <input
                      className="product-id-input"
                      name="productId"
                      value={product.productId}
                      variant="outlined"
                    />
                  </div>
                </div>
                {/*  */}
                <div className="input-ele">
                  <p>제품명</p>
                  <div className="product-id-input-wrapper">
                    <input
                      name="product_name"
                      value={product.productName}
                      variant="outlined"
                    />
                  </div>
                </div>
                {/* */}
                <div className="input-ele">
                  <p>가격</p>
                  <div className="product-id-input-wrapper">
                    <input
                      className="product-id-input"
                      name="product_price"
                      value={product.productPrice}
                      variant="outlined"
                    />
                  </div>
                </div>
                {/*  */}
                <div className="input-ele">
                  <p>제품군</p>
                  <div className="product-id-input-wrapper">
                    <input
                      className="product-id-input"
                      name="product_line"
                      value={product.productGroupName}
                      variant="outlined"
                      readOnly={readOnly ? false : true}
                    />
                  </div>
                </div>

                {/* 하단 버튼 */}
              <div className="product-register-medi-btn">
                <MediButton label="수정하기" onClick={onEditBtn} />
                <MediButton label="삭제하기" onClick={onDeleteBtn} />
                <MediButton label="대기화면 등록" onClick={goWaitingPage} />
              </div>
              {/* <div className="product-register-medi-btn">
                
              </div> */}
                {/* <button
                  className="bottom-btn"
                  onClick={() => navigate(`/manager/productEdit/${seq}`)}>수정하기
                </button>
                <button className="bottom-btn" onClick={goWaitingPage}>
                  제품 대기화면 보기
                </button> */}
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* <div className="mainContent-wrapper">
        <div className="left-img">
  
          <img cclassName="product-register-img" alt="#" src={product.productThumbnail} />
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
      </div> */}

      {/* Btn */}
      {/* <div className="bottomContent-wrapper">
    <button className="bottom-btn" onClick={()=>
      navigate(`/manager/productEdit/${seq}`)}>
            수정하기
        </button>
        <button className="bottom-btn" onClick={goWaitingPage}>
            제품 대기화면 보기
        </button>
    </div> */}

      <Footer />
    </>
  );
}

export default ProductDetail;
