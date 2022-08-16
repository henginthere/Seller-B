import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Link, useNavigate, useParams } from "react-router-dom";
import "./NoticeDetail.css";
import { Footer, NavBar } from "../../components/index";
import { DangerMediButton} from '../../components/Common/DangerMediButton'

import {
  detailNoticeApi,
  modifyNoticeApi,
  delNoticeApi,
} from "../../api/noticeApi";

function NoticeEdit() {
  const navigate = useNavigate();
  const { id } = useParams();

  const [noticeData, setNoticeData] = useState({
    noticeSeq: id,
    noticeTitle: "",
    noticeContent: "",
  });

  const [bSeq, setBSeq] = useState(sessionStorage.getItem("brandSeq"));

  // 공지사항 내용 수정사항 반영
  const onChange = (e) => {
    e.preventDefault();

    const { value, name } = e.target;

    setNoticeData({
      ...noticeData,
      [name]: value,
    });
    console.log("noticeData:" + noticeData);
  };

  // 수정사항 제출 버튼
  const onEditSubmitBtn = () => {
    setBSeq(parseInt(bSeq));
   
    // console.log("")

    const Info = {
      noticeSeq: id,
      post :{
        brandSeq: bSeq,
        noticeTitle: noticeData.noticeTitle,
        noticeContent: noticeData.noticeContent
      }
    }

    modifyNoticeApi(Info)
      .then((res) => {
        console.log(res.data);
        navigate("/manager/noticeList");
      })
      .catch((err) => {
        console.log(JSON.stringify(err.data));
      });
  };

  // 삭제 버튼
  const onDeleteSubmitBtn = () => {
    delNoticeApi(id)
      .then((res) => {
        console.log(res.data);
        navigate("/manager/noticeList");
      })
      .catch((err) => {
        console.log(err.data);
      });
  };

  // 게시글의 상세정보 받아와서 setState
  useEffect(() => {
    detailNoticeApi(id)
      .then((res) => {
        setNoticeData(res.data);
        console.log("noticeData:" + noticeData);
      })
      .catch((e) => {
        console.log("err");
      });
  }, []);
  const parsingDate = (date) => {
    var parsedDate = new Date(date);
    var yyyy = parsedDate.getFullYear();
    var MM = parsedDate.getMonth() + 1;
    var dd = parsedDate.getDate();
    var hh = parsedDate.getHours() + 9;
    var mm = parsedDate.getMinutes();
    return (
      yyyy +
      "-" +
      addZero(MM) +
      "-" +
      addZero(dd) +
      "-" +
      addZero(hh) +
      ":" +
      addZero(mm)
    );
  };
  const addZero = (n) => {
    return n < 10 ? "0" + n : n;
  };
  return (
    <>
      <NavBar />
      <div className='consultant-main-image-wrapper'>
        <img
          src={`${process.env.PUBLIC_URL}/img/consultantMainPageImage.jpg`}
        />
      </div>
      <div className='board-wrap'>
        <div className='detail-head'>
          <div className='notice-detail-title'>공지사항</div>
          <div className='notice-detail-subTitle'>sellerB의 공지사항</div>
        </div>

        <div className='notice-detail-content-header'>
          <div className='content-header-row'>
            <div className='row-left'>제목</div>
            <input
              className='row-right'
              name='noticeTitle'
              onChange={onChange}
              defaultValue={noticeData.noticeTitle}
            />
          </div>
          <div className='content-header-row'>
            <div className='row-left'>작성일</div>
            <div className='row-right'>
              {parsingDate(noticeData.noticeRegDate)}
            </div>
          </div>

          <div className='notice-detail-content-detail'>
            <div>
              <pre>
                <input
                  style={{"width":"100%"}}
                  // className='row-right'
                  name='noticeContent'
                  defaultValue={noticeData.noticeContent}
                  onChange={onChange}
                />
              </pre>
            </div>
          </div>
          {/* content하단 */}
          <div className='notice-detail-bottom'>
            <button className='detail-button' onClick={onEditSubmitBtn}>
              수정완료
            </button>
            <DangerMediButton label="삭제하기" onClick={onDeleteSubmitBtn} />
            {/* <button className='detail-button' onClick={onDeleteSubmitBtn}>
              삭제하기
            </button> */}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default NoticeEdit;
