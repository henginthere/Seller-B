import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ProductRegister.css";
import axios from "axios";
import styles from "./WaitingPage.module.css";
import { Footer, NavBar } from "../../../components/index";
import {
  productRegisterApi,
  registerWaitingPageApi,
  waitingPageApi,
} from "../../../api/productApi";
import { productDetailApi } from "../../../api/productApi";
import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";
import { BackSmallButton } from "../../../components/Common/BackSmallButton";

function WaitingPage() {
  const navigate = useNavigate();
  const { id } = useParams();

  // 이미지 등록 관련 코드
  const [resImg, setResImg] = useState("");
  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState("");
  const [previewUrl, setPreviewUrl] = useState(
    `${process.env.PUBLIC_URL}/img/default_img.png`
  );

  // 대기화면
  const [product, setProduct] = useState({
    productSeq: "",
    productId: "",
    productName: "",
    productPrice: "",
    customerWaitingPageImage: "",
  });

  const [waitingImg, setWaitingImg] = useState("");
  const [waitingMent, setWaitingMent] = useState("");
  const [inputWaitingMent, setInputWaitingMent] = useState("");

  /* 해당 seq에 맞는 Product 정보 먼저 가져오기 */
  useEffect(() => {
    console.log("WaitingPage Seq: " + id);
    productDetailApi(id)
      .then((res) => {
        console.log(res.data);
        setProduct(res.data);
        console.log("가져온 제품정보: " + product);
      })
      .catch((err) => {
        console.log(err.data);
      });
  }, []);

  useEffect(() => {
    waitingPageApi(id)
      .then((res) => {
        console.log("in UseEffect waitingPageApi: " + JSON.stringify(res.data));
        setWaitingImg(res.data.customerWaitingPageImage);
        setWaitingMent(res.data.customerWaitingPageMent);
      })
      .catch((err) => {
        console.log("가져온 대기화면 정보 없음");
      });
  }, []);

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

  const onChangeInputMent = (e)=>{
    console.log("on Change Ment :" + e.target.value);

    setInputWaitingMent(e.target.value);
  }

  const onChangeMent = (e)=>{
    console.log("on Change Ment :" + e.target.value);

    setWaitingMent(e.target.value);
  }

  const onResetFile = () => {
    setImgFile("");
    setWaitingImg("");
  };

  const onRegisterBtn = () => {

    console.log("등록할 멘트 : " + waitingMent)
    let infoMent = "";
    if(inputWaitingMent === ""){
      infoMent = waitingMent;
    }else{
      infoMent = inputWaitingMent;
    }
    
    const Info = {
      productSeq: product.productSeq,
      customerWaitingPageMent: infoMent,
      customerWaitingPageImage: resImg
    };

    console.log("등록 전, WaitingPage Info: " + JSON.stringify(Info));

    registerWaitingPageApi(Info)
      .then((res) => {
        console.log(res.data);
        navigate(`/manager/productDetail/${id}`);
      })
      .catch((err) => {
        console.log(err.data);
      });
  };

  const onImgRegisterBtn = async () => {
    const fd = new FormData();
    // imgFile의 파일들을 읽어와서, file이라는 이름으로 저장하기
    // -> FormData에 file이라는 이름의 파일 배열이 들어감
    Object.values(imgFile).forEach((file) => fd.append("data", file));

    // fd.append(
    //   "comment",)
    console.log("보낼 fd: " + fd);

    await axios
      .post("https://i7d105.p.ssafy.io/api/file/waiting-page", fd, {
        header: {
          "Content-Type": `multipart/form-data`,
        },
      })
      .then((response) => {
        if (response.data) {
          console.log(response.data);
          setResImg(response.data);
        }
      })
      .catch((error) => {
        console.log("Error");
      });
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
              {
                // 기존이미지도 없고, 새로 올린 파일도 없으면, Preview 이미지  
                waitingImg === "" && imgFile === ""
                ? (
                  <img
                    className="product-register-img"
                    alt="#"
                    src={previewUrl}
                  />
                  ) 
              : ( 
                waitingImg !=="" // 만약에 기존등록이미지가 있으면 고걸 보여줘
                  ? (   
                    <img
                      className="product-register-img"
                      alt="###"
                      src={waitingImg}
                    />
                    )
                : (
                  imgBase64.map((item) => {
                    return (
                      <div>
                        <img
                          className="product-register-img"
                          src={item}
                          alt="First Slide"
                        />
                      </div>
                    );
                  })
                )
                
              )}
              
              <div className="product-img-bottom-wrapper">
                <input
                  className="img-btn"
                  multiple="multiple"
                  type="file"
                  accept="image/*"
                  id="file"
                  onChange={onHandleChangeFile}
                />
                <div className="product-register-small-btn">
                  <SmallButton label="업로드 완료" onClick={onImgRegisterBtn} />
                  <BackSmallButton label="이미지 초기화" onClick={onResetFile} />
                </div>
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
                {/* <div className="input-ele">
                  <p>제품군</p>
                  <div className="product-id-input-wrapper">
                    <input
                      className="product-id-input"
                      name="productGroup"
                      // value={product.productGroup.productGroupName}
                      variant="outlined"
                    />
                  </div>
                </div> */}
                {/*  */}
                <div className="input-ele">
                  <p>대기화면 멘트</p>
                  <div className="product-id-input-wrapper">
                    {
                      waitingMent === "" 
                      ? <input
                        className="product-id-input"
                        onChange={onChangeInputMent}
                        // name="productGroup"
                        value={inputWaitingMent}
                        placeholder="출력 멘트를 입력해주세요"
                        variant="outlined"
                      />
                    : <input
                        className="product-id-input"
                        name="productGroup"
                        value={waitingMent}
                        onChange={onChangeMent}
                        variant="outlined"
                      />
                    }
                    
                  </div>
                </div>
                {/*  */}
                <div className="product-register-medi-btn">
                  <MediButton label="등록하기" onClick={onRegisterBtn} />
                  {/* <MediButton label="이미지 초기화" onClick={onResetFile} /> */}
                </div>
              </div>
            </div>
            {/*  */}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default WaitingPage;