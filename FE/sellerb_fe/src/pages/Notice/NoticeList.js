import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import styled from "styled-components";
// import Axios from 'axios'
// import { listNoticeApi } from "../../api/noticeApi";

import "./NoticeList.css";
import { Footer, NavBar } from "../../components/index";

import getStringDate from "../../utils/date";

const dummyNoticeList = [
  {
    notice_seq: 1,
    notice_title: "first Notice Title",
    notice_reg_date: getStringDate(new Date()),
  },
  {
    notice_seq: 2,
    notice_title: "Two Notice Title",
    notice_reg_date: getStringDate(new Date()),
  },
  {
    notice_seq: 3,
    notice_title: "Three Notice Title",
    notice_reg_date: getStringDate(new Date()),
  },
];

function NoticeList() {
  const navigate = useNavigate();  

  const [noticeList, setNoticeList] = useState(""); // -> api res.data 로 값 갱신해주기
  const [searchTitle, setSearchTitle] = useState("");

  const onSearchByTitleHandler = (e) => {
    setSearchTitle(e.target.value);
  };

  const submitSearchByTitle = (e) => {
    // Test
    console.log(searchTitle);
    // Axios
  };

  // useEffect
  // useEffect(()=>{
  //   listNoticeApi()
  //   .then((res)=>{
  //     noticeList = res.data; 
  //     console.log(res.data)
  //   })
  //   .catch((err)=>{
  //     console.log(err);
  //   })
  // })

  return (
    <>
      <NavBar />

      <div className="notice-title">공지사항</div>
      <div className="notice-wrapper">
        <div className="search-byTitle-wrapper">
          <input
            className="search-byTitle"
            placeholder="제목으로 검색하기"
            value={searchTitle}
            onChange={onSearchByTitleHandler}
          />
          <button onClick={submitSearchByTitle}>검색</button>
        </div>
        <div className="notice-list">
          <table>
            <thead>
              <tr>
                <th>No</th>
                <th>Title</th>
                <th>Date</th>
              </tr>
            </thead>
            <tbody>
              {dummyNoticeList.map(function (ele, i) {
                return (
                  <tr>
                    <td>{ele.notice_seq}</td>
                    <td  onClick={() => navigate(`/noticeDetail/${ele.notice_seq}`)}>{ele.notice_title}</td>
                    <td>{ele.notice_reg_date}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
        <div className="notice-write-wrapper">
          <Link to="/manager/noticeWrite">
           
          
            <button className="write-btn">글작성</button>
          </Link>
        
        </div>
      </div>
      <Footer />
    </>
  );
}

export default NoticeList;
