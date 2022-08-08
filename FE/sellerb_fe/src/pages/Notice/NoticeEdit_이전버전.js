import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Link, useNavigate, useParams } from "react-router-dom";
import "./NoticeWrite.css";
import { Footer, NavBar } from "../../components/index";
// api 
import { detailNoticeApi, modifyNoticeApi, delNoticeApi} from "../../api/noticeApi";

import { getStringDate } from "../../utils/date";

const NoticeWrapper = styled.div`
  padding: 3rem 5rem;
  box-sizing: border-box;
  width: 100%;
  overflow: auto;
`;

const NoticeEdit = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const onTitleHandler = (event)=>{
    let newTitle = event.currentTarget.value;
    setTitle(newTitle);
  }

  const onContentHandler = (event) => {
    let newContent = event.currentTarget.value;
    setContent(newContent);
  }

  const onEditBtn = () =>{
    const postData = {
      noticeSeq: id,
      noticeTitle: title,
      noticeContent: content
    }

    modifyNoticeApi(postData)
    .then((res)=>{
      console.log("success");
    })
    .catch((err)=>{
      console.log("error")
    })
  }


//   const dispatch = useDispatch();

// mount시점에서, 공지사항 id에 해당하는 게시글 받아오기 
//   useEffect(()=>{
//     detailNoticeApi(params.id)
//     .then((res)=>{
//         // response에 대해 비구조화 할당
//         setNoticeData(res.data);
//         console.log(noticeData);
//     })
//     .catch((e)=>{
//         console.log("err")
//     })
//   })

  return (
    <>
      <NavBar />
      <NoticeWrapper>
        <div className="notice-write-header">공지사항 수정</div>
        <div className="write-form-wrapper">
          <div className="write-input-title">
            <input
              className="write-title"
              placeholder="NoticeEdit : 수정가능"
              value={title}
              onChange={onTitleHandler}
            />
          </div>

          <div className="write-input-content">
            <input
              className="write-content"
              placeholder="NoticeEdit : 수정가능"
              value={content}
              onChange={onContentHandler}
            />
          </div>
          <div>
            <Link to="/manager/noticeList">
              <button>목록</button>
            </Link>
            <Link to="/manager/noticeList">
            <button onClick={onEditBtn}>수정완료</button>
            </Link>
          </div>
        </div>
      </NoticeWrapper>
      <Footer />
    </>
  );
};

export default NoticeEdit;
