import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Link, useNavigate, useParams } from "react-router-dom";
import "./NoticeWrite.css";
import { Footer, NavBar } from "../../components/index";
import Button from "@mui/material/Button";
// api
import {
  detailNoticeApi,
  modifyNoticeApi,
  delNoticeApi,
} from "../../api/noticeApi";


const NoticeWrapper = styled.div`
  padding: 3rem 5rem;
  box-sizing: border-box;
  width: 100%;
  overflow: auto;
`;

// 매니저 or 상담사 버튼 보이는 관리
const adminCheck = localStorage.getItem("adminCheck");

//////////////////////////////////////////////////

const NoticeDetail = () => {
  const params = useParams();

  const navigate = useNavigate();
  const [noticeData, setNoticeData] = useState({
    noticeSeq: "",
    noticeTitle: "",
    noticeContent: ""
  });
  const { noticeSeq, noticeTitle, noticeContent } = noticeData;
  const [isModify, setIsModify] = useState(false);

  // const dispatch = useDispatch();
  const handleChange = (e) => {
    e.preventDefault();
    const { value, name } = e.target; 

    setNoticeData({
      ...noticeData,
      [name] : value, 
    })
  };

  // mount시점에서, 공지사항 id에 해당하는 게시글 받아오기
  useEffect(() => {
    detailNoticeApi(params.id)
      .then((res) => {

        setNoticeData(res.data);
      })
      .catch((e) => {
        console.log("err");
      });
  }, []);

  const delNotice = () => {
    if (window.confirm("삭제하시겠습니까?")) {
      console.log(
        "noticeData.noticeSeq in delNotice : " + noticeData.noticeSeq,
      );
      delNoticeApi(noticeData.noticeSeq);
      navigate("/manager/noticeList");
    } else {
      return;
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    alert("수정하시겠습니까?");

    modifyNoticeApi(noticeData)
    .then((res)=>{
      console.log("Success")
    })
    .catch((err)=>{
      console.log("Error");
    })
    setIsModify(!isModify);
  };

  const goDetail = (e) =>{
    e.preventDefault();

    navigate(`/manager/noticeEdit/:${noticeData.noticeSeq}`)
  }

  return (
    <>
      <NavBar />
      <NoticeWrapper>
        <div className='notice-write-header'>공지사항 내용</div>
        <div className='notice-detail-gotoList'>
          <Link to='/manager/noticeList'>
            <Button>목록</Button>
          </Link>
        </div>
        <div className='write-form-wrapper'>
        <div className='write-input-title'>
          <input
            readOnly={true}
            className='write-title'
            defaultValue={noticeData.noticeTitle}
          />
        </div>
        <div className='write-input-content'>
          <input
            readOnly={true}
            className='write-content'
            defaultValue={noticeData.noticeContent}
          />
        </div>
        <div>
          {/* {adminCheck === "ROLE_ADMIN" ? ( */}
            <div>
              <span className='noticedetail-button-wrapper'>
                <Button
                  variant='outlined'
                  onClick={(e)=> goDetail()}
                >
                  수정
                </Button>
              </span>
              <span className='noticedetail-button-wrapper'>
                <Button variant='outlined' color='error' onClick={delNotice}>
                  삭제
                </Button>
              </span>
            </div>
          {/* ) : null */}
        </div>
      </div>

      </NoticeWrapper>
      <Footer />
    </>
  );
};

export default NoticeDetail;