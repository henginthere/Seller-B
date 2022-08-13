import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ProductRegister.css";
import axios from "axios";
import styles from './WaitingPage.module.css'
import { Footer, NavBar } from "../../../components/index";
import { productRegisterApi, registerWaitingPageApi, waitingPageApi } from "../../../api/productApi";
import {productDetailApi } from "../../../api/productApi";
import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";

function WaitingPage() {
  const navigate = useNavigate();
  const { id } = useParams();

  // 이미지 등록 관련 코드
  const [resImg, setResImg] = useState("");
  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState("");
  const [previewUrl, setPreviewUrl] = useState(`${process.env.PUBLIC_URL}/img/default_img.png`);

  // 대기화면
  const [product, setProduct] = useState({
    productSeq:"",
    productId: "",
    productName: "",
    productPrice: ""
  });

  const [waitingImg, setWaitingImg] = useState("");
  const [waitingMent, setWaitingMent] = useState("");


  /* 해당 seq에 맞는 Product 정보 먼저 가져오기 */
  useEffect(()=>{
    console.log("WaitingPage Seq: " + id);
    productDetailApi(id)
    .then((res)=>{
        console.log(res.data);
        setProduct(res.data);
        console.log("가져온 제품정보: " + product)
    })
    .catch((err)=>{
        console.log(err.data)
    })
  }, [])

  useEffect(()=>{
    waitingPageApi(product.productSeq)
    .then((res)=>{
      console.log("웨이팅 페이지 API: " + JSON.stringify(res.data));
      // setWaitingImg(res.data.customerWaitingPageImage);
      setImgFile(res.data.customerWaitingPageImage);
    })
    .catch((err)=>{

    })
  }, [])

  const onHandleChangeFile = (event) => {
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

  const onResetFile = () => {

  };

    const onRegisterBtn = () => {
      console.log("in RegisterBtn API : " + resImg)
  
      // 선택한 그룹군에 대해, productGroupSeq찾기 
      // console.log("제출 전 seq : " + selectSeq)
  
      const Info = {
        productSeq: product.productSeq,
        customerWaitingPageMent: "멘트준비중",
        customerWaitingPageImageUrl: resImg
      };
  
      console.log("등록 전 Product: " + JSON.stringify(Info))
  
      registerWaitingPageApi(Info)
      .then((res)=>{
        console.log(res.data);
      })
      .catch((err)=>{
        console.log(err.data);
      })
    };

    const onImgRegisterBtn = async() => {
      const fd = new FormData(); 
      // imgFile의 파일들을 읽어와서, file이라는 이름으로 저장하기 
      // -> FormData에 file이라는 이름의 파일 배열이 들어감 
      Object.values(imgFile).forEach((file) => fd.append("data", file));
  
      // fd.append(
      //   "comment",)
      console.log("보낼 fd: " + fd);
  
      await axios.post('https://i7d105.p.ssafy.io/api/file/waiting-page', fd, {
        header: {
          "Content-Type": `multipart/form-data`
        }
      })
      .then((response) => {
        if(response.data){
          console.log(response.data)
          setResImg(response.data);
        }
      })
      .catch((error)=>{
        console.log("Error");
      })
    };

  return (
    <>
      <NavBar />
      {/* <h4 className="page-title">대기화면 등록</h4> */}
    <div className="register-main-wrapper">
      <div className="register-sub-wrapper">
        <div className="register-area-wrapper">
          {/*  */}
          <div className="left-img">
            {imgFile === "" ? (
                <img
                  className="product-register-img"
                  alt="#"
                  src={previewUrl}
                />
              ) : null}
              {imgBase64.map((item) => {
                return (
                  <div>
                    <img
                      className="product-register-img"
                      src={item}
                      alt="First Slide"
                    />
                  </div>
                );
              })}
              <div className="product-img-bottom-wrapper">
                <input
                  className="img-btn"
                  multiple="multiple"
                  type="file"
                  accept="image/*"
                  id="file"
                  onChange={onHandleChangeFile}
                />

              </div>

          </div>
          {/*  */}
          <div className="right-input">
              <div className="input-sub-content-wrapper">
                {/*  */}
                <div className="input-ele">
                  <p>품번</p>
                  <div className="product-id-input-wrapper">
                    <input
                      className="product-id-input"
                      name="productId"
                      defaultValue={product.productId}
                      variant="outlined"
                    />
                  </div>
                </div>
                {/*  */}
                <div className="input-ele">
                  <p>제품명</p>
                  <div className="product-id-input-wrapper">
                    <input
                      className="product-id-input"
                      name="productName"
                      value={product.productName}
                      variant="outlined"
                    />
                  </div>
                </div>
                {/*  */}
                <div className="input-ele">
                  <p>가격</p>
                  <div className="product-id-input-wrapper">
                    <input
                      className="product-id-input"
                      name="productPrice"
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
                      name="productGroup"
                      // value={product.productGroup.productGroupName}
                      variant="outlined"
                    />
                  </div>
                </div>
              {/*  */}
              <div className="product-register-medi-btn">
                <MediButton label="대기화면 등록" onClick={onImgRegisterBtn} />
                <MediButton label="이미지 초기화" onClick={onResetFile} />
              </div>             
              </div>
            </div>
            {/*  */}
        </div>
      </div>
    </div>



{/* 
      <div className="mainContent-wrapper">
        <div className="left-img">
          { 
            imgFile === ""
            ?    <img
            className="preview-img" 
            alt="#" src={previewUrl} />
            : <img alt="#" src={imgFile} />
          }
          {imgBase64.map((item) => {
            return (
              <div className="img-wrapper">
                <img src={item} alt="Frist Slide" />
              </div>
            );
          })} */}
{/* 
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
        </div> */}
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
        {/* <div className="bottomContent-wrapper">
        <input
          className="img-btn"
          type="file"
          accept="image/*"
          id="file"
          onChange={handleChangeFile}
        />
        <button onClick={onImgRegisterBtn}>이미지등록하기</button>
        <button onClick={onRegisterBtn }>제품 등록하기</button>
      </div>
      </div> */}

      <Footer />
    </>
  );
}

export default WaitingPage;