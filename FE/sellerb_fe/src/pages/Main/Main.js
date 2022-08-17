import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Main.css";

import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
// import { LOGIN } from "../../slices/userSlice";

// import { loginUser }

// import { LoginApi } from "../../api/userApi";
import { getManagerInfoApi } from "../../api/managerApi";
// import { setRefreshToken, getCookieToken } from "../../storage/Cookie";
import { SET_TOKEN, CHECK_ADMIN } from "../../slices/authSlice";

// import material ui
// import { styled } from "@mui/material/styles";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
// import Checkbox from "@mui/material/Checkbox";
// import FormControlLabel from "@mui/material/Checkbox";

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
        // const accessToken = res.data.tokenDto.accessToken;
        // const refreshToken = res.data.tokenDto.refreshToken;
        const adminCheck = res.data.authority;
        // const managerSeq = res.data.seq;
        // const brandSeq = res.data.brandSeq;

        // sessionStorage에 저장
        sessionStorage.setItem("accessToken", res.data.tokenDto.accessToken);
        sessionStorage.setItem("refreshToken", res.data.tokenDto.refreshToken);
        sessionStorage.setItem("seq", res.data.seq);
        sessionStorage.setItem("brandSeq", res.data.brandSeq);
        console.log(sessionStorage.getItem("seq"));

        // isAdmin이라면, Redux isAdmin 값 true로 전환
        dispatch(CHECK_ADMIN());

        if (adminCheck === "ROLE_ADMIN") {
          sessionStorage.setItem("adminCheck", "ROLE_ADMIN");
          console.log("admin:" + sessionStorage.getItem("adminCheck"));
          navigate("/manager/main");
        } else {
          sessionStorage.setItem("adminCheck", "ROLE_USER");
          console.log("consultant:" + sessionStorage.getItem("adminCheck"));
          navigate("/consultant/main");
        }
      })
      .then((res) => {
        const seq = sessionStorage.getItem("seq");
        // axios

        getManagerInfoApi(seq)
          .then((res) => {
            console.log("LOGIN 한 매니저 정보: " + JSON.stringify(res.data));

            sessionStorage.setItem("brandNameKor", res.data.brand.brandNameKor);
            sessionStorage.setItem("brandNameEng", res.data.brand.brandNameEng);
            sessionStorage.setItem("brandSeq", res.data.brand.brandSeq);

            console.log("IN SESSION:" + sessionStorage.getItem("brandSeq"));
          })
          .catch((err) => {});
      })
      .catch((err) => {
        console.log(err.data);
        alert("아이디와 비밀번호를 확인하세요!");
      });
  };

  return (
    <>
      <div className='main-body'>
        {/* main화면 왼쪽 */}
        <div className='main-left'>
          <div className='big-sellerb'>SellerB</div>
          <div className='main-subtitle'>오늘의 sellerB가 되어 보세요</div>
          <div className='main-form-wrapper'>
            {/* Form start */}
            <form className='main-form'>
              <TextField
                className='main-TextField'
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
                label='ID'
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
                type='password'
                label='PASSWORD'
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
              <div className='manager-register-btn-wrapper'>
                <Link to='/manager/register' className='link-to'>
                  <div className='manager-register'>관리자 회원가입</div>
                </Link>
              </div>
            </form>
            {/* Form end */}
          </div>
        </div>

        {/* Main화면 오른쪽 이미지 */}
        <div className='main-right'>
          <img className='main-bg-img' alt='#' src='img/Main-bg-img.png' />
        </div>
      </div>
    </>
  );
}

export default Main;
