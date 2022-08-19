import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Meeting from "../../../components/Meeting/Meeting";

import { NavBar, Footer } from "../../../components/index";
import "../Meeting.css";

function MeetingManCon() {
  const wrapper = {
    minHeight: "555px",
    height: "100%",
  };
  const navigate = useNavigate();
  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
  }, []);
  return (
    <div style={wrapper}>
      <NavBar />
      <div className='consultant-main-image-wrapper'>
        <img
          src={`${process.env.PUBLIC_URL}/img/consultantMainPageImage.jpg`}
        />
      </div>
      <div style={wrapper}>
        <Meeting />
      </div>
      <Footer />
    </div>
  );
}

export default MeetingManCon;
