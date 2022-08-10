import React, { useState, useEffect } from "react";
import "./ManagerMyPage.css";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import { getManagerInfoApi } from '../../../api/managerApi'

function ManagerMyPage() {
  
  const [info, setInfo] = useState([]);
  const [managerSeq, setManagerSeq] = useState(sessionStorage.getItem("seq"));

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
    
    ChangeToModify();
  };
  // 최종적으로 수정 완료 버튼을 누르면 api로 DB에 반영하기 위한 함수
  const handleSubmit = (e) => {
    e.preventDefault();

    alert("수정하시겠습니까?");

    ChangeToModify();
  };
  // 바뀔때마다 setData로 수정된 데이터로 바꿔줌
  const handleChange = (e) => {
    e.preventDefault();
    const { value, name } = e.target.value;

    setInfo({
      ...info,
      [name] : value
    })
  };

  const onModifyBtn = ()=>{
    console.log("modifyBtn: " + info.managerTel)
  }

  useEffect(()=>{
    console.log("useEffect:" + sessionStorage.getItem("seq"))
    getManagerInfoApi(managerSeq)
    .then((res)=>{
      console.log(JSON.stringify(res.data));
      setInfo(res.data);
      // console.log(info)
    })
    .catch((err)=>{
      console.log("error")
    })
  }, [])
  

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
              defaultValue={info.managerId}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='ID'
              defaultValue={info.managerId}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='PW'
              value={info.manager}
              InputProps={{
                readOnly: true,
              }}
              type='password'
              fullWidth='true'
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='Tel.'
              value={info.managerTel}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
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
            {/* <TextField
              label='담당 브랜드'
              defaultValue={info.brand.brandNameKor}
              InputProps={{
                readOnly: true,
              }}
              fullWidth='true'
              disabled='true'
              variant='filled'
            /> */}
          </div>
          <div className='InfoTextField'>
            <TextField
              id='outlined-disabled'
              label='ID'
              defaultValue={info.managerId}
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
              required
              label='PW'
              defaultValue=""
              type='password'
              fullWidth='true'
              name='managerPass'
              onChange={handleChange}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              required
              label='Tel.'
              defaultValue={info.managerTel}
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
            <Button onClick={()=> onModifyBtn()} variant='contained' size='large' type='submit'>
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
              src={`${process.env.PUBLIC_URL}/img/ManagerImage.png`}
              alt='NOIMAGE'
            ></img>
          </div>
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
