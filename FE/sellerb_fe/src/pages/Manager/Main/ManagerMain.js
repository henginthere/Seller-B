/*
 *
 *  관리자 Main
 *
 *  */
import React from "react";
import ManagerMainLeft from "../../../components/Manager/Main/ManagerMainLeft";
import ManagerMainRight from "../../../components/Manager/Main/ManagerMainRight";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/footer";

function ManagerMain() {

  return (
    <div className="center">
      {/* HeaderNavBar */}
      <nav>
        <NavBar></NavBar>
      </nav>
      <div id="wrapper">
        {/* 좌측 환영인사 및 달력, 회의생성 */}

        <ManagerMainLeft />

        {/* 우측 상담사 관리 */}

        <ManagerMainRight />
      </div>
      {/* footer */}
      <div className="center">
        <Footer />
      </div>
    </div>
  );
}

export default ManagerMain;
