import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ProductRegister.css";
import "./ProductDetail.css";
import { Footer, NavBar } from "../../../components/index";
import { productDetailApi, productDelApi } from "../../../api/productApi";
import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";
import { DangerMediButton } from "../../../components/Common/DangerMediButton";

function ProductDetail() {
  const { seq } = useParams();
  const [readOnly, setReadOnly] = useState(true);
  const [product, setProduct] = useState({
    productSeq: "",
    productGroupName: "",
    productGroupSeq: "",
    productId: 0,
    productName: "",
    productPrice: 0,
    productManual: "",
    productThumbnail: "",
  });

  /* 해당 seq에 맞는 Product 정보 먼저 가져오기 */
  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
    productDetailApi(seq)
      .then((res) => {
        console.log(JSON.stringify(res.data));
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

  const onDeleteBtn = () => {
    productDelApi(seq)
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log("Error");
      });

    navigate("/manager/productList");
  };

  return (
    <>
      <NavBar />
      <div className='register-main-wrapper'>
        <div className='register-sub-wrapper'>
          <div className='register-area-wrapper'>
            <div className='left-img'>
              <img
                className='product-register-img'
                alt='#'
                src={product.productThumbnail}
              />
            </div>

            {/* 오른쪽 영역 */}
            <div className='right-input'>
              <div className='input-sub-content-wrapper'>
                <div className='input-ele'>
                  <p>품번</p>
                  <div className='product-id-input-wrapper'>
                    <input
                      className='product-id-input'
                      name='productId'
                      value={product.productId}
                      variant='outlined'
                    />
                  </div>
                </div>
                <div className='input-ele'>
                  <p>제품명</p>
                  <div className='product-id-input-wrapper'>
                    <input
                      name='productName'
                      value={product.productName}
                      variant='outlined'
                    />
                  </div>
                </div>
                <div className='input-ele'>
                  <p>가격</p>
                  <div className='product-id-input-wrapper'>
                    <input
                      className='product-id-input'
                      name='productPrice'
                      value={product.productPrice}
                      variant='outlined'
                    />
                  </div>
                </div>
                <div className='input-ele'>
                  <p>제품메뉴얼</p>
                  <div className='product-id-input-wrapper'>
                    <div className='product-detail-manual-wrapper'>
                      <a
                        className='product-detail-manual-link'
                        href={product.productManual}
                      >
                        {product.productManual.slice(0, 30)}...
                      </a>
                    </div>
                  </div>
                </div>
                <div className='input-ele'>
                  <p>제품군</p>
                  <div className='product-id-input-wrapper'>
                    <input
                      className='product-id-input'
                      name='productGroupName'
                      value={product.productGroupName}
                      variant='outlined'
                      readOnly={readOnly ? false : true}
                    />
                  </div>
                </div>

                {/* 하단 버튼 */}
                <div className='product-register-medi-btn'>
                  <MediButton label='수정하기' onClick={onEditBtn} />
                  <MediButton label='대기화면 등록' onClick={goWaitingPage} />
                  <DangerMediButton label='삭제하기' onClick={onDeleteBtn} />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <Footer />
    </>
  );
}

export default ProductDetail;
