import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ProductRegister.css";
import styles from './WaitingPage.module.css'
import { Footer, NavBar } from "../../../components/index";
import { productRegisterApi } from "../../../api/productApi";
import {productDetailApi } from "../../../api/productApi";

function WaitingPage() {
  const navigate = useNavigate();
  const { id } = useParams();

  const [product, setProduct] = useState({
    productSeq:"",
    productId: "",
    productName: "",
    productPrice: "",
    productThumbnail:"",
    productGroup: {
      productGroupName:"",
    }
  });
  /* 해당 seq에 맞는 Product 정보 먼저 가져오기 */
  useEffect(()=>{
    console.log("WaitingPage Seq: " + id);
    productDetailApi(id)
    .then((res)=>{
        console.log(res.data);
        setProduct(res.data);
        console.log("WatingPage : 가져온 제품정보: " + product)
    })
    .catch((err)=>{
        console.log(err.data)
    })
  }, [])

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState({
    // 파일 그 자체를 받을 state
    image_file: "",
    preview_URL: `${process.env.PUBLIC_URL}/img/default_img.png`,
  });

  const handleChangeFile = (event) => {
    setImgFile(event.target.files);

    setImgBase64([]);
    for (var i = 0; i < event.target.files.length; i++) {
      if (event.target.files[i]) {
        let reader = new FileReader();
        reader.readAsDataURL(event.target.files[i]); // 파일을 읽어서 버퍼에 저장중

        // 파일 상태업데이트
        reader.onloadend = () => {
          const base64 = reader.result;
          console.log(base64);
          if (base64) {
            var base64Sub = base64.toString();

            setImgBase64((imgBase64) => [...imgBase64, base64Sub]);
          }
        };
      }
    }
  };

    // 서버에 파일 & 제품정보 전송 : FormData()
    const onProductSubmitBtn = (e) => {
      // e.preventDefault();
      console.log("product: " + JSON.stringify(product))
  
      productRegisterApi(product)
      .then((res)=>{
        console.log("onSubmitBtn:" + JSON.stringify(res.data));
        console.log("success");
        
        // navigate("/manager/productList")
      })
      .catch((err)=>{
        console.log(JSON.stringify(err.data));
      })
    };

  return (
    <>
      <NavBar />
      <h4 className="page-title">대기화면 등록</h4>
      <div className="mainContent-wrapper">
        <div className="left-img">
          {/* <div className="element-wrapper"> */}
          { 
            imgFile.image_file === ""
            ?    <img
            className="preview-img" 
            alt="#" src={imgFile.preview_URL} />
            : null
          }
          {imgBase64.map((item) => {
            return (
              <div className="img-wrapper">
                <img src={item} alt="Frist Slide" />
              </div>
            );
          })}

          {/* 제품정보 */}
          <div className="product-info">
            <h5>품번 : </h5>
            {product.productId}
          </div>
          <div className="product-info">
            <h5>제품명 : </h5>
            {product.productName}
          </div>
          <div className="product-info">
            <h5>가격 : </h5>
            {product.productPrice}
          </div>
        </div>
        {/* right content START */}
        {/* <div className="styles.right-waiting-img">
          {imgFile.image_file === "" ? (
            <img className="preview-img" alt="#" src={imgFile.preview_URL} />
          ) : null}
          {imgBase64.map((item) => {
            return (
              <div className="img-wrapper">
                <img src={item} alt="Frist Slide" />
              </div>
            );
          })}
        </div> */}
        <div className="bottomContent-wrapper">
        <input
          className="img-btn"
          type="file"
          accept="image/*"
          id="file"
          onChange={handleChangeFile}
        />
        {/* <button onClick={deleteImage}>이미지 삭제</button> */}
        <button className="bottom-btn" onClick={()=> onProductSubmitBtn()}>
          업로드하기
        </button>
      </div>
      </div>

      <Footer />
    </>
  );
}

export default WaitingPage;
