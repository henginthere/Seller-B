import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

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

//   function ConsultantLog (props) {
//     if(logOption === '출결이력'){
//         // console.log(params.consultants
//         return <AttendanceLog consultant_id = {seq} />
//     }
//     else{
//         return <ConsultingLog consultant_id = {seq} />
//     }
//  }

function AttendanceLog({ consultant_id }) {
  console.log(consultant_id);
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
        console.log(JSON.stringify(res.data));

        setInitData(res.data);

        let list = initData.map((ele) => ele.loginTime);
        console.log("반환된 list : " + list);
        list = list.slice();
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffect(() => {
    console.log(" 출결 initData: " + JSON.stringify(initData));

    // loginTime = initData.map((ele)=>{
    //   return (
    //     <>
    //     </>
    //   )
    // })
  });

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
                      {/* <td  onClick={() => navigate(`/noticeDetail/${ele.notice_seq}`)}>{ele.notice_title}</td> */}
                      <TableCell>{ele.loginTime.slice(0, 10)}</TableCell>
                      <TableCell>{ele.loginTime.slice(11, 19)} </TableCell>
                      <TableCell>{ele.logoutTime.slice(11, 19)}</TableCell>
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
