import axios from "axios";

import React, { Component } from "react";
import Meeting from "../../../components/Meeting/Meeting";

import { NavBar, Footer } from "../../../components/index";
import "../Meeting.css";

class MeetingManCon extends Component {
  render() {
    return (
      <>
        <NavBar />
        <Meeting />
        <Footer />
      </>
    );
  }
}

export default MeetingManCon;
