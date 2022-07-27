import React from 'react'
import { Link } from 'react-router-dom'
import './NavBar.css'

function NavBar() {
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
                    <Link to="/" className='link-to'>
                        <h4>로그아웃</h4>
                    </Link>
                </div>
            </div>
        </div>
    </>
  )
}

export default NavBar