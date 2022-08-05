import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { Formik, ErrorMessage } from "formik";

import * as Yup from "yup";
import { useNavigate } from "react-router-dom";
import { registerApi } from "../../api/userApi";

import "./ManageRegister.css";

function ManageRegister() {
  const flexDisplay = {
    display: "flex",
    justifyContent: "center",
    alignItem: "center",
  };
  const logoImageStyle = {
    margin: "20px",
    minHeight: "100px",
    minWidth: "100px",
  };
  const m_1 = {
    margin: "10px",
  };

  const navigate = useNavigate();
  const moveMain = () => {
    navigate("/");
  };
  const validationSchema = Yup.object().shape({
    brand: Yup.string().min(1).required("브랜드명을 입력하세요"),
    id: Yup.string()
      .min(2, "아이디는 최소 2글자 이상입니다!")
      .max(10, "아이디 최대 10글자입니다!")
      .matches(
        /^[가-힣a-zA-Z][^!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?\s]*$/,
        "닉네임에 특수문자가 포함되면 안되고 숫자로 시작하면 안됩니다!",
      )
      .required("아이디를 입력하세요!"),
    password: Yup.string()
      .min(8, "비밀번호는 최소 8자리 이상입니다")
      .max(15, "비밀번호는 최대 15자리입니다!")
      .required("패스워드를 입력하세요!")
      .matches(
        /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])[^\s]*$/,
        "알파벳, 숫자, 공백을 제외한 특수문자를 모두 포함해야 합니다!",
      ),
    password2: Yup.string()
      .oneOf([Yup.ref("password"), null], "비밀번호가 일치하지 않습니다!")
      .required("필수 입력 값입니다!"),
    // phone: Yup.string().matches(/^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/),
    email: Yup.string()
      .email("올바른 이메일 형식이 아닙니다!")
      .required("이메일을 입력하세요!"),
  });

<<<<<<< HEAD
  // Axios
  const submit = async (values) => {
    console.log(values);
    // const { brand, id, password, phone, email} = values;
=======
  const registerBtn = async (values)=>{
    const { brand, id, password, phone, email} = values;
    console.log(values.brand);

    const userInfo = {
      "managerId": id,
      "managerName":"",
      "managerPass":password,
      "managerTel":phone,
      "managerEmail":email
    }

    console.log("userInfo:" + userInfo.managerId);
    registerApi(userInfo)
    .then((res)=>{
      console.log(res.data);
    })
    .catch((err)=>{
      console.log(err);
    })


  }

  // Axios 
  const submit = async (values) => {
    const { brand, id, password, phone, email} = values;
    console.log(values.brand);
>>>>>>> feature-fe-consultant-crud-api-hy
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
<<<<<<< HEAD
    <div className='manage-register-page'>
      <div style={flexDisplay}>
        <img
          style={logoImageStyle}
          src={`${process.env.PUBLIC_URL}/img/sellerB_Logo.svg`}
          alt='No Image'
        />
      </div>

      <div className='login'>
        <Formik
          initialValues={{
            brandKor: "",
            brandEnd: "",
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
            <div className='signup-wrapper'>
              {/* <ToastContainer /> */}
              <form
                onSubmit={handleSubmit}
                autoComplete='off'
                className='login-form'
              >
                <h3 className='login-title'>Welcome to SellerB</h3>
                <hr />
                <label className='login-form-label'>
                  <p>제품 브랜드(Kor)</p>
                  <input
                    value={values.brand}
                    name='brandKor'
                    type='text'
                    variant='outlined'
                    onChange={handleChange}
                    placeholder='제품 브랜드'
                    className='size'
                  />
                  <div className='error-message'>{errors.brand}</div>
                </label>
                <label className='login-form-label'>
                  <p>제품 브랜드(Eng)</p>
                  <input
                    value={values.brand}
                    name='brandEng'
                    type='text'
                    variant='outlined'
                    onChange={handleChange}
                    placeholder='Brand Name'
                    className='size'
                  />
                  <div className='error-message'>{errors.brand}</div>
                </label>
                <label className='login-form-label'>
                  <p>아이디</p>
                  <input
                    value={values.id}
                    name='id'
                    type='text'
                    variant='outlined'
                    onChange={handleChange}
                    placeholder='아이디'
                    className='size'
                  />
                  <div className='error-message'>{errors.id}</div>
                </label>

                <label className='login-form-label'>
                  <p>비밀번호</p>
                  <input
                    value={values.password}
                    name='password'
                    type='text'
                    variant='outlined'
                    onChange={handleChange}
                    placeholder='비밀번호'
                    className='size'
                  />
                  <div className='error-message'>{errors.password}</div>
                </label>

                <label className='login-form-label'>
                  <p>비밀번호 확인</p>
                  <input
                    value={values.password2}
                    name='password2'
                    type='text'
                    variant='outlined'
                    onChange={handleChange}
                    placeholder='비밀번호 확인'
                    className='size'
                  />
                  <div className='error-message'>{errors.password2}</div>
                </label>

                <label className='login-form-label'>
                  <p>Tel.</p>
                  <input
                    value={values.phone}
                    name='phone'
                    type='text'
                    variant='outlined'
                    onChange={handleChange}
                    placeholder='핸드폰번호'
                    className='size'
                  />
                  <div className='error-message'>{errors.phone}</div>
                </label>

                <label className='login-form-label'>
                  <p>Email</p>
                  <input
                    value={values.email}
                    name='email'
                    type='text'
                    variant='outlined'
                    onChange={handleChange}
                    placeholder='Email'
                    className='size'
                  />
                  <div className='error-message'>{errors.email}</div>
                </label>
                <div style={flexDisplay}>
                  <button type='submit' style={m_1}>
                    가입
                  </button>
                  <button onClick={moveMain} style={m_1}>
                    뒤로
                  </button>
                </div>
              </form>
            </div>
          )}
        </Formik>
=======
    <div className="manage-register-page">
      <NavBar />
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
            <form onSubmit={handleSubmit} autoComplete="off" className="login-form">
            <h3 className="login-title">Welcome to SellerB</h3>
            <hr />
            <label className="login-form-label">
              <p>제품 브랜드</p>
              <input
                value={values.brand}
                name="brand"
                type="text"
                variant="outlined"
                onChange={handleChange}
                placeholder="제품 브랜드"
                className="size"
              />
              <div className="error-message">{errors.brand}</div>
            </label>
            
            <label className="login-form-label">
              <p>아이디</p>
              <input
                value={values.id}
                name="id"
                type="text"
                variant="outlined"
                onChange={handleChange}
                placeholder="아이디"
                className="size"
              />
              <div className="error-message">{errors.id}</div>
            </label>

            <label className="login-form-label">
              <p>비밀번호</p>
              <input
                value={values.password}
                name="password"
                type="text"
                variant="outlined"
                onChange={handleChange}
                placeholder="비밀번호"
                className="size"
              />
              <div className="error-message">{errors.password}</div>
            </label>

            <label className="login-form-label">
              <p>비밀번호 확인</p>
              <input
                value={values.password2}
                name="password2"
                type="text"
                variant="outlined"
                onChange={handleChange}
                placeholder="비밀번호 확인"
                className="size"
              />
              <div className="error-message">{errors.password2}</div>
            </label>
  
            <label className="login-form-label">
              <p>Tel.</p>
              <input
                value={values.phone}
                name="phone"
                type="text"
                variant="outlined"
                onChange={handleChange}
                placeholder="핸드폰번호"
                className="size"
              />
              <div className="error-message">{errors.phone}</div>
            </label>
    
            <label className="login-form-label">
              <p>Email</p>
              <input
                value={values.email}
                name="email"
                type="text"
                variant="outlined"
                onChange={handleChange}
                placeholder="Email"
                className="size"
              />
              <div className="error-message">{errors.email}</div>
            </label>

              {/* <div className="input-forms">
                <div className="input-forms-item">
                  <div className="input-label">이메일</div>
                  <TextField
                    value={values.email}
                    name="email"
                    variant="outlined"
                    onChange={handleChange}
                  />
                  <div className="error-message">{errors.email}</div>
                </div>
                <div className="input-forms-item">
                  <div className="input-label">닉네임</div>
                  <TextField
                    value={values.username}
                    name="username"
                    variant="outlined"
                    onChange={handleChange}
                  />
                  <div className="error-message">{errors.username}</div>
                </div>
                <div className="input-forms-item">
                  <div className="input-label">비밀번호</div>
                  <TextField
                    value={values.password}
                    name="password"
                    variant="outlined"
                    type="password"
                    onChange={handleChange}
                  />
                  <div className="error-message">{errors.password}</div>
                </div>
                <div className="input-forms-item">
                  <div className="input-label">비밀번호 확인</div>
                  <TextField
                    value={values.password2}
                    name="password2"
                    variant="outlined"
                    type="password"
                    onChange={handleChange}
                  />
                  <div className="error-message">{errors.password2}</div>
        </div> */}
                <Button
                  color="primary"
                  variant="contained"
                  fullWidth
                  type="submit"
                  onClick={()=> registerBtn(values)}
                >
                  회원가입
                </Button>
           
            </form>
          </div>
        )}
      </Formik>
>>>>>>> feature-fe-consultant-crud-api-hy
      </div>
    </div>
  );
}

export default ManageRegister;
