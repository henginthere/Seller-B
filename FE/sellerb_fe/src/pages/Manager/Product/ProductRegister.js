import React, { useState, useEffect } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import axios from "axios";
import "./ProductRegister.css";
import { Footer, NavBar } from "../../../components/index";
import {
  productRegisterApi,
  productGroupListApi,
  productImgRegisterApi,
} from "../../../api/productApi";
import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";

function ProductRegister() {
  const navigate = useNavigate();

  const [price, setPrice] = useState(0);
  const [toIntPrice, setToIntPrice] = useState(0);

  const inputPriceFormat = (str) => {
    console.log("s", str);
    const comma = (str) => {
      str = String(str);

      return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
    };
    const uncomma = (str) => {
      str = String(str);
      return str.replace(/[^\d]+/g, "");
    };

    return comma(uncomma(str));
  };

  const [resImg, setResImg] = useState("");
  const [product, setProduct] = useState({
    productGroupName: "",
    productId: "",
    productName: "",
    productPrice: "",
    productManual: "",
    productThumbnail: resImg,
  });
  const [selectSeq, setSelectSeq] = useState([]);
  const [groupList, setGroupList] = useState([]);
  const [groupOption, setGroupOption] = useState("");

  const [managerBrand, setManagerBrand] = useState(
    sessionStorage.getItem("brandNameKor"),
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
    `${process.env.PUBLIC_URL}/img/default_img.png`,
  );

  // 매니저가 속한 브랜드의 제품군 리스트 받아오기
  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
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

    setProduct({
      ...product,
      [name]: value,
    });
    console.log(productId);
  };

  const onGroupChange = (e) => {
    e.preventDefault();

    const item = groupList.find(
      (it) =>
        it.brandName === managerBrand && it.productGroupName === e.target.value,
    );

    setSelectSeq(item.productGroupSeq);
  };

  // 이미지 파일을 업로드하면, 실행될 함수
  const onHandleChangeFile = (event) => {
    console.log(event.target.files);
    setImgFile(event.target.files);

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
    console.log("in RegisterBtn API : " + resImg);

    // 선택한 그룹군에 대해, productGroupSeq찾기
    console.log("제출 전 seq : " + selectSeq);

    const Info = {
      productGroupName: product.productGroupName,
      productGroupSeq: selectSeq,
      productId: product.productId,
      productName: product.productName,
      productPrice: product.productPrice,
      productManual: product.productManual,
      productThumbnailUrl: resImg,
    };

    console.log("등록 전 Product: " + JSON.stringify(Info));

    productRegisterApi(Info)
      .then((res) => {
        console.log(res.data);

        navigate("/manager/productList");
      })
      .catch((err) => {
        if(resImg === "")
        {
          toast.error("이미지를 먼저 등록해주세요!", {
            autoClose: 700,
            position: toast.POSITION.TOP_CENTER,
          });
        }else{
          toast.error("제품 정보를 모두 채워주세요!", {
            autoClose: 700,
            position: toast.POSITION.TOP_CENTER,
          });
        }
        console.log(err.data);
      });
  };

  const onImgRegisterBtn = async () => {
    const fd = new FormData();
    // imgFile의 파일들을 읽어와서, file이라는 이름으로 저장하기
    // -> FormData에 file이라는 이름의 파일 배열이 들어감
    Object.values(imgFile).forEach((file) => fd.append("data", file));


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
            autoClose: 1000,
            position: toast.POSITION.TOP_CENTER,
          });
        }
      })
      .catch((error) => {
        toast.error("이미지를 등록해주세요!", {
          autoClose: 1000,
          position: toast.POSITION.TOP_CENTER,
        });
        console.log("Error");
      });
  };

  return (
    <>
      <NavBar />
      <div className='register-main-wrapper'>
        <div className='register-sub-wrapper'>
          <div className='register-area-wrapper'>
            <div className='left-img'>
              {imgFile === "" ? (
                <img
                  className='product-register-img'
                  alt='#'
                  src={previewUrl}
                />
              ) : null}
              {imgBase64.map((item) => {
                return (
                  <div>
                    <img
                      className='product-register-img'
                      src={item}
                      alt='First Slide'
                    />
                  </div>
                );
              })}
              <div className='product-img-bottom-wrapper'>
                <input
                  multiple='multiple'
                  type='file'
                  id='file'
                  name='file'
                  accept='image/*'
                  onChange={onHandleChangeFile}
                  style={{ display: "none" }}
                />
                <div className='product-register-small-btn'>
                  <label for='file'>
                    <div className='find-file-btn'>파일찾기</div>
                  </label>
                  <SmallButton label='이미지 등록' onClick={onImgRegisterBtn} />
                  <ToastContainer />
                </div>
              </div>
            </div>

            {/* 오른쪽 영역 */}
            <div className='right-input'>
              <div className='input-sub-content-wrapper'>
                <div className='input-ele'>
                  <p>품번</p>
                  <div className='product-id-input-wrapper'>
                    <input
                      className='product-id-input'
                      name='productId'
                      onChange={onChange}
                      value={productId}
                      variant='outlined'
                    />
                  </div>
                </div>

                <div className='input-ele'>
                  <p>제품명</p>
                  <div className='product-id-input-wrapper'>
                    <input
                      className='product-id-input'
                      name='productName'
                      onChange={onChange}
                      value={productName}
                      variant='outlined'
                    />
                  </div>
                </div>

                <div className='input-ele'>
                  <p>가격</p>
                  <div className='product-id-input-wrapper'>
                    <input
                      className='product-id-input'
                      type='text'
                      name='productPrice'
                      onChange={onChange}
                      value={productPrice}
                      variant='outlined'
                    />
                  </div>
                </div>

                <div className='input-ele'>
                  <p>제품 메뉴얼</p>
                  <div className='product-id-input-wrapper'>
                    <input
                      className='product-id-input'
                      type='text'
                      name='productManual'
                      onChange={onChange}
                      value={productManual}
                      variant='outlined'
                    />
                  </div>
                </div>

                <div className='input-ele'>
                  <p>제품군</p>
                  <select
                    onChange={onGroupChange}
                    className='product-select-option'
                    defaultValue={product.productGroupName}
                    name='productGroupName'
                  >
                    <option>---제품군 선택--</option>
                    {groupList.map((option) =>
                      option.brandName === managerBrand ? (
                        <option value={option.productGroupName}>
                          {option.productGroupName}
                        </option>
                      ) : (
                        ""
                      ),
                    )}
                  </select>
                </div>
              </div>
              <div className='product-register-medi-btn'>
                <MediButton label='제품 등록' onClick={onRegisterBtn} />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className='bottomContent-wrapper'></div>
      <Footer />
    </>
  );
}

export default ProductRegister;
