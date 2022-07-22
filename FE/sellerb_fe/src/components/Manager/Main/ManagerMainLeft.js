import React from "react";
import isWeekend from "date-fns/isWeekend";
import TextField from "@mui/material/TextField";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { StaticDatePicker } from "@mui/x-date-pickers/StaticDatePicker";
import Button from "@mui/material/Button";

function ManagerMainLeft() {
  const [value, setValue] = React.useState(new Date());
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
  return (
    <div style={styleObj_WrapDiv}>
      <div style={styleObj_Button}>ManagerMainLeft</div>
      <div style={welcome_text}>
        <h2 style={blue}>OOO</h2>
        <h2>님 환영합니다!</h2>
      </div>
      {/* 달력  */}
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
        <Button variant='contained'>회의 생성</Button>
      </div>
    </div>
  );
}
export default ManagerMainLeft;
