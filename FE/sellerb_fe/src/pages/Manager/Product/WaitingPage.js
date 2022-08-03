import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ProductRegister.css";
import styles from './WaitingPage.module.css'
import { Footer, NavBar } from "../../../components/index";
import { productRegisterApi } from "../../../api/productApi";

function WaitingPage() {
  const navigate = useNavigate();
  const { seq } = useParams();
  console.log(seq);

  const [product, setProduct] = useState({
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

  //   useEffect(()=>{
  //     productRegisterApi(seq)
  //     .then((res)=>{
  //         setProduct(res.data);
  //         console.log(res.data);
  //     })
  //     .catch((err)=>{

  //         console.log(err.data);
  //     })
  //   })

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState({
    // 파일 그 자체를 받을 state
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

  return (
    <>
      <NavBar />
      <h4 className="page-title">대기화면 등록</h4>
      <div className="mainContent-wrapper">
        <div className="left-img">
          {/* <div className="element-wrapper"> */}
          {/* img 자리 */}
          <img
            className="img-wrapper"
            alt="#"
            src={`${product.product_thumbnail}`}
          />

          {/* 제품정보 */}
          <div className="product-info">
            <h5>품번 : </h5>
            {product.product_id}
          </div>
          <div className="product-info">
            <h5>제품명 : </h5>
            {product.product_name}
          </div>
          <div className="product-info">
            <h5>가격 : </h5>
            {product.product_price}
          </div>
        </div>
        {/* right content START */}
        <div className="styles.right-waiting-img">
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
        </div>
      </div>

      <Footer />
    </>
  );
}

export default WaitingPage;
