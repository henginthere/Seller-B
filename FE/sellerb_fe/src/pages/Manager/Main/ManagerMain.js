/*
 *
 *  관리자 Main
 *
 *  */
import React from "react";
import ManagerMainLeft from "../../../components/Manager/ManagerMainLeft";
import ManagerMainRight from "../../../components/Manager/ManagerMainRight";
import NavBar from "../../../components/Common/NavBar/NavBar";

function ManagerMain() {
  const makeCenter = {
    textAlign: "center",
  };
  const styleObj = {
    width: "100%",
    display: "flex",
    flexDirection: "row",
  };

  return (
    <div style={makeCenter}>
      {/* HeaderNavBar */}
      <nav>
        <NavBar></NavBar>
      </nav>
      <div style={styleObj}>
        {/* 좌측 환영인사 및 달력, 회의생성 */}

        <ManagerMainLeft />

        {/* 우측 상담사 관리 */}

        <ManagerMainRight />
      </div>
      {/* footer */}
      <div style={makeCenter}>footer</div>
    </div>
  );
}

export default ManagerMain;
