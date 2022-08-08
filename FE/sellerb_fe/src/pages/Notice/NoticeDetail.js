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

function NoticeDetail() {
  const navigate = useNavigate();
  const { id } = useParams();

  const [noticeData, setNoticeData] = useState({
    noticeSeq: id,
    noticeTitle: "",
    noticeContent: "",
  });

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

  const onModifyBtn = () => {
    console.log("modfiy전 id확인: " + id);
    navigate(`/manager/noticeEdit/${id}`);
  };

  const onDeleteBtn = () => {
    delNoticeApi(id)
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log("Error");
      });
  };

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
                    <div className="row-right">{noticeData.noticeTitle}</div>
                </div>
          
          
                <div className="content-header-row">
                    <div className="row-left">작성일</div>
                    <div className="row-right">{noticeData.noticeRegDate}</div>
                </div>
            
            
            <div className="notice-detail-content-detail">
                <div>
                    <pre>
                        {noticeData.noticeContent}
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                        zzzzzzzzzzzz공지사하아앙
                    </pre>
                </div>
            </div>
            {/* content하단 */}
            <div className="notice-detail-bottom">
                <button className="detail-button">목록</button>
            </div>

            </div>
        </div>
      <Footer />
    </>
  );
}

export default NoticeDetail;
