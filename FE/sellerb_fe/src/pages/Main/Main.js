import React, { useState } from 'react'
import { Link } from 'react-router-dom';

import './Main.css'

// import material ui
import { styled } from "@mui/material/styles"
import  TextField  from '@mui/material/TextField';
import Button from '@mui/material/Button'
import Checkbox from '@mui/material/Checkbox';
import FormControlLabel from '@mui/material/Checkbox';


function Main() {

  // const MyTextField = style
  let [id, setId] = useState("");

  const onIdHandler = (e)=>{
    console.log(e.target.value)
    setId(e.target.value)
  }

  const onLoginButtonHandler = () =>{
    
  }

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
              borderColor: 'yellow',
              height: '25%',
              boxShadow: 1,
              width: 200,
              marginLeft: 13,
              fontSize: 15,
            }}
            style={{marginTop: '15px', marginBottom : '10px', display: 'block'}}
            value={id}
            onChange={onIdHandler}
            label='ID'
            />
            <TextField 
            sx={{
              borderColor: 'yellow',
              height: '25%',
              boxShadow: 1,
              width: 200,
              marginLeft: 13,
            }}
            style={{marginBottom : '10px', display: 'block'}}
            label='PASSWORD'
            />
     
            <Button
              sx = {{
                bgcolor: '#FCF0D2',
                fontcolor: '#324E66',
                marginLeft: 13,
                width: 200,
              }}
              > <Link to={'/manager/main'}>Login</Link>
              </Button>
    
              <div className='FormControlLabel-wrapper'>
              <FormControlLabel 
                style={{ marginLeft: '90px'}} 
                label="checkManagerLogin" 
                
              />
              관리자 로그인
              </div>
              <div className='register-resetPassword'>
              <Link to="/manager/register">
                <div className='manager-register'>
                  관리자 회원가입
                </div>
                </Link>
                <div className='reset-pssword'>
                  <Link to="#">
                  비밀번호 재설정
                  </Link>
                  </div>
              </div>
        </form>
        {/* Form end */}
      </div>
    </div>

  {/* Main화면 오른쪽 이미지 */}
    <div className='main-right'>
      <img className='main-bg-img' alt='#' src="img/Main-bg-img.png" />
    </div>

    </div>
    </>
  )
}

export default Main