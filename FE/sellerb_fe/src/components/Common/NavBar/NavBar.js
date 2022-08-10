import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./NavBar.css";
import { getManagerInfoApi } from "../../../api/managerApi";
import { detailConsultantApi } from "../../../api/consultantApi";

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
  useEffect(()=>{
    const seq = sessionStorage.getItem("seq");
    setSeq(seq);
    console.log("받은 seq : " + seq)
  }, [])

  return (
    <>
      <div className='navbar-wrapper'>
        <div className='navbar-left'>
          <Link to={isManager ? "/manager/main" : "/consultant/main"} style={{ marginTop: "7px" }}>
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
              <Link to='/manager/noticeList' className='link-to'>
                <h4>공지사항</h4>
              </Link>
              <Link to='/meeting/mancon' className='link-to'>
                <h4>회의 생성</h4>
              </Link>
              <Link to='/manager/productList' className='link-to'>
                <h4>제품관리</h4>
              </Link>
              <div className='link-to' onClick={()=> navigate(`/manager/mypage/${seq}`)}>
                <h4>마이페이지</h4>
              </div>
              <Link to='/' className='link-to'>
                <h4>로그아웃</h4>
              </Link>
            </div>
          ) : (
            <div className='navbar-right-navigator'>
              <Link to='/manager/noticeList' className='link-to'>
                <h4>공지사항</h4>
              </Link>
              <Link to='/meeting/mancon' className='link-to'>
                <h4>회의 참여</h4>
              </Link>
              <div className='link-to' onClick={(e)=> navigate(`/consultant/mypage/${seq}`)}>
                <h4>마이페이지</h4>
              </div>
              <Link to='/' className='link-to'>
                <h4>로그아웃</h4>
              </Link>
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default NavBar;
