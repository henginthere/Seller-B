import React, {useEffect, useState} from "react";

import { Footer, NavBar } from "../../../components/index";
import "./ConsultantMain.css";
import styled from "styled-components";
import { goWorkApi, leaveWorkApi } from "../../../api/consultantApi";

function ConsultantMain() {

  const [conSeq, setConSeq] = useState("");
  const tableDummyData = [
    {
      No: 1,
      제품명: "SHA-16A",
    },
    {
      No: 2,
      제품명: "SHA-16B",
    },
    {
      No: 3,
      제품명: "SHA-16C",
    },
  ];

  useEffect(()=>{
    const val = sessionStorage.getItem("seq");

    setConSeq(val);

    console.log(conSeq);

  }, [])

  const goWorkBtn = () =>{
    const Info = {
      consultantSeq : conSeq
    }
    
    goWorkApi(Info)
    .then((res)=>{
      console.log("success");
    })
    .catch((err)=>{
      console.log("Error");
    })


  }
  
  const leaveWorkBtn = ()=>{
    leaveWorkApi(conSeq)
    .then((res)=>{
      console.log("Success");
    })
    .catch((err)=>{
      console.log("Error");
    })

  }

  return (
    <>
      <NavBar></NavBar>
      <div className="consultant-main-wrapper">

        <div className="main-header">
          <div className="header-left">
            <div className="left-content-comment">
              oo님 환영합니다!
            </div>
            <div className="attend-wrapper">
              <div className="go-btn-wrapper" onClick={goWorkBtn}>
                <div className="go-btn" >
                  출근
                </div>
              </div>
              <div className="leave-btn-wrapper" onClick={leaveWorkBtn}>
              <div className="leave-btn" >
                  퇴근
                </div>
              </div>
            </div>
          </div>
          {/* <div className="attendance-wrappper">
            <div>출근</div>
            <div>퇴근</div>
          </div> */}
        </div>
        <div className="notice-consulting-wrapper">
          <div className="notice">
            <div className="notice-title">공지사항</div>
            <hr />
          </div>
          <div className="consulting-request">
            <div className="reqeust-title">상담신청 내역</div>

            <hr />

            {/* table START*/}
            <table className="content-table">
              <thead>
                <tr>
                  <th>No</th>
                  <th>제품명</th>
                  <th>수락</th>
                  <th>거절</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td>SHA-16AA</td>
                  <td>
                    <button className="accept-btn">o</button>
                  </td>
                  <td>
                    <button className="reject-btn">o</button>
                  </td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>SPT-A134</td>
                  <td>
                    <button className="accept-btn">o</button>
                  </td>
                  <td>
                    <button className="reject-btn">o</button>
                  </td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>QTA-BC12</td>
                  <td>
                    <button className="accept-btn">o</button>
                  </td>
                  <td>
                    <button className="reject-btn">o</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>


      </div>
      <Footer />
    </>
  );
}

export default ConsultantMain;
