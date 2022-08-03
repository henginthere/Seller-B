import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Link, useNavigate, useParams } from "react-router-dom";
import "./NoticeWrite.css";
import { Footer, NavBar } from "../../components/index";
// api 
import { detailNoticeApi } from "../../api/noticeApi";

import { getStringDate } from "../../utils/date";

const NoticeWrapper = styled.div`
  padding: 3rem 5rem;
  box-sizing: border-box;
  width: 100%;
  overflow: auto;
`;

const NoticeDetail = () => {
  const params = useParams();
 
  console.log(params.notice_seq);

  const navigate = useNavigate();

  const [readOnly, setReadOnly] = useState(true)
  const [noticeData, setNoticeData] = useState({
    title: "",
    content: "", 
  })

  // const dispatch = useDispatch();

// mount시점에서, 공지사항 id에 해당하는 게시글 받아오기 
  useEffect(()=>{
    detailNoticeApi(params.id)
    .then((res)=>{
        // response에 대해 비구조화 할당
        setNoticeData(res.data);
        console.log(noticeData);
    })
    .catch((e)=>{
        console.log("err")
    })
  }, [])

  return (
    <>
      <NavBar />
      <NoticeWrapper>
        <div className="notice-write-header">공지사항 내용</div>
        <div className="write-form-wrapper">
          <div className="write-input-title">
            <input
              readOnly= {readOnly}
              className="write-title"
              placeholder="DetailPage : 수정불가"
            />
          </div>
          <div className="write-input-content">
            <input
              readOnly={readOnly}
              className="write-content"
              placeholder="DetailPage : 수정불가"
            />
          </div>
          <div>
            <Link to="/manager/noticeList">
              <button>뒤로</button>
            </Link>
            <Link to="/manager/noticeEdit">
            <button>수정</button>
            </Link>
          </div>
        </div>
      </NoticeWrapper>
      <Footer />
    </>
  );
};

export default NoticeDetail;
