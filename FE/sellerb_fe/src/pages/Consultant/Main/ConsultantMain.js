import React from "react";

import NavBar from "../../../components/Common/NavBar/NavBar";
import "./ConsultantMain.css";
import styled from "styled-components";

function ConsultantMain() {

  const tableDummyData = [
    {
      "No": 1,
      "제품명" : "SHA-16A",
    },
    {
      "No": 2,
      "제품명" : "SHA-16B",
    },
    {
      "No": 3,
      "제품명" : "SHA-16C",
    }
  ]

  return (
    <>
      <NavBar></NavBar>
      <div className="consultant-main-wrapper">
        <div className="welcome-msg">---님 환영합니다!</div>
        <div className="notice-consulting-wrapper">
          <div className="notice">
            <div className="notice-title">공지사항</div>
            <hr />
          </div>
          <div className="consulting-request">
            <div className="reqeust-title">상담신청 내역</div>
            <hr />
            {/* table START*/}
            <table class="content-table">
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
    </>
  );
}

export default ConsultantMain;
