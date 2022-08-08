import React, { useEffect, useState} from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { Formik, ErrorMessage } from "formik";
import { Button, TextField } from "@mui/material";
import * as Yup from "yup";
import { registerApi } from "../../api/userApi";
import { listBrandApi } from "../../api/brandApi";

import { Footer, NavBar } from "../index";
import "./ManageRegister.css";

function ManageRegister() {
  const navigate = useNavigate();
  const [brandList, setBrandList] = useState([]); // API로 받아올 현재 브랜드 목록
  const [selectBrand, setSelectBrand] = useState("브랜드"); // 관리자가 선택하는 brand option
  const [selectedBrandSeq, setSelectedBrandSeq] = useState("");

  const onBrandChange = (e) => {
    const { value } = e.target;
    console.log("onBrandChange: " + value);
    setSelectBrand(value);

    // 선택된 브랜드에 해당하는 BrandSeq 찾기
    const item = brandList.find((it) => it.brandNameKor === value);
    console.log("brandSeq: " + item.brandNameKor);
    setSelectedBrandSeq(item.brandSeq);
  };


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

  // 회원가입 버튼
  const registerBtn = async (values) => {
    const { brand, id, password, phone, email } = values;
    console.log(values.brand);

    const userInfo = {
      brandSeq: selectedBrandSeq,
      managerId: id,
      managerName: "",
      managerPass: password,
      managerTel: phone,
      managerEmail: email,
    };

    console.log("userInfo:" + userInfo.managerId);

    registerApi(userInfo)
      .then((res) => {
        console.log(res.data);
      

        navigate("/");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // Axios
  const submit = async (values) => {
    const { brand, id, password, phone, email } = values;
    console.log(values.brand);
    // try {
    //   await axios.post("/api/auth/signup", {
    //     brand,
    //     id,
    //     password,
    //     phone,
    //     email
    //   });
    //   toast.success(<h3>회원가입이 완료되었습니다.<br/>로그인 하세요😎</h3>, {
    //     position: "top-center",
    //     autoClose: 2000
    //   });
    //   setTimeout(()=> {
    //     navigate("/login");
    //   }, 2000);

    // } catch (e) {
    //   // 서버에서 받은 에러 메시지 출력
    //   toast.error(e.response.data.message + "😭", {
    //     position: "top-center",
    //   });
    // }
  };

  return (
    <div className="manage-register-page">
      <div className="login">
        <Formik
          initialValues={{
            brand: "",
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
                <h3 className="login-title">Sign Up</h3>
                <hr />
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
                  {/* <input
                value={values.brand}
                name="brand"
                type="text"
                variant="outlined"
                onChange={handleChange}
                placeholder="제품 브랜드"
                className="size"
              /> */}
                  <div className="error-message">{errors.brand}</div>
                  {/* </label> */}
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

                <Button
                  color="primary"
                  variant="contained"
                  fullWidth
                  type="submit"
                  onClick={() => registerBtn(values)}
                >
                  회원가입
                </Button>
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
