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
var isManager = true;

const NoticeDetail = () => {
  const params = useParams();

  console.log("params noticeSeq : " + params.notice_seq);

  const navigate = useNavigate();
  const [noticeData, setNoticeData] = useState({
    noticeSeq: "",
    noticeTitle: "",
    noticeContent: "",
  });
  var { noticeSeq, noticeTitle, noticeContent } = noticeData;
  const [isModify, setIsModify] = useState(false);

  // const dispatch = useDispatch();
  const handleChange = (e) => {
    e.preventDefault();
    // console.log("handleChange!");

    noticeData[e.target.name] = e.target.value;
    console.log("==handleChange== ");
    console.log(noticeData);
  };
  // mount시점에서, 공지사항 id에 해당하는 게시글 받아오기
  useEffect(() => {
    detailNoticeApi(params.id)
      .then((res) => {
        // console.log("== response Data in useEffect == ");
        // console.log(res.data);
        // response에 대해 비구조화 할당
        setNoticeData(res.data);

        // console.log("== noticeData in useEffect ==");
        // console.log(noticeData);
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
    console.log("== noticeData in handleSubmit ==");
    console.log(noticeData);
    setNoticeData({
      ...noticeData,
    });
    console.log("=== FINAL notice Data ===");
    console.log(noticeData);
    modifyNoticeApi(noticeData);
    setIsModify(!isModify);
  };
  const ModifyNotice = () => {
    return (
      <form className='write-form-wrapper' onSubmit={handleSubmit}>
        <div className='write-input-title'>
          <input
            className='write-title'
            defaultValue={noticeData.noticeTitle}
            onChange={handleChange}
            name='noticeTitle'
          />
        </div>
        <div className='write-input-content'>
          <input
            className='write-content'
            defaultValue={noticeData.noticeContent}
            onChange={handleChange}
            name='noticeContent'
          />
        </div>
        <div>
          <div>
            <Button type='submit' variant='contained'>
              수정
            </Button>
            <Button
              variant='contained'
              color='error'
              onClick={() => {
                setIsModify(!isModify);
                // console.log("After : " + isModify);
              }}
            >
              취소
            </Button>
          </div>
        </div>
      </form>
    );
  };
  const DefaultNotice = () => {
    return (
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
          {isManager ? (
            <div>
              <span className='noticedetail-button-wrapper'>
                <Button
                  variant='outlined'
                  onClick={() => {
                    setIsModify(!isModify);
                    // console.log(isModify);
                  }}
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
          ) : null}
        </div>
      </div>
    );
  };

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
        {isModify ? <ModifyNotice /> : <DefaultNotice />}
      </NoticeWrapper>
      <Footer />
    </>
  );
};

export default NoticeDetail;
