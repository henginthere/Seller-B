import React, { useState, useEffect } from "react";
import "./ManagerMyPage.css";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import { getManagerInfoApi } from "../../../api/managerApi";
import { indigo } from "@mui/material/colors";

import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";

function ManagerMyPage() {
  const [info, setInfo] = useState([]);
  const [managerSeq, setManagerSeq] = useState(sessionStorage.getItem("seq"));
  const [brandNameKor, setBrandNameKor] = useState("");

  // 비동기로 처리하기 위함. useState로 바로바로 적용!
  const [isModify, setModify] = useState(false);
  // 수정된 데이터  --> data
  // var data = { ...dummy_data };
  // 수정 페이지 인지 아닌지 바꿔주는 함수
  const ChangeToModify = () => {
    setModify(!isModify);
    console.log("isModify : " + isModify);
  };
  

  const cancelModify = () => {
    getManagerInfoApi(managerSeq)
      .then((res) => {
        // console.log(JSON.stringify(res.data));
        setInfo(res.data);
        setBrandNameKor(res.data.brand.brandNameKor);
      })
      .catch((err) => {
        console.log(err);
      });
    ChangeToModify();
  };
  // 최종적으로 수정 완료 버튼을 누르면 api로 DB에 반영하기 위한 함수
  const handleSubmit = (e) => {
    e.preventDefault();

    alert("수정하시겠습니까?");
    console.log(info);
    ChangeToModify();
  };
  // 바뀔때마다 setData로 수정된 데이터로 바꿔줌
  const handleChange = (e) => {
    e.preventDefault();
    const { value, name } = e.target;
    console.log("[value, name] : " + " [" + value + ", " + name + "]");
    setInfo({
      ...info,
      [name]: value,
    });
  };

  const onModifyBtn = () => {
    console.log("modifyBtn: " + info.managerTel);
  };

  useEffect(() => {
    console.log("useEffect:" + sessionStorage.getItem("seq"));
    getManagerInfoApi(managerSeq)
      .then((res) => {
        // console.log(JSON.stringify(res.data));
        setInfo(res.data);
        setBrandNameKor(res.data.brand.brandNameKor);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  // 그냥 My Page 컴포넌트
  const Main = () => {
    return (
      <>
        <div className='topText'>
          <h2>내 정보</h2>
        </div>
        <div className='InfoWrapper'>
          <div className='InfoTextField'>
            <TextField
              id='outlined-read-only-input'
              label='담당 브랜드'
              value={brandNameKor}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='ID'
              value={info.managerId}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
            />
          </div>
          {/* <div className='InfoTextField'>
            <TextField
              label='PW'
              value={info.pass}
              InputProps={{
                readOnly: true,
              }}
              type='password'
              fullWidth='true'
              name='pass'
              onChange={handleChange}
            />
          </div> */}
          <div className='InfoTextField'>
            <TextField
              label='Tel.'
              value={info.managerTel}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
              name='managerTel'
              onChange={handleChange}
            />
          </div>

          <div className='InfoTextField'>
            <TextField
              label='Email'
              value={info.managerEmail}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
              name='managerEmail'
              onChange={handleChange}
            />
          </div>
        </div>
        <div className='Button'>
          <Button variant='contained' size='large' onClick={ChangeToModify}>
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
        <div className='topText'>
          <h2>내 정보 수정</h2>
        </div>
        <form className='InfoWrapper' onSubmit={handleSubmit}>
          <div className='InfoTextField'>
            {/* 이 부분 Form 으로 바꿔주기 */}
            <TextField
              label='담당 브랜드'
              defaultValue={brandNameKor}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
              disabled='true'
              variant='filled'
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              id='outlined-disabled'
              label='ID'
              value={info.managerId}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
              disabled='true'
              variant='filled'
            />
          </div>
          {/* <div className='InfoTextField'>
            <TextField
              required
              label='PW'
              value={info.pass}
              type='password'
              fullWidth='true'
              name='managerPass'
              onChange={handleChange}
            />
          </div> */}
          <div className='InfoTextField'>
            <TextField
              required
              label='Tel.'
              value={info.managerTel}
              fullWidth='true'
              name='managerTel'
              onChange={handleChange}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              required
              label='Email'
              defaultValue={info.managerEmail}
              InputProps={{
                readOnly: !{ isModify },
              }}
              fullWidth='true'
              name='managerEmail'
              onChange={handleChange}
            />
          </div>
          <div className='Button'>
            <Button
              onClick={() => onModifyBtn()}
              variant='contained'
              size='large'
              type='submit'
            >
              수정 완료
            </Button>
            <Button
              variant='contained'
              color='error'
              size='large'
              onClick={cancelModify}
            >
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
      <div className='wrapper'>
        <div id='left'>
          <div className='topText'>
            <h2>My Page</h2>
          </div>
          <div className='imageWrapper'>
            <img
              src={info.managerImageUrl}
              alt='NOIMAGE'
            ></img>
          </div>
          {/* 이미지 업로듭 부분 */}
          
        </div>
        {/* 오른쪽 */}
        <div id='right'>
          <div>{isModify ? <Modify /> : <Main />}</div>
        </div>
      </div>
      <Footer></Footer>
    </>
  );
}

export default ManagerMyPage;
