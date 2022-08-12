import React, { useState, useEffect } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";

import axios from "axios";
import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import {
  productRegisterApi,
  productGroupListApi,
  productImgRegisterApi,
} from "../../../api/productApi";

function ProductRegister() {
  const navigate = useNavigate();
  const [resImg, setResImg] = useState("");
  const [product, setProduct] = useState({
    productGroupName: "",
    productId: "",
    productName: "",
    productPrice: "",
    productManual: "",
    productThumbnail: resImg
  });

  const [groupList, setGroupList] = useState([]);
  const [managerBrand, setManagerBrand] = useState(
    sessionStorage.getItem("brandNameKor")
  );
  const {
    productGroupName,
    productId,
    productName,
    productPrice,
    productManual,
    productThumbnail,
  } = product;

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState("");
  const [previewUrl, setPreviewUrl] = useState(
    `${process.env.PUBLIC_URL}/img/default_img.png`
  );

 

  // 매니저가 속한 브랜드의 제품군 리스트 받아오기
  useEffect(() => {
    productGroupListApi()
      .then((res) => {
        setGroupList(res.data); // groupList
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const onChange = (e) => {
    const { value, name } = e.target;
    console.log("value:" + value);
    console.log("name:" + name);
    setProduct({
      ...product,
      [name]: value,
    });
    console.log(productId);
  };

  // 서버에 파일 & 제품정보 전송 : FormData()
  // const onProductSubmitBtn = (e) => {
  //  const formData = new FormData(); // FormData객체 생성

  //   // Form객체에 파일값 추가 : append(key, value) or append(key, value, filename)
  //   formData.append("file", e.target.files[0]);
  //   setProduct({
  //     ...product,
  //     productThumbnail: formData,
  //   });

  //   console.log("보낼 product :" + JSON.stringify(product))

  //   productRegisterApi(product)
  //     .then((res) => {
  //       console.log("onSubmitBtn:" + JSON.stringify(res.data));
  //       console.log("success");
  //     })
  //     .catch((err) => {
  //       console.log(JSON.stringify(err.data));
  //     });
  // };

  // 이미지 파일을 업로드하면, 실행될 함수
  const onHandleChangeFile = (event) => {
    console.log(event.target.files);
    setImgFile(event.target.files);
    // const file = event.target.files;

    // 미리보기 state
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
            // 변환해서 미리보기 이미지에 넣어주는 부분
            var base64Sub = base64.toString();
            setImgBase64((imgBase64) => [...imgBase64, base64Sub]);
          }
        };
      }
    }
  };

  const deleteImage = () => {
    setImgFile({
      image_file: "",
      preview_URL: `${process.env.PUBLIC_URL}/img/default_img.png`,
    });
    setImgBase64("");
  };

  const onRegisterBtn = () => {
    console.log("in RegisterBtn API : " + resImg)
    const Info = {
      productGroupName : product.productGroupName,
      productId : product.productId,
      productName: product.productName,
      productPrice: product.productPrice,
      productManual: product.productManual,
      productThumbnailUrl : resImg
    };

    console.log("등록 전 Product: " + JSON.stringify(Info))

    productRegisterApi(Info)
    .then((res)=>{
      console.log(res.data);
    })
    .catch((err)=>{
      console.log("Error");
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

    await axios.post('https://i7d105.p.ssafy.io/api/file', fd, {
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


    // productImgRegisterApi(fd)
    // .then((res)=>{
    //   console.log("받은 URL: " + res.data);
    // })
    // .catch((err)=>{
    //   console.log("Error")
    // })
  };
  

  return (
    <>
      <NavBar />
      <h4 className="page-title">제품 등록</h4>
      <div className="mainContent-wrapper">
        <div className="left-img">
          {imgFile === "" ? (
            <img className="preview-img" alt="#" src={previewUrl} />
          ) : null}
          {imgBase64.map((item) => {
            return (
              <div className="img-wrapper">
                <img src={item} alt="First Slide" />
              </div>
            );
          })}
        </div>
        <div className="right-input">
          <div className="input-ele">
            <p>품번</p>
            <input
              name="productId"
              onChange={onChange}
              value={productId}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>제품명</p>
            <input
              name="productName"
              onChange={onChange}
              value={productName}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>가격</p>
            <input
              name="productPrice"
              onChange={onChange}
              value={productPrice}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>제품군</p>
            <select
              onChange={onChange}
              value={productGroupName}
              name="productGroupName"
            >
              <option value=""></option>
              {groupList.map((option) =>
                option.brandName === managerBrand ? (
                  <option>{option.productGroupName}</option>
                ) : (
                  ""
                )
              )}
            </select>
          </div>
        </div>
      </div>

      <div className="bottomContent-wrapper">
        <input
          className="img-btn"
          multiple="multiple"
          type="file"
          accept="image/*"
          id="file"
          onChange={onHandleChangeFile}
        />
        {/* <button onClick={deleteImage}>이미지 삭제</button> */}
        {/* <button className="bottom-btn" onCanPlay={}>
          업로드하기
        </button> */}
        <button onClick={onImgRegisterBtn}>이미지등록하기</button>
        <button onClick={onRegisterBtn }>제품 등록하기</button>
      </div>
      <Footer />
    </>
  );
}

export default ProductRegister;
