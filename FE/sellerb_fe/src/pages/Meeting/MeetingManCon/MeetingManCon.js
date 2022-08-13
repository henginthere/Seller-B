import React from "react";
import Meeting from "../../../components/Meeting/Meeting";

import { NavBar, Footer } from "../../../components/index";
import "../Meeting.css";

function MeetingManCon() {
  const wrapper = {
    minHeight: "555px",
    height: "100%",
  };
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
