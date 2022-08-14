import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import axios from "axios";

import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import { productEditApi, productDetailApi } from "../../../api/productApi";
import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";

function ProductEdit() {
  const { seq } = useParams();
  const navigate = useNavigate();
  const [readOnly, setReadOnly] = useState(true);
  const [resImg, setResImg] = useState("");
  const [product, setProduct] = useState({
    productSeq: "",
    productId: "",
    productName: "",
    productPrice: "",
    // product_thumbnail : 서버에서 url로 받아옴
    productThumbnail: "",
    productGroup: {
      productGroupName: "",
    },
  });

  const {
    productId,
    productName,
    productPrice,
    productGroup,
    productGroupName,
  } = product;

  const [imgBase64, setImgBase64] = useState([]);
  const [imgFile, setImgFile] = useState("");
  const [previewUrl, setPreviewUrl] = useState(
    `${process.env.PUBLIC_URL}/img/default_img.png`
  );

  /* 해당 seq에 맞는 Product 정보 먼저 가져오기 */
  useEffect(() => {
    console.log("product Edit Seq: " + seq);
    productDetailApi(seq)
      .then((res) => {
        console.log(res.data);
        setProduct(res.data);
        console.log("수정된 제품정보: " + product);
      })
      .catch((err) => {
        console.log(err.data);
      });
  }, []);

  const onEditCompleteBtn = () => {
    const editData = {
      productSeq: seq,
      productGroupName: product.productGroup.productGroupName,
      productId: product.productId,
      productName: product.productName,
      productPrice: product.productPrice,
      productManual: "",
      productThumbnail: "",
    };

    productEditApi(editData)
      .then((res) => {
        console.log("Edit Success");
      })
      .catch((err) => {
        console.log(err.data);
      });

    navigate("/manager/productList");
  };

  const onHandleChangeFile = (event) => {
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

  const onImgRegisterBtn = async () => {
    const fd = new FormData();
    // imgFile의 파일들을 읽어와서, file이라는 이름으로 저장하기
    // -> FormData에 file이라는 이름의 파일 배열이 들어감
    Object.values(imgFile).forEach((file) => fd.append("data", file));

    // fd.append(
    //   "comment",)
    console.log("보낼 fd: " + fd);

    await axios
      .post("https://i7d105.p.ssafy.io/api/file/product", fd, {
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

  const onChange = (e) => {
    const { value, name } = e.target;
    setProduct({
      ...product,
      [name]: value,
    });

    console.log(productId);
  };

  const onResetFile = () => {
    setProduct({
      ...product,
      product_thumbnail: "",
    });
  };

  const onBackBtn = () => {
    navigate(`/manager/ProductDetail/${product.productSeq}`);
  };

  const goWaitingPage = () => {
    navigate(`/manager/waitingPage/${seq}`); // 제품대기화면 페이지로 이동
  };

  return (
    <>
      <NavBar />
      {/* <h4 className="page-title">제품 상세</h4> */}
      <div className="register-main-wrapper">
        <div className="register-sub-wrapper">
          <div className="register-area-wrapper">
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
                <div className="product-register-small-btn">
                  <SmallButton label="이미지 등록" onClick={onImgRegisterBtn} />
                  <SmallButton label="이미지 초기화" onClick={onResetFile} />
                </div>
              </div>
            </div>

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
                      onChange={onChange}
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
                      onChange={onChange}
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
                      onChange={onChange}
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
                      onChange={onChange}
                      variant="outlined"
                    />
                  </div>
                </div>
              {/*  */}
              <div className="product-register-medi-btn">
                <MediButton label="수정완료" onClick={onEditCompleteBtn} />
                <MediButton label="대기화면 보기" onClick={goWaitingPage} />
                <MediButton label="돌아가기" onClick={onBackBtn} />
              </div>             

              </div>
            </div>
          </div>
        </div>
      </div>

      {/* 
      <div className="mainContent-wrapper">
        <div className="left-img">
          { product.productThumbnail
          ? <img className="img-wrapper" alt="#" src={`${product.productThumbnail}`} />
            :   
                imgFile === ""
                ?  <img
                className="preview-img" 
                alt="#" src={previewUrl} />
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
              defaultValue={product.productId}
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
              // value={product.productGroup.productGroupName}
              onChange={onChange}
              variant="outlined"
            />
          </div>
        </div>
      </div> */}

      <Footer />
    </>
  );
}

export default ProductEdit;
