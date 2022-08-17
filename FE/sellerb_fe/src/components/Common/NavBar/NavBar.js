import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./NavBar.css";
// import { getManagerInfoApi } from "../../../api/managerApi";
// import { detailConsultantApi } from "../../../api/consultantApi";
// import styled from "styled-components";
import {
  NotificationOutlined,
  YoutubeOutlined,
  LogoutOutlined,
  BuildOutlined,
  CustomerServiceOutlined,
} from "@ant-design/icons";
import { CgProfile } from "react-icons/cg";

function NavBar() {
  const navigate = useNavigate();
  const [seq, setSeq] = useState("");
  const [isManager, setIsManager] = useState(true);

  // 관리자, 상담사든 로그인할 때, 사용자의 seq받기
  useEffect(() => {
    if (sessionStorage.getItem("adminCheck") === "ROLE_ADMIN") {
      setIsManager(true);
    } else {
      setIsManager(false);
    }

    const seq = sessionStorage.getItem("seq");
    setSeq(seq);
  }, []);

  const onLogOutBtn = () => {
    sessionStorage.clear();
    navigate("/main");
  };

  const onGoMain = () => {
    if (isManager) {
      navigate("/manager/main");
    } else {
      navigate("/consultant/main");
    }
  };

  return (
    <>
      <div className='navbar-wrapper'>
        <div className='navbar-left'>
          <img
            onClick={onGoMain}
            className='navbar-logo-img'
            alt='#'
            src={`${process.env.PUBLIC_URL}/img/sellerB_Logo.svg`}
          />
          <div className='small-sellerb' onClick={onGoMain}>
            SellerB
          </div>
        </div>

        <div className='navbar-right'>
          {isManager ? (
            <div className='navbar-right-navigator'>
              <div
                className='navi-title'
                onClick={() => navigate("/manager/noticeList")}
              >
                <NotificationOutlined id='notice-icon' /> 공지사항
              </div>

              <div
                className='navi-title'
                onClick={() => navigate("/meeting/mancon")}
              >
                <YoutubeOutlined id='meet-icon' />
                회의생성
              </div>

              <div
                className='navi-title'
                onClick={() => navigate("/manager/productList")}
              >
                <BuildOutlined id='product-icon' /> 제품관리
              </div>

              <div
                className='navi-title'
                onClick={() => navigate(`/manager/mypage/${seq}`)}
              >
                <CgProfile id='mypage-icon' /> 마이 페이지
              </div>

              <div className='navi-title' onClick={onLogOutBtn}>
                <LogoutOutlined id='logout-icon' />
                로그아웃
              </div>
            </div>
          ) : (
            <div className='navbar-right-navigator'>
              <div
                className='navi-title'
                onClick={() => navigate("/manager/noticeList")}
              >
                <NotificationOutlined id='notice-icon' /> 공지사항
              </div>
              <div
                className='navi-title'
                onClick={() => navigate("/meeting/mancon")}
              >
                <YoutubeOutlined id='meet-icon' />
                회의참가
              </div>
              <div
                className='navi-title'
                onClick={() => navigate("/meeting/consultingMain")}
              >
                <CustomerServiceOutlined id='notice-icon' /> 상담
              </div>
              <div
                className='navi-title'
                onClick={() => navigate(`/consultant/mypage/${seq}`)}
              >
                <CgProfile id='mypage-icon' /> 마이 페이지
              </div>

              <div className='navi-title' onClick={onLogOutBtn}>
                <LogoutOutlined id='logout-icon' />
                로그아웃
              </div>
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default NavBar;
