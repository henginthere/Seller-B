import React from "react";
// import './Footer.css'

function Footer() {
  const styleObj = {
    width: "100%",
    height: "200px",
    textAlign: "center",
    backgroundColor: "lightgray",
    display: "flex",
    // Footer 하단 고정 
    position: "absolute",
    bottom: "0",
 
  };
  const styleObj_content = {
    width: "25%",
    padding: "20px",
  };
  const styleObj_list = {
    listStyleType: "none",
    textAlign: "Left",
    fontSize: "10px",
  };
  return (
    <footer className="footer-wrapper">
      <div style={styleObj}>
        <div style={styleObj_content}>
          <h4>고객센터</h4>
          <ul style={styleObj_list}>
            <li>Tel : 010-0000-0000</li>
            <li>E-mail : ssafy@ssafy.com</li>
            <li>KakaoTalk : dlacogus5239</li>
            <li>09:00AM ~ 06:00PM 까지 상담가능합니다.</li>
          </ul>
        </div>
        <div style={styleObj_content}>
          <h4>회사정보</h4>
          <ul style={styleObj_list}>
            <li>상호 : SellerB</li>
            <li>대표 : 신혜연</li>
            <li>주소 : 경상북도 구미시</li>
            <li>SSAFY</li>
          </ul>
        </div>
        <div style={styleObj_content}>
          <h4>근무시간 정보</h4>
          <ul style={styleObj_list}>
            <li>근무시간 : 월 ~ 금 AM 9:00 ~ PM 7:00</li>
            <li>점심시간 : PM12 :00 ~ PM 1 : 00</li>
            <li>고객센터 : AM 10: 00 ~ PM6 : 00</li>
          </ul>
        </div>
        <div style={styleObj_content}>
          <h4>은행정보</h4>
          <ul style={styleObj_list}>
            <li>국민은행 : 950002-00-087953(예금주 : 임채현)</li>
            <li>카카오뱅크 : 3333-13-9493990(예금주 : 임채현)</li>
          </ul>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
