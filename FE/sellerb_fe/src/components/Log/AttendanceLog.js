import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import Axiso from "axios";

import "../../pages/Manager/Consultant/ConsultantDetail.css";
import { Footer, NavBar } from '../index';
import {
  listAttendanceApi
} from "../../api/consultantApi";
import { getStringDate } from '../../utils/date'


const styleObj_center = {
  textAlign: "center",
  margin: "50px",
};

const dummyData = [
  {
    date: "2022-07-25",
    login_time: "08:30",
    logout_time: "18:10",
  },
  {
    date: "2022-07-26",
    login_time: "08:35",
    logout_time: "18:21",
  },
];

//   function ConsultantLog (props) {
//     if(logOption === '출결이력'){
//         // console.log(params.consultants
//         return <AttendanceLog consultant_id = {seq} />
//     }
//     else{
//         return <ConsultingLog consultant_id = {seq} />
//     }
//  }


function AttendanceLog({consultant_id}) {
  console.log(consultant_id)
  const params = useParams();

  const [initData, setInitData] = useState([]);
  const [logData,setLogData] = useState([]);

  const [date, setDate] = useState("");
  const [loginTime, setLoginTime] = useState("");
  const [logOutTime, setLogOutTime] = useState("");

  // axios : 상담사 출결이력 표시
  useEffect(() => {
    console.log("in useEffect:" + consultant_id)

    listAttendanceApi(consultant_id)
      .then((res) => {
        console.log(JSON.stringify(res.data));

        setInitData(res.data);
        
        // logData = {initData.map((ele)=>{
        //   return (
        //     <>
            
        //     </>
        //   )
        // })}

        // setDate(getStringDate(logData));

      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffect(()=>{
    console.log(" 출결 initData: " + JSON.stringify(initData));

    // loginTime = initData.map((ele)=>{
    //   return (
    //     <>
    //     </>
    //   )
    // })

  })

  function CalLoginTime (props) {
    const time = props.getHours(); 
    console.log("in callLoginTime: " + time);
  
    return <td>time</td>
  }
  
  
  return (
    <>
      <div style={styleObj_center}>
        
        <table className="table-wrapper">
          <thead className="table-header-wrapper">
            <tr>
              <th>출근날짜</th>
              <th>출근시간</th>
              <th>퇴근시간</th>
            </tr>
          </thead>
          <tbody>
            {initData.map(function (ele, i) {
              return (
                <>
                  <tr>
                    {/* <td  onClick={() => navigate(`/noticeDetail/${ele.notice_seq}`)}>{ele.notice_title}</td> */}
                    <td>{ele.loginTime.slice(0,10)}</td>
                    <td>{ele.loginTime.slice(11,19)} </td>
                    <td>{ele.logoutTime.slice(11,19)}</td>
                  </tr>
                </>
              );
            })}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default AttendanceLog;
