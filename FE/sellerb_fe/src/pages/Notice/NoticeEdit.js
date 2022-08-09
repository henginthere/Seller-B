import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Link, useNavigate, useParams } from "react-router-dom";
import "./NoticeDetail.css";
import { Footer, NavBar } from "../../components/index";

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

  // 공지사항 내용 수정사항 반영
  const onChange = (e)=>{
    e.preventDefault(); 

    const { value, name} = e.target;

    setNoticeData({
      ...noticeData,
      [name] : value
    })
    console.log("noticeData:" + noticeData)
  }

  // 수정사항 제출 버튼
  const onEditSubmitBtn = ()=>{
    modifyNoticeApi(noticeData)
    .then((res)=>{
      console.log(res.data);
      navigate("/manager/noticeList")
    })
    .catch((err)=>{
      console.log(err.data)
    })
  }

  // 삭제 버튼
  const onDeleteSubmitBtn = ()=>{
    delNoticeApi(id)
    .then((res)=>{
      console.log(res.data);
      navigate("/manager/noticeList")
    })
    .catch((err)=>{
      console.log(err.data);
    })
  }

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

  return (
    <>
        <NavBar />
        <div className="board-wrap">
            <div className="detail-head">
                <div className="notice-detail-title">공지사항</div>
                <div className="notice-detail-subTitle">sellerB의 공지사항</div>
            </div>

            <div className="notice-detail-content-header">
                <div className="content-header-row">
                    <div className="row-left">제목</div>
                    <input
                      className="row-right"
                      name="noticeTitle"
                      onChange={onChange}
                      defaultValue={noticeData.noticeTitle}
                      />
                </div>
                <div className="content-header-row">
                    <div className="row-left">작성일</div>
                    <div className="row-right">{noticeData.noticeRegDate}</div>
                </div>
            
            
            <div className="notice-detail-content-detail">
                <div>
                    <pre>
                    <input 
                      className="row-right"
                      name="noticeContent"
                      defaultValue={noticeData.noticeContent}
                      onChange={onChange}
                    />
                    </pre>
                </div>
            </div>
            {/* content하단 */}
            <div className="notice-detail-bottom">
                <button className="detail-button"
                  onClick={onEditSubmitBtn}>수정완료</button>
                <button 
                className="detail-button"
                onClick={onDeleteSubmitBtn}
                >삭제하기</button>
            </div>
     

            </div>
        </div>
      <Footer />
    
    </>
  )
}

export default NoticeEdit