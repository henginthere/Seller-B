import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Main.css";

import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { LOGIN } from "../../slices/userSlice";

// import { loginUser }

import { LoginApi } from "../../api/userApi";
import { setRefreshToken, getCookieToken } from "../../storage/Cookie";
import { SET_TOKEN, CHECK_ADMIN } from "../../slices/authSlice";

// import material ui
import { styled } from "@mui/material/styles";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/Checkbox";

function Main() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  // const URL = '/api/auth/login'

  // const MyTextField = style
  let [id, setId] = useState("");
  let [pass, setPass] = useState("");

  const onIdHandler = (e) => {
    setId(e.target.value);
  };

  const onPasswordHandler = (e) => {
    setPass(e.target.value);
  };

  // 로그인 버튼 클릭 후
  const onLoginBtn = () => {
    const data = {
      id,
      pass,
    };

    axios
      .post("/api/auth/login", data)
      .then((res) => {
        // 데이터 자체만 뽑기
        const accessToken = res.data.tokenDto.accessToken;
        const refreshToken = res.data.tokenDto.refreshToken;
        const adminCheck = res.data.authority;
        const managerSeq = res.data.seq;
        const brandSeq = res.data.brandSeq;

        // localstorage에 저장
        localStorage.setItem("accessToken", res.data.tokenDto.accessToken);
        localStorage.setItem("refreshToken", res.data.tokenDto.refreshToken);
        localStorage.setItem("seq", res.data.seq);
        console.log(localStorage.getItem("seq"))
        

        axios
          .get(`/api/brand/${brandSeq}`)
          .then((res)=>{
            localStorage.setItem("brandNameKor", res.data.brandNameKor);
            console.log("manager's brand:" + res.data.brandNameKor);
            localStorage.getItem("accessToken")
            console.log(localStorage.getItem("brandNameKor"));
          })
          .catch((err)=>{

          })

        // isAdmin이라면, Redux isAdmin 값 true로 전환
        dispatch(CHECK_ADMIN());
        navigate("/manager/main");
        if (adminCheck === "ROLE_ADMIN") {
          
        } else {
          navigate("/consultant/main");
        }
      })
      .catch((err) => {
        console.log(err.data);
      });
  };

  return (
    <>
      <div className="main-body">
        {/* main화면 왼쪽 */}
        <div className="main-left">
          <div className="big-sellerb">SellerB</div>
          <div className="main-subtitle">오늘의 sellerB가 되어 보세요</div>
          <div className="main-form-wrapper">
            {/* Form start */}
            <form className="main-form">
              <TextField
                className="main-TextField"
                sx={{
                  borderColor: "yellow",
                  height: "25%",
                  boxShadow: 1,
                  width: 200,
                  marginLeft: 13,
                  fontSize: 15,
                }}
                style={{
                  marginTop: "15px",
                  marginBottom: "10px",
                  display: "block",
                }}
                value={id}
                onChange={onIdHandler}
                label="ID"
              />
              <TextField
                sx={{
                  borderColor: "yellow",
                  height: "25%",
                  boxShadow: 1,
                  width: 200,
                  marginLeft: 13,
                }}
                style={{ marginBottom: "10px", display: "block" }}
                onChange={onPasswordHandler}
                type="password"
                label="PASSWORD"
              />

              <Button
                sx={{
                  bgcolor: "#FCF0D2",
                  fontcolor: "#324E66",
                  marginLeft: 13,
                  width: 200,
                }}
                onClick={onLoginBtn}
              >
                Login
              </Button>

              {/* <div className='FormControlLabel-wrapper'>
              <FormControlLabel 
                style={{ marginLeft: '90px'}} 
                label="checkManagerLogin" 
              />
              관리자 로그인
              </div> */}
              <div className="register-resetPassword">
                <Link to="/manager/register" className="link-to">
                  <div className="manager-register">관리자 회원가입</div>
                </Link>
                <div className="reset-pssword">
                  <Link to="#" className="link-to">
                    비밀번호 재설정
                  </Link>
                </div>
              </div>
            </form>
            {/* Form end */}
          </div>
        </div>

        {/* Main화면 오른쪽 이미지 */}
        <div className="main-right">
          <img className="main-bg-img" alt="#" src="img/Main-bg-img.png" />
        </div>
      </div>
    </>
  );
}

export default Main;
