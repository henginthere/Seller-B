import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { Formik, ErrorMessage } from "formik";
import { toast, ToastContainer } from "react-toastify";
import { Button, TextField } from "@mui/material";
import * as Yup from "yup";
import { useNavigate } from "react-router-dom";
import { registerApi } from "../../api/userApi";
import { listBrandApi } from "../../api/brandApi";
import { SmallButton } from "../../components/Common/SmallButton"
import { MediButton } from "../../components/Common/MediButton"

import { Footer, NavBar } from "../index";
import "./ManageRegister.css";

function ManageRegister() {
  const navigate = useNavigate();
  const [brandList, setBrandList] = useState([]); // API로 받아올 현재 브랜드 목록
  const [selectBrand, setSelectBrand] = useState("브랜드"); // 관리자가 선택하는 brand option
  const [selectedBrandSeq, setSelectedBrandSeq] = useState("");

  const [resImg, setResImg] = useState("");
  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState("");
  const [previewUrl, setPreviewUrl] = useState(
    `${process.env.PUBLIC_URL}/img/default_img.png`
  );

  useEffect(() => {
    listBrandApi()
      .then((res) => {
        console.log("brandList:" + res.data[0]);
        setBrandList(res.data);
        console.log(brandList[0]);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const onBrandChange = (e) => {
    const { value } = e.target;
    console.log("onBrandChange: " + value);
    setSelectBrand(value);

    // 선택된 브랜드에 해당하는 BrandSeq 찾기
    const item = brandList.find((it) => it.brandNameKor === value);
    setSelectedBrandSeq(item.brandSeq);
  };

  // 유효성 체크 설정
  const validationSchema = Yup.object().shape({
    brand: Yup.string().min(1).required("브랜드명을 입력하세요"),
    id: Yup.string()
      .min(2, "아이디는 최소 2글자 이상입니다!")
      .max(10, "아이디 최대 10글자입니다!")
      .matches(
        /^[가-힣a-zA-Z][^!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?\s]*$/,
        "닉네임에 특수문자가 포함되면 안되고 숫자로 시작하면 안됩니다!"
      )
      .required("아이디를 입력하세요!"),
    password: Yup.string()
      .min(8, "비밀번호는 최소 8자리 이상입니다")
      .max(15, "비밀번호는 최대 15자리입니다!")
      .required("패스워드를 입력하세요!")
      .matches(
        /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])[^\s]*$/,
        "알파벳, 숫자, 공백을 제외한 특수문자를 모두 포함해야 합니다!"
      ),
    password2: Yup.string()
      .oneOf([Yup.ref("password"), null], "비밀번호가 일치하지 않습니다!")
      .required("필수 입력 값입니다!"),
    // phone: Yup.string().matches(/^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/),
    email: Yup.string()
      .email("올바른 이메일 형식이 아닙니다!")
      .required("이메일을 입력하세요!"),
  });

  // 이미지 관련
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
            autoClose: 1000,
            position: toast.POSITION.TOP_CENTER
          });

        }
      })
      .catch((error) => {
        toast.error("이미지를 등록해주세요!", {
          autoClose: 1000,
          position: toast.POSITION.TOP_CENTER
        });
        console.log("Error");
      });
  };

  // 회원가입 버튼
  const registerBtn = async (values) => {
    const { brand, managerName, id, password, phone, email } = values;
    console.log(values.brand);

    const userInfo = {
      brandSeq: selectedBrandSeq,
      managerId: id,
      managerName: managerName,
      managerPass: password,
      managerTel: phone,
      managerEmail: email,
      managerImageUrl: resImg
    };

    registerApi(userInfo)
      .then((res) => {
        console.log(res.data);

        navigate("/main");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // Axios
  const submit = async (values) => {
    const { brand, managerName, id, password, phone, email } = values;
    console.log(values.brand);
  };

  return (
    <div className="manage-register-page">
      <div className="login">
        <div className="login-title-wrapper">
        <h3 className="login-title">Sign Up</h3>
        </div>
        <hr />
        <div className="signup-profile-img-wrapper">
        <div className="signUp-row">
          <div className="row-left-label">
            <div className="left-label-text">프로필</div>
          </div>
          <div className="row-right-profile">
            <div className="right-profile-wrapper">
              <div className="right-profile-content">
                {imgFile === "" ? (
                  <img
                    className="manage-preview-img"
                    alt="#"
                    src={previewUrl}
                  />
                ) : null}
                {imgBase64.map((item) => {
                  return (
                    <div>
                      <img
                        className="manage-profile-img"
                        src={item}
                        alt="First Slide"
                      />
                    </div>
                  );
                })}
              </div>
              <div className="product-img-bottom-wrapper">
                <input
                  // className="img-btn"
                  multiple="multiple"
                  type="file"
                  id="file"
                  name="file"
                  accept="image/*"
                  onChange={onHandleChangeFile}
                  style={{"display":"none"}}
                />
                <div className="product-register-small-btn">
                  <label for="file">
                    <div className="find-file-btn">파일찾기</div>
                  </label>
                  <SmallButton label="이미지 등록" onClick={onImgRegisterBtn} />
                  <ToastContainer />
                </div>
              </div>
            </div>
          </div>
        </div>
        </div>
        <Formik
          initialValues={{
            brand: "",
            managerName:"",
            id: "",
            password: "",
            password2: "",
            phone: "",
            email: "",
          }}
          validationSchema={validationSchema}
          onSubmit={submit}
          validateOnMount={true}
        >
          {({ values, handleSubmit, handleChange, errors }) => (
            <div className="signup-wrapper">
              {/* <ToastContainer /> */}
              <form
                onSubmit={handleSubmit}
                autoComplete="off"
                className="login-form"
              >
                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">제품 브랜드</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <select onChange={onBrandChange} value={selectBrand}>
                          {brandList.map((ele, i) => {
                            return <option>{ele.brandNameKor}</option>;
                          })}
                        </select>
                      </div>
                    </div>
                  </div>
                  <div className="error-message">{errors.brand}</div>
                  {/* </label> */}
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">이름</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={values.managerName}
                          name="managerName"
                          type="text"
                          variant="outlined"
                          onChange={handleChange}
                          placeholder="이름"
                          className="size"
                        />
                      </div>
                    </div>
                    <div className="error-message">{errors.id}</div>
                  </div>
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">아이디</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={values.id}
                          name="id"
                          type="text"
                          variant="outlined"
                          onChange={handleChange}
                          placeholder="아이디"
                          className="size"
                        />
                      </div>
                    </div>
                    <div className="error-message">{errors.id}</div>
                  </div>
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">비밀번호</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={values.password}
                          name="password"
                          type="text"
                          variant="outlined"
                          onChange={handleChange}
                          placeholder="비밀번호"
                          className="size"
                        />
                      </div>
                    </div>
                    <div className="error-message">{errors.password}</div>
                  </div>
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">비밀번호 확인</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={values.password2}
                          name="password2"
                          type="text"
                          variant="outlined"
                          onChange={handleChange}
                          placeholder="비밀번호 확인"
                          className="size"
                        />
                      </div>
                    </div>
                    <div className="error-message">{errors.password2}</div>
                  </div>
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">Tel.</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={values.phone}
                          name="phone"
                          type="text"
                          variant="outlined"
                          onChange={handleChange}
                          placeholder="핸드폰번호"
                          className="size"
                        />
                      </div>
                    </div>
                    <div className="error-message">{errors.phone}</div>
                  </div>
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">Email</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={values.email}
                          name="email"
                          type="text"
                          variant="outlined"
                          onChange={handleChange}
                          placeholder="Email"
                          className="size"
                        />
                      </div>
                    </div>
                    <div className="error-message">{errors.email}</div>
                  </div>
                </div>

                {/* <Button
                  color="primary"
                  variant="contained"
                  fullWidth
                  type="submit"
                  onClick={() => registerBtn(values)}
                >
                  회원가입
                </Button> */}
                <MediButton 
                  label="회원가입"
                  onClick={registerBtn(values)}
                />
              </form>
            </div>
          )}
        </Formik>
      </div>
      <Footer />
    </div>
  );
}

export default ManageRegister;
