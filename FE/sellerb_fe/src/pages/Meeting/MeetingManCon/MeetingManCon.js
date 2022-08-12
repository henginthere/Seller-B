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
      <div style={wrapper}>
        <Meeting />
      </div>
      <Footer />
    </div>
  );
}

export default MeetingManCon;
