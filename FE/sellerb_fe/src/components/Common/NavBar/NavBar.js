import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./NavBar.css";
import { getManagerInfoApi } from "../../../api/managerApi";
import { detailConsultantApi } from "../../../api/consultantApi";
import styled from "styled-components";
import {
  NotificationOutlined,
  YoutubeOutlined,
  DatabaseOutlined,
  LogoutOutlined,
  BuildOutlined,
} from "@ant-design/icons";
import { CgProfile } from "react-icons/cg";

function NavBar() {
  const navigate = useNavigate();
  const [seq, setSeq] = useState("");

  var isManager = true;
  if (sessionStorage.getItem("adminCheck") === "ROLE_ADMIN") {
    isManager = true;
  } else {
    isManager = false;
  }

  // 관리자, 상담사든 로그인할 때, 사용자의 seq받기
  useEffect(() => {
    const seq = sessionStorage.getItem("seq");
    setSeq(seq);
    console.log("받은 seq : " + seq);
  }, []);

  const onLogOutBtn = () => {
    sessionStorage.clear();
    navigate("/");
  };

  return (
    <>
      <div className='navbar-wrapper'>
        <div className='navbar-left'>
          <Link
            to={isManager ? "/manager/main" : "/consultant/main"}
            style={{ marginTop: "7px" }}
          >
            <img
              className='navbar-logo-img'
              alt='#'
              src={`${process.env.PUBLIC_URL}/img/sellerB_Logo.svg`}
            />
          </Link>
          <div className='small-sellerb'>SellerB</div>
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
                회의생성
              </div>
              <div
                className='navi-title'
                onClick={() => navigate("/meeting/ConsultingMain")}
              >
                <YoutubeOutlined id='meet-icon' />
                상담
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
