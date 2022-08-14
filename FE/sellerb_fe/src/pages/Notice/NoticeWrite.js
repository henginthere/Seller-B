import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { SmallButton } from "../../components/Common/SmallButton";

import { registerNoticeApi } from "../../api/noticeApi";
import "./NoticeWrite.css";
import { Footer, NavBar } from "../../components/index";
import getStringDate from "../../utils/date";

function NoticeWrite() {
  const navigate = useNavigate();

  const [noticeTitle, setNoticeTitle] = useState("");
  const [noticeContent, setNoticeContent] = useState("");

  const onNoticeTitleHandler = (event) => {
    setNoticeTitle(event.currentTarget.value);
    // test
    // console.log(noticeTitle);
  };

  const onNoticeContentHandler = (event) => {
    setNoticeContent(event.currentTarget.value);
    // test
    // console.log(noticeContent);
  };

  const onSubmitBtnHandler = (event) => {
    // event.preventDefault();

    if (window.confirm("공지사항을 등록하시겠습니까?")) {
      let noticePostBody = {
        noticeTitle: noticeTitle,
        noticeContent: noticeContent,
      };
      console.log(noticePostBody);

      registerNoticeApi(noticePostBody)
        .then((res) => {
          console.log(res.data);
          navigate("/manager/noticeList");
        })
        .catch((err) => {
          console.log(err.data);
        });
    }
    // axios

    // Axios.post('/notice', noticePostBody)
    // .then((res)=> console.log("success"))
    // .catch((err) => console.log("err"))
  };

  const onResetBtnHandler = () => {
    setNoticeTitle("");
    setNoticeContent("");
  };

  const onCloseBtnHandler = () => {
    navigate("/manager/noticeList", { replace: true });
  };

  return (
    <>
      <NavBar />
      <div className='consultant-main-image-wrapper'>
        <img
          src={`${process.env.PUBLIC_URL}/img/consultantMainPageImage.jpg`}
        />
      </div>
      <div className='notice-write-header'>공지사항 작성</div>
      <div className='write-form-wrapper'>
        <div className='write-input-title'>
          <input
            className='write-title'
            placeholder='제목을 입력해주세요..'
            value={noticeTitle}
            onChange={onNoticeTitleHandler}
          />
          <hr />
        </div>

        <div className='write-input-content'>
          <input
            className='write-content'
            placeholder='내용을 입력해주세요 ..'
            value={noticeContent}
            onChange={onNoticeContentHandler}
          />
        </div>
        <div className='btns-wrapper'>
          <SmallButton onClick={onCloseBtnHandler} label='뒤로' size='sm' />
          {/* <button className='back-btn' onClick={onCloseBtnHandler}>
            뒤로
          </button> */}
          <SmallButton onClick={onSubmitBtnHandler} label='등록' size='sm' />
          {/* <button className='register-btn' onClick={onSubmitBtnHandler}>
            등록
          </button> */}
          <SmallButton onClick={onResetBtnHandler} label='초기화' size='sm' />
          {/* <button className='reset-btn' onClick={onResetBtnHandler}>
            초기화
          </button> */}
        </div>
      </div>

      <Footer />
    </>
  );
}

export default NoticeWrite;
