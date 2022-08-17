import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import axios from "axios";

import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import { productEditApi, productDetailApi,  productGroupListApi } from "../../../api/productApi";

import { toast, ToastContainer } from "react-toastify";
import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";
import { BackMediButton } from "../../../components/Common/BackMediButton";
import { BackSmallButton } from "../../../components/Common/BackSmallButton";

function ProductEdit() {
  const { seq } = useParams();
  const navigate = useNavigate();

  const [groupList, setGroupList] = useState([]);

  const [managerBrand, setManagerBrand] = useState(
    sessionStorage.getItem("brandNameKor")
  );
  const [selectSeq, setSelectSeq] = useState([]);
  const [readOnly, setReadOnly] = useState(true);
  
  const [resImg, setResImg] = useState("");
  const [product, setProduct] = useState({
    productSeq: seq,
    productGroupName: "",
    productGroupSeq: 0,
    productId: "",
    productName: "",
    productPrice: 0,
    productManual:"",
    productThumbnail: "",
  });

  const {
    productSeq,
    productGroupName,
    productGroupSeq,
    productId,
    productName,
    productPrice,
    productManual,
    productThumbnail
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
        // console.log("수정된 제품정보: " + pro);
      })
      .catch((err) => {
        console.log(err.data);
      });
  }, []);

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

  const onEditCompleteBtn = async () => {
    console.log(seq);
    const EditInfo = {
      productSeq: product.productSeq,
      productId: product.productId,
      productName: product.productName,
      productPrice: product.productPrice,
      productManual: product.productManual,
      productThumbnailUrl: resImg
      // productGroupName: product.productGroupName,
    };

    // await productEditApi(EditInfo)
    //   .then((res) => {
    //     console.log("수정 Edit API Success!!!!!");
    //   })
    //   .catch((err) => {
    //     console.log(err.data);
    //   });

    await axios
      .put(`https://i7d105.p.ssafy.io/api/product/${seq}` , EditInfo, {
        header: {
          "Content-Type": `multipart/form-data`,
        },
      })
      .then((response) => {
        console.log("success");

        toast.success("제품수정 완료!", {
          autoClose: 700,
          position: toast.POSITION.TOP_CENTER
        });
      })
      .catch((error) => {
        console.log("Error!!!");
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
          toast.success("이미지 등록 완료!", {
            autoClose: 700,
            position: toast.POSITION.TOP_CENTER
          });
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
  };

  const onGroupChange = (e) => {
    e.preventDefault();

    const item = groupList.find(
      (it) =>
        it.brandName === managerBrand && it.productGroupName === e.target.value
    );

    setSelectSeq(item.productGroupSeq);
  };

  const onResetFile = () => {
    setImgFile("");
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
              {imgFile === "" ? (       // 아직 업로드한 파일이 없으면
                <img
                  className="product-register-img"
                  alt="#"
                  src={previewUrl}
                />
              ) : imgBase64.map((item) => {
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
              {/* {imgBase64.map((item) => {
                return (
                  <div>
                    <img
                      className="product-register-img"
                      src={item}
                      alt="First Slide"
                    />
                  </div>
                );
              })} */}
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
                  <ToastContainer />
                  <BackSmallButton label="이미지 초기화" onClick={onResetFile} />
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
                  <p>제품메뉴얼</p>
                  <div className="product-id-input-wrapper">
                    <input
                      className="product-id-input"
                      name="productManual"
                      value={product.productManual}
                      onChange={onChange}
                      variant="outlined"
                    />
                  </div>
                </div>


                {/*  */}
                <div className="input-ele">
                  <p>제품군</p>
                  {/* <select
                    onChange={onGroupChange}
                    className="product-select-option"
                    defaultValue={product.productGroupName}
                    name="productGroupName"
                  >
                  <option>---제품군 선택--</option>
                    {groupList.map((option) =>
                      option.brandName === managerBrand ? (
                        <option value={option.productGroupName}>
                          {option.productGroupName}
                        </option>
                      ) : (
                        ""
                      )
                    )}
                  </select> */}
                  <div className="product-id-input-wrapper">
                    <input
                      className="product-id-input"
                      name="productGroup"
                      value={product.productGroupName}
                      readOnly="true"
                      onChange={onChange}
                      variant="outlined"
                    />
                  </div>
                </div>
              {/*  */}
              <div className="product-register-medi-btn">
                <MediButton label="수정완료" onClick={onEditCompleteBtn} />
                <MediButton label="대기화면 보기" onClick={goWaitingPage} />
                <BackMediButton label="돌아가기" onClick={onBackBtn} />
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
