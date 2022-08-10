import React, { useState, useEffect } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";

import Axios from "axios";
import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import { productRegisterApi, productGroupListApi } from "../../../api/productApi";
import axios from "axios";

function ProductRegister() {
  const navigate = useNavigate();
  
  const [product, setProduct] = useState({
    productGroupName: "",
    productId: "",
    productName: "",
    productPrice: "",
    productManual:"",
    productThumbnail:"",
  });

  const [groupList, setGroupList] = useState([]);
  const [managerBrand, setManagerBrand] = useState(
    localStorage.getItem("brandNameKor")
  );
  const { productGroupName, productId, 
          productName, productPrice, 
          productManual, productThumbnail } = product;

  const onChange = (e) => {
    const { value, name } = e.target;
    console.log("value:"  + value);
    console.log("name:" + name);
    setProduct({
      ...product,
      [name]: value,
    });

    console.log(productId);
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

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState({
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

  const deleteImage = () => {
    setImgFile({
      image_file: "",
      preview_URL:  `${process.env.PUBLIC_URL}/img/default_img.png`,
    });

    setImgBase64("");
  };

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

  return (
    <>
      <NavBar />
      <h4 className="page-title">제품 등록</h4>
      <div className="mainContent-wrapper">
        <div className="left-img">

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
            <select onChange={onChange} value={productGroupName} name="productGroupName">
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
      <Footer />
    </>
  );
}

export default ProductRegister;
