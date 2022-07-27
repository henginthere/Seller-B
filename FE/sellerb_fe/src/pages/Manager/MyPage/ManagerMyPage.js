import React, { useState } from "react";
import "./ManagerMyPage.css";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Input from "@mui/material/Input";
function ManagerMyPage() {
  // 초기 더미 데이터
  const dummy_data = {
    brand: "SAMSUNG",
    id: "admin",
    name: "관리자",
    pw: "admin",
    tel: "010-9999-9999",
    email: "admin@admin.com",
  };

  // 비동기로 처리하기 위함. useState로 바로바로 적용!
  const [isModify, setModify] = useState(false);
  // 수정된 데이터  --> data
  const [data, setData] = useState(dummy_data);
  // 수정 페이지 인지 아닌지 바꿔주는 함수
  const ChangeToModify = () => {
    setModify(!isModify);
    console.log("isModify : " + isModify);
  };

  const cancelModify = () => {
    setData({ ...dummy_data });
    ChangeToModify();
  };
  // 최종적으로 수정 완료 버튼을 누르면 api로 DB에 반영하기 위한 함수
  const handleSubmit = (e) => {
    e.preventDefault();

    alert("수정하시겠습니까?");
    console.log("id" + e.id);
    console.log("pw" + e.pw);
    console.log("tel" + e.tel);
    console.log("email" + e.email);
    // 임시로 메인으로 돌아가게 함
    console.log(data);
    console.log(dummy_data);
    ChangeToModify();
  };
  // 바뀔때마다 setData로 수정된 데이터로 바꿔줌
  const handleChange = (e) => {
    e.preventDefault();
    const { name, value } = e.target;
    setData({ ...data, [name]: value });
  };

  // 그냥 My Page 컴포넌트
  const Main = () => {
    return (
      <>
        <div className="topText">
          <h2>내 정보</h2>
        </div>
        <div className="InfoWrapper">
          <div className="InfoTextField">
            <TextField
              id="outlined-read-only-input"
              label="담당 브랜드"
              defaultValue={dummy_data.brand}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="ID"
              defaultValue={dummy_data.id}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="PW"
              defaultValue={dummy_data.pw}
              InputProps={{
                readOnly: true,
              }}
              type="password"
              fullWidth="true"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="Tel."
              defaultValue={dummy_data.tel}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
            />
          </div>

          <div className="InfoTextField">
            <TextField
              label="Email"
              defaultValue={dummy_data.email}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
            />
          </div>
        </div>

        <div className="Button">
          <Button variant="contained" size="large" onClick={ChangeToModify}>
            수정
          </Button>
        </div>
      </>
    );
  };

  // "수정" My Page 컴포넌트
  const Modify = () => {
    return (
      <>
        <div className="topText">
          <h2>내 정보 수정</h2>
        </div>
        <form className="InfoWrapper" onSubmit={handleSubmit}>
          <div className="InfoTextField">
            {/* 이 부분 Form 으로 바꿔주기 */}
            <TextField
              label="담당 브랜드"
              defaultValue={data.brand}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
              disabled="true"
              variant="filled"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              id="outlined-disabled"
              label="ID"
              defaultValue={data.id}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
              disabled="true"
              variant="filled"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              required
              label="PW"
              defaultValue={data.pw}
              type="password"
              InputProps={{
                readOnly: !{ isModify },
              }}
              fullWidth="true"
              name="pw"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              required
              label="Tel."
              defaultValue={data.tel}
              InputProps={{
                readOnly: !{ isModify },
              }}
              fullWidth="true"
              name="tel"
              //   onChange={handleChange}
            />
          </div>

          <div className="InfoTextField">
            <TextField
              required
              label="Email"
              defaultValue={data.email}
              InputProps={{
                readOnly: !{ isModify },
              }}
              fullWidth="true"
              name="email"
              //   onChange={handleChange}
            />
          </div>
          <div className="Button">
            <Button variant="contained" size="large" type="submit">
              수정 완료
            </Button>
            <Button variant="contained" color="error" size="large" onClick={cancelModify}>
              취소
            </Button>
          </div>
        </form>
      </>
    );
  };
  return (
    <>
      <NavBar></NavBar>
      {/* 왼쪽 */}
      <div className="wrapper">
        <div id="left">
          <div className="topText">
            <h2>My Page</h2>
          </div>
          <div className="imageWrapper">
            <img src={`${process.env.PUBLIC_URL}/img/ManagerImage.png`} alt="NOIMAGE"></img>
          </div>
        </div>
        {/* 오른쪽 */}
        <div id="right">
          <div>{isModify ? <Modify /> : <Main />}</div>
        </div>
      </div>
      <Footer></Footer>
    </>
  );
}

export default ManagerMyPage;
