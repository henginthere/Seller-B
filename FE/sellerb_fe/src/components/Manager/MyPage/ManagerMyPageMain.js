/**
 * 
 * 
 * 관리자 My Page
 * 
 * 
 */
import React from "react";
import "./ManagerMyPageMain.css";
import Box from "@mui/material/Box";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";

function ManagerMyPageMain(props){
    // console.log(props);

    return <div id="wrapper">
    <div id="left">
        <div className="imageWrapper"><img src={`${process.env.PUBLIC_URL}/img/ManagerImage.png`} alt="NOIMAGE"></img></div>
    </div>
    <div id="right">
        <div>

        <h2>내 정보</h2>
        </div>
        <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 2, width: "50%" },
      }}
      noValidate
      autoComplete="off"
    >
            <div>
            <TextField
          id="outlined-read-only-input"
          label="담당 브랜드"
          defaultValue={props.data.brand}
          InputProps={{
            readOnly: true,
          }}
          variant="standard"
        /> <TextField
        id="outlined-read-only-input"
        label="ID"
        defaultValue={props.data.id}
        InputProps={{
          readOnly: true,
        }}
        variant="standard"
      /> <TextField
      id="outlined-read-only-input"
      label="Tel."
      defaultValue={props.data.tel}
      InputProps={{
        readOnly: true,
      }}
      variant="standard"
    /> <TextField
    id="outlined-read-only-input"
    label="Email"
    defaultValue={props.data.email}
    type="email"
    InputProps={{
      readOnly: true,
    }}
    variant="standard"
  /> <TextField
  id="outlined-read-only-input"
  label="Password"
  defaultValue={props.data.pw}
  type="password"
  InputProps={{
    readOnly: true,
  }}
  variant="standard"
/>
                
            </div>
        </Box>


    {/* 버튼 자리 */}
    <div className="Button">
    <Button variant="contained" size="large">수정</Button>

    </div>
    
    </div>
    </div>
}

export default ManagerMyPageMain;