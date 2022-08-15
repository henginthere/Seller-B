import React, { useState, useEffect, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";

import { Footer, NavBar } from "../../components/index";
import { MediButton } from "../../components/Common/MediButton";

import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";


import "./NoticeWrite.css"
import { registerNoticeApi } from "../../api/noticeApi";

function NotieWriteTwo() {
  const navigate = useNavigate();

  const [noticeTitle, setNoticeTitle] = useState("");
  const [noticeContent, setNoticeContent] = useState("");

  const [viewContent, setViewContent] = useState([]);

  const onNoticeTitleHandler = (e) => {
    setNoticeTitle(e.target.value);
    console.log("바뀐 Title: " + noticeTitle);
  };

  const onNoticeContentHandler = (e) => {
    setNoticeContent(e.target.value);
    console.log("바뀐 Content: " + noticeContent);
  };

  const onSubmitBtnHandler = (event) =>{
    // event.preventDefault(); 

    // const parseResult = noticeContent.

    let noticePostBody = {
      noticeTitle: noticeTitle,
      noticeContent: noticeContent
    };

    registerNoticeApi(noticePostBody)
      .then((res)=>{
        console.log(res.data);
        navigate("/manager/noticeList");
      })
      .catch((err)=>{
        console.log(err.data);
      })
  }

  const onCancleBtnHandler = () => {
    navigate("/manager/noticeList");
  }

  return (
    <>
      <NavBar />
      <div className="notice-list-wrapper">
      <div className='notice-title-wrapper'>
          <h4 className='notice-title-text'>공지사항</h4>
        </div>
        <div className="write-two-input-title">
          <input
            className="write-title"
            placeholder="제목을 입력해주세요.."
            value={noticeTitle}
            onChange={onNoticeTitleHandler}
          />
          <hr />
        </div>
        {/*  */}
        <div className="ckeditor-wrapper">
        <CKEditor
          className="ckeditor"
          editor={ClassicEditor}
          data="<p>내용을 작성해주세요!</p>"

          onReady={(editor) => {
            console.log("Editor is ready to use!", editor);
          }}

          onChange={(event, editor) => {
            const data = editor.getData(); 
            console.log({event, editor, data});
            setNoticeContent(data);

            console.log("바뀐 editor 내용: "+ noticeContent)
          }}
        />
        </div>
        {/*  */}
    <div className="notice-write-bottom-wrapper" >
        <MediButton onClick={onSubmitBtnHandler} label='등록' size='sm' />
        <MediButton onClick={onCancleBtnHandler} label='뒤로가기' />
    </div>
    </div>
    </>
  );
}

export default NotieWriteTwo;