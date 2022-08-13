import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import { productEditApi, productDetailApi } from "../../../api/productApi";

function ProductEdit() {
  const { seq } = useParams();
  const [readOnly, setReadOnly] = useState(true)
  const [product, setProduct] = useState({
    productSeq: "",
    productId: "",
    productName: "",
    productPrice: "",
    // product_thumbnail : 서버에서 url로 받아옴
    productThumbnail:
      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1T5-8wefzN-Nv1nUOwyhfYoh4js2cTgJpCw&usqp=CAU",
    productGroup: "",
  });


  /* 해당 seq에 맞는 Product 정보 먼저 가져오기 */
  useEffect(()=>{
    console.log("in useEffect: " + seq);

    productDetailApi(seq)
    .then((res)=>{
        console.log(res.data);

        setProduct(res.data);
    })
    .catch((err)=>{
        console.log(err.data)
    })
  }, [])
    // useEffect(()=>{
    //   productEditApi(id)
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

  const onEditCompleteBtn = ()=>{

    productEditApi(product)
    .then((res)=>{
        setProduct(res);

        console.log(res.data);
    })
    .catch((err)=>{
        console.log(err.data);
    })


    navigate('/manager/productList');
  }

 const onBackBtn = ()=>{
    navigate(`/manager/ProductDetail/${product.product_seq}`);
 }

  const { productSeq, productId, productName, productPrice, productThumbnail, productGroup } = product;
  const onChange = (e) => {
    const { value, name } = e.target;
    setProduct({
      ...product,
      [name]: value,
    });

    console.log(productId);
  };

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
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

  const onResetFile = ()=>{
    setProduct({
        ...product,
        product_thumbnail: "",     
  })
  }

  return (
    <>
      <NavBar />
      <h4 className="page-title">제품 상세</h4>
      <div className="mainContent-wrapper">
        <div className="left-img">
          {/* img 자리 */}
          { product.product_thumbnail
          ? <img className="img-wrapper" alt="#" src={`${product.product_thumbnail}`} />
            :   
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
        <button onClick={onResetFile}>
            사진 초기화
        </button>
          <input
          className="img-btn"
          type="file"
          accept="image/*"
          id="file"
          onChange={handleChangeFile}
        />
        </div>


        <div className="right-input">
          <div className="input-ele">
            <p>품번</p>
            <input
              name="productId"
              value={product.productId}
              onChange={onChange}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>제품명</p>
            <input
              name="productName"
              value={product.productName}
              onChange={onChange}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>가격</p>
            <input
              name="productPrice"
              value={product.productPrice}
              onChange={onChange}
              variant="outlined"
            />
          </div>
          <div className="input-ele">
            <p>제품군</p>
            <input
              name="productGroup"
              value={product.productGroup.productGroupName}
              onChange={onChange}
              variant="outlined"
            />
          </div>
        </div>
      </div>

      {/* Btn */}
    <div className="bottomContent-wrapper">
        <button className="bottom-btn" onClick={onEditCompleteBtn}>
            수정완료
        </button>
        <button className="bottom-btn" onClick={onBackBtn}>
            돌아가기
        </button>
        
        <button className="bottom-btn" onClick={goWaitingPage}>
            제품 대기화면 보기
        </button>
    </div>

      <Footer />
    </>
  );
}

export default ProductEdit;
