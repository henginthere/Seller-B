import React, { useState } from "react";
import { Link } from "react-router-dom";
// import Axios from 'axios'

// 1. navbar, footer import
import "./NoticeWrite.css";
import { Footer, NavBar } from "../../components/index";
// calculate Date Util
import getStringDate from "../../utils/date";


function NoticeWrite() {

    const [noticeTitle, setNoticeTitle] = useState("");
    const [noticeContent, setNoticeContent] = useState("");

    const onNoticeTitleHandler = ((e)=>{
        
    })



  return (
    <>
    <NavBar />  
        <div className="notice-write-header">공지사항 작성</div>
        <div className="write-form-wrapper">
            <div className="write-input-title">
                <input 
                    className="write-title"
                    placeholder="제목을 입력해주세요.." />
                 <hr />
            </div>
            
            <div className="write-input-content">
                <input
                    className="write-content"
                    placeholder="내용을 입력해주세요 .." 
                />
            </div>
            <div>
            <button>닫기</button>
            <button>초기화</button>
            <button>등록</button>
        </div>
        </div> 
        

    <Footer />
    </>
  )
}

export default NoticeWrite