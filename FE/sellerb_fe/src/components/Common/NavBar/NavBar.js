import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import './NavBar.css'

function NavBar() {

    const navigate = useNavigate();
    const onLogOutBtn = ()=>{
        localStorage.removeItem("accessToken");
        navigate("/");
    }



  return (
    <>
        <div className='navbar-wrapper'>
            <div className='navbar-left'>
                <Link to="/" style={{marginTop:"7px"}}>
                    <img className='navbar-logo-img' alt='#' src={`${process.env.PUBLIC_URL}/img/sellerB_Logo.svg`} />
                </Link>   
                    <div className='small-sellerb'>SellerB</div>
            </div>

            <div className='navbar-right'>
                <div className='navbar-right-navigator'>
                    <Link to="/manager/noticeList" className='link-to'>
                        <h4>공지사항</h4>
                    </Link>
                    <Link to="#" className='link-to'>
                        <h4>회의 참여</h4>
                    </Link>
                    <Link to="/manager/mypage" className='link-to'>
                        <h4>마이페이지</h4>
                    </Link>
                        <h4 onClick={onLogOutBtn}>로그아웃</h4>
                </div>
            </div>
        </div>
    </>
  )
}

export default NavBar