import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import "../../pages/Manager/Consultant/ConsultantDetail.css";
import { listAttendanceApi } from "../../api/consultantApi";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  TableContainer,
  Tab,
} from "@mui/material";

const styleObj_center = {
  textAlign: "center",
  margin: "50px",
};


const addZero = (n) => {
  return n < 10 ? "0" + n : n;
};
function AttendanceLog({ consultant_id }) {
  // console.log(consultant_id);
  const params = useParams();

  const [initData, setInitData] = useState([]);
  const [logData, setLogData] = useState([]);

  const [date, setDate] = useState("");
  const [loginTime, setLoginTime] = useState("");
  const [logOutTime, setLogOutTime] = useState("");

  // axios : 상담사 출결이력 표시
  useEffect(() => {
    console.log("in useEffect:" + consultant_id);

    listAttendanceApi(consultant_id)
      .then((res) => {

        setInitData(res.data);

        let list = initData.map((ele) => ele.loginTime);
        list = list.slice();
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);


  const parsingDate = (date) => {
    var parsedDate = new Date(date);
    var yyyy = parsedDate.getFullYear();
    var MM = parsedDate.getMonth() + 1;
    var dd = parsedDate.getDate();
    var hh = parsedDate.getHours() + 9;
    var mm = parsedDate.getMinutes();
    if (hh > 24) {
      hh = hh - 24;
      dd = dd + 1;
      setDate(date + 1);
    }

    return (
      yyyy +
      "-" +
      addZero(MM) +
      "-" +
      addZero(dd) +
      "-" +
      addZero(hh) +
      ":" +
      addZero(mm)
    );
  };
  function CalLoginTime(props) {
    const time = props.getHours();
    console.log("in callLoginTime: " + time);

    return <td>time</td>;
  }

  return (
    <>
      <div style={styleObj_center}>
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>출근날짜</TableCell>
                <TableCell>출근시간</TableCell>
                <TableCell>퇴근시간</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {initData.map(function (ele, i) {
                return (
                  <>
                    <TableRow>
                      {}
                      <TableCell>
                        {parsingDate(ele.loginTime).slice(0, 10)}
                      </TableCell>
                      <TableCell>
                        {parsingDate(ele.loginTime).slice(11)}{" "}
                      </TableCell>
                      <TableCell>
                        {parsingDate(ele.logoutTime).slice(11)}
                      </TableCell>
                    </TableRow>
                  </>
                );
              })}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    </>
  );
}

export default AttendanceLog;
