/**
 *
 *
 * 관리자 My Page
 *
 *
 */
import React from "react";
import "./ManagerMyPageMain.css";

import { TextField } from "@mui/material";
import Button from "@mui/material/Button";

function ManagerMyPageMain(props) {

  return (
    <div id="wrapper">
      <div id="right">
        <div>
          <h2>내 정보 수정</h2>
        </div>
        <div className="InfoWrapper">
          <div className="InfoTextField">
            <TextField
              id="outlined-disabled"
              label="담당 브랜드"
              defaultValue={props.data.brand}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              id="outlined-read-only-input"
              label="ID"
              defaultValue={props.data.id}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              id="outlined-read-only-input"
              label="PW"
              defaultValue={props.data.pw}
              InputProps={{
                readOnly: true,
              }}
              type="password"
              fullWidth="true"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              id="outlined-read-only-input"
              label="Tel."
              defaultValue={props.data.tel}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
            />
          </div>

          <div className="InfoTextField">
            <TextField
              id="outlined-read-only-input"
              label="Email"
              defaultValue={props.data.email}
              InputProps={{
                readOnly: true,
              }}
              fullWidth="true"
            />
          </div>
        </div>

        {/* 버튼 자리 */}
        <div className="Button">
          <Button variant="contained" size="large">
            수정
          </Button>
        </div>
      </div>
    </div>
  );
}

export default ManagerMyPageMain;
