import React from "react";
import { Link } from "react-router-dom";
import "./NavBar.css";

function NavBar() {
  var isManager = true;
  if (sessionStorage.getItem("adminCheck") === "ROLE_ADMIN") {
    isManager = true;
  } else {
    isManager = false;
  }

  // 로그아웃 처리
  const handleLogOut = () => {
    sessionStorage.clear();
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
              <Link to='/manager/noticeList' className='link-to'>
                <h4>공지사항</h4>
              </Link>
              <Link to='/meeting/mancon' className='link-to'>
                <h4>회의 생성</h4>
              </Link>
              <Link to='/manager/productList' className='link-to'>
                <h4>제품관리</h4>
              </Link>
              <Link to='/manager/mypage' className='link-to'>
                <h4>마이페이지</h4>
              </Link>
              <Link to='/' className='link-to' onClick={handleLogOut}>
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
              <Link to='/consultant/mypage' className='link-to'>
                <h4>마이페이지</h4>
              </Link>
              <Link to='/' className='link-to' onClick={handleLogOut}>
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
