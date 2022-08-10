import React from "react";
import isWeekend from "date-fns/isWeekend";
import TextField from "@mui/material/TextField";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { StaticDatePicker } from "@mui/x-date-pickers";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";

import { MediButton} from '../../Common/MediButton'

function ManagerMainLeft() {
  const [value, setValue] = React.useState(new Date());
  const navigate = useNavigate();
  const styleObj_WrapDiv = {
    width: "35%",
  };
  const styleObj_Button = {
    textAlign: "center",
    margin: "50px",
  };
  const welcome_text = {
    display: "flex",
    textAlign: "center",
    size: "20px",
    justifyContent: "center",
  };
  const blue = {
    color: "blue",
  };
  const mvMeetingManCon = () => {
    navigate("/meeting/mancon");
  };

  return (
    <>
      <div style={styleObj_WrapDiv}>
        <div style={welcome_text}>
          <h2 style={blue}>OOO</h2>
          <h2>님 환영합니다!</h2>
        </div>
        {/* 달력 */}
        <LocalizationProvider dateAdapter={AdapterDateFns}>
          <StaticDatePicker
            displayStaticWrapperAs='desktop'
            openTo='day'
            value={value}
            shouldDisableDate={isWeekend}
            onChange={(newValue) => {
              setValue(newValue);
            }}
            renderInput={(params) => <TextField {...params} />}
          />
        </LocalizationProvider>
        {/* 달력 END */}
        <div style={styleObj_Button}>
          <MediButton label="회의 생성" onClick={mvMeetingManCon} />
          {/* <Button variant='contained' onClick={mvMeetingManCon}>
            회의 생성
          </Button> */}
        </div>
      </div>
    </>
  );
}
export default ManagerMainLeft;
