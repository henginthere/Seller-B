import React, { useEffect, useState } from "react";
import isWeekend from "date-fns/isWeekend";
import TextField from "@mui/material/TextField";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { StaticDatePicker } from "@mui/x-date-pickers";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";

import { MediButton } from "../../Common/MediButton";
import { getManagerInfoApi } from "../../../api/managerApi";

function ManagerMainLeft() {
  const [value, setValue] = React.useState(new Date());
  const [managerName, setManagerName] = useState("");

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
    marginTop: "20px",
  };
  const blue = {
    color: "blue",
  };
  const mvMeetingManCon = () => {
    navigate("/meeting/mancon");
  };

  useEffect(() => {
    const managerSeq = sessionStorage.getItem("seq");
    console.log(managerSeq);

    getManagerInfoApi(managerSeq)
      .then((res) => {
        setManagerName(res.data.managerName);
      })
      .catch((err) => {
        console.log("Error");
      });
  });

  return (
    <>
      <div style={styleObj_WrapDiv}>
        <div style={welcome_text}>
          <h2 style={blue}>{managerName}</h2>
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
          <MediButton label='회의 생성' onClick={mvMeetingManCon} />
        </div>
      </div>
    </>
  );
}
export default ManagerMainLeft;
