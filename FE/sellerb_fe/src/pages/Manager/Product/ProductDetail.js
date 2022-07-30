import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import { productDetailApi } from "../../../api/productApi";

function ProductDetail() {
  const { seq } = useParams();
  const [readOnly, setReadOnly] = useState(true)
  const [product, setProduct] = useState({
    // 가져왔다고 하고, 더미데이터로 테스트
    product_seq: "3",
    product_id: "abc",
    product_name: "스탠드형 에어컨",
    product_price: "1,300,000",
    // product_thumbnail : 서버에서 url로 받아옴
    product_thumbnail:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1T5-8wefzN-Nv1nUOwyhfYoh4js2cTgJpCw&usqp=CAU",
    product_line: "에어컨",
    reg_date: "2022-07-25",
  });

  /* 해당 seq에 맞는 Product 정보 먼저 가져오기 */
    // useEffect(()=>{
    //   productDetailApi(id)
    //   .then((res)=>{
    //       console.log(res.data);

    //       setProduct(res.data);
    //   })
    //   .catch((err)=>{
    //       console.log(err.data)
    //   })
    // })

  const navigate = useNavigate();
  const goWaitingPage = ()=>{
    navigate(`/manager/waitingPage/${product.product_seq}`); // 제품대기화면 페이지로 이동
  }

  const onEdit = ()=>{
    navigate(`/manager/productEdit/${product.product_seq}`);
  }

  return (
    <>
      <NavBar />
      <h4 className="page-title">제품 상세</h4>
      <div className="mainContent-wrapper">
        <div className="left-img">
          {/* img 자리 */}
          <img className="img-wrapper" alt="#" src={`${product.product_thumbnail}`} />
        </div>


        <div className="right-input">
          <div className="input-ele">
            <p>품번</p>
            <input
              name="product_id"
              value={product.product_id}
              variant="outlined"
              readOnly={readOnly ? false : true}
            />
          </div>
          <div className="input-ele">
            <p>제품명</p>
            <input
              name="product_name"
              value={product.product_name}
              variant="outlined"
              readOnly={readOnly ? false : true}
            />
          </div>
          <div className="input-ele">
            <p>가격</p>
            <input
              name="product_price"
              value={product.product_price}
              variant="outlined"
              readOnly={readOnly ? false : true}
            />
          </div>
          <div className="input-ele">
            <p>제품군</p>
            <input
              name="product_line"
              value={product.product_line}
              variant="outlined"
              readOnly={readOnly ? false : true}
            />
          </div>
        </div>
      </div>

      {/* Btn */}
    <div className="bottomContent-wrapper">
    <button className="bottom-btn" onClick={onEdit}>
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
