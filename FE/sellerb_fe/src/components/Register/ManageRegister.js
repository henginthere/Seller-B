import React, {useState} from "react";
import { Link } from "react-router-dom";
import Axios from 'axios'

import { Footer, NavBar } from "../index";
import "./ManageRegister.css";

function ManageRegister() {
   
  const [brand, setBrand] = useState("");
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");

  // HandlerFunction 
  const onBrandHandler = ((e)=>{
    setBrand(e.target.value);
  })

  const onIdHandler = ((e)=>{
    setId(e.target.value);
  })

  const onPasswordHandler = ((e)=>{
    setPassword(e.target.value);
  })

  const onConfrimPasswordHandler = ((e)=>{
    setConfirmPassword(e.target.value);
  })

  const onPhoneHandler = ((e)=>{
    setPhone(e.target.value);
  })

  const onEmailHandler = ((e)=>{
    setEmail(e.target.value)
  })

  // axios post 
  const onSubmitHandler = ((event)=>{
    event.preventDefault(); 

    let customerInfo = {
      brand,
      id,
      password,
      confirmPassword,
      phone,
      email,
    }

    // Axios.post('/api/manager', customerInfo)
    // .then(response => {

    // })

  })

  return (
    <div className="manage-register-page">
      <NavBar />
      <div className="login">
        <form className="login-form">
          <h3 className="login-title">Welcome to SellerB</h3>
          <hr />

          <label className="login-form-label">
            <p>제품 브랜드</p>
            <input type="text" value={brand} onChange={onBrandHandler} placeholder="제품 브랜드" className="size" />
          </label>

          <label>
            <p>관리자 ID</p>
            <input type="text" value={id} onChange={onIdHandler} placeholder="아이디" className="size" />
          </label>

          <label>
            <p>Password </p>
            <input type="password" value={password} onChange={onPasswordHandler} placeholder="비밀번호" className="size" />
          </label>

          <label>
            <p>Password Confirm</p>
            <input
              type="password"
              value={confirmPassword}
              onChange={onConfrimPasswordHandler}
              placeholder="비밀번호 확인"
              className="size"
            />
          </label>

          <label>
            <p>Mobile Phone</p>
            <input
              placeholder="숫자만 입력"
              value={phone}
              onChange={onPhoneHandler}
              className="size num2"
              required
            />
          </label>

          <label>
            <p>E-mail</p>
            <input type="text" value={email} onChange={setEmail} placeholder="이메일" className="size" />
          </label>

          <br />
          <p>
            <input type="submit" value="Create Acoout" className="btn" />
          </p>
        </form>
      </div>

      <Footer />
    </div>
  );
}

export default ManageRegister;
