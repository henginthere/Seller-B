import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

import Axios from "axios";
import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import { productRegisterApi } from "../../../api/productApi";
import axios from "axios";

function ProductRegister() {
  const [product, setProduct] = useState({
    product_id: "",
    product_name: "",
    product_price: "",
    product_line: "",
    // 사진 파일 부분 필드 추가 
    formData: "",
  });

  const { product_id, product_name, product_price, product_line } = product;

  const onChange = (e) => {
    const { value, name } = e.target;
    setProduct({
      ...product,
      [name]: value,
    });

    console.log(product_id);
  };

  // 서버에 파일 & 제품정보 전송 : FormData()
  const onProductSubmitBtn = async (e) => {
    const formData = new FormData(); // FormData객체 생성

    // Form객체에 파일값 추가 : append(key, value) or append(key, value, filename)
    formData.append("file", e.target.files[0]);
    console.log(formData);

    // const config = {
    //   Headers: {
    //     "content-type": "multipart/form-data",
    //   },
    // };

    // 제품 정보
    setProduct({
      ...product,
      formData: formData,
    })

    // axios.post('api', product, config);
  };

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  //const [imgFile, setImgFile] = useState(null); // 파일 그 자체를 받을 state
  const [imgFile, setImgFile] = useState({
    image_file: "",
    preview_URL: `${process.env.PUBLIC_URL}/img/default_img.png`,
  });

  const handleChangeFile = (event) => {
    console.log(event.target.files);
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
                <img src={item} alt="Frist Slide" />
              </div>
            );
          })}
        </div>
        <div className="right-input">
          <div className="input-ele">
            <p>품번</p>
            <input
              name="product_id"
              onChange={onChange}
              value={product_id}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>제품명</p>
            <input
              name="product_name"
              onChange={onChange}
              value={product_name}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>가격</p>
            <input
              name="product_price"
              onChange={onChange}
              value={product_price}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>제품군</p>
            <input
              name="product_line"
              onChange={onChange}
              value={product_line}
              variant="outlined"
            />
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
        <button className="bottom-btn" onClick={onProductSubmitBtn}>
          업로드하기
        </button>
      </div>
      <Footer />
    </>
  );
}

export default ProductRegister;
