import React, { useState } from "react";
import { Link } from "react-router-dom";
// import Axios from 'axios'

// 1. navbar, footer import
import "./Notice.css";
import { Footer, NavBar } from "../../components/index";
// calculate Date Util
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
  // search title
  const [searchTitle, setSearchTitle] = useState("");

  const onSearchByTitleHandler = (e) => {
    setSearchTitle(e.target.value);
  };

  const submitSearchByTitle = (e) => {
    // Test
    console.log(searchTitle);
    // Axios
  };

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
                    <td>{ele.notice_title}</td>
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
