import React, { useEffect, useState } from "react";

import { Footer, NavBar } from "../../../components/index";
import "./ConsultantMain.css";
import styled from "styled-components";
import {
  goWorkApi,
  leaveWorkApi,
  detailConsultantApi,
} from "../../../api/consultantApi";
import { listNoticeApi } from "../../../api/noticeApi";

function ConsultantMain() {
  const [conSeq, setConSeq] = useState(""); // 상담사 seq
  const [conName, setConName] = useState(""); // 상담사 이름

  // const [today, setToday] = useState(new Date().getFullYear() + "-" + (new Date().getMonth()+1) + "-" + new Date().getDate());
  const [today, setToday] = useState(new Date());
  const [items, setItems] = useState([]);
  const [noticeList, setNoticeList] = useState([]); //

  useEffect(() => {
    const val = sessionStorage.getItem("seq");
    setConSeq(val);

    detailConsultantApi(val)
      .then((res) => {
        setConName(res.data.consultantName);
      })
      .catch((err) => {
        console.log("Error");
      });
  }, []);

  useEffect(() => {
    console.log("Today : " + today);
    listNoticeApi()
      .then((res) => {
        console.log(res.data);
        setItems(res.data);

        console.log(items);

        const items = res.data.filter(
          (it) => it.noticeRegDate.getDate() === today.getDate(),
        );
        console.log("오늘 새로운 공지사항 갯수:");
      })
      .catch((err) => {});
  }, []);

  const goWorkBtn = () => {
    const Info = {
      consultantSeq: conSeq,
    };

    goWorkApi(Info)
      .then((res) => {
        console.log("success");
        alert("출근완료!");
      })
      .catch((err) => {
        console.log("Error");
      });
  };

  const leaveWorkBtn = () => {
    leaveWorkApi(conSeq)
      .then((res) => {
        console.log("Success");

        alert("퇴근완료!");
      })
      .catch((err) => {
        console.log("Error");
      });
  };

  return (
    <>
      <NavBar></NavBar>
      <div className='consultant-main-wrapper'>
        <div className='main-header'>
          <div className='header-left'>
            <div className='left-content-comment'>
              {conName} 님, 환영합니다!
            </div>
            <div className='attend-wrapper'>
              <div className='go-btn-wrapper' onClick={goWorkBtn}>
                <div className='go-btn'>출근</div>
              </div>
              <div className='leave-btn-wrapper' onClick={leaveWorkBtn}>
                <div className='leave-btn'>퇴근</div>
              </div>
            </div>
          </div>
          {/* <div className="attendance-wrappper">
            <div>출근</div>
            <div>퇴근</div>
          </div> */}
        </div>
        <div className='notice-consulting-wrapper'>
          <div className='notice'>
            <div className='notice-title'>공지사항</div>
            <hr />
            <div></div>
          </div>
          <div className='consulting-request'>
            <div className='reqeust-title'>상담신청 내역</div>
            <hr />
            <div>아직 들어온 상담 신청이 없습니다!</div>

            {/* 새로 들어온 상담신청 띄울 영역 START*/}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default ConsultantMain;
