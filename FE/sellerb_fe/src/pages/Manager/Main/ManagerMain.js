/*
 *
 *  관리자 Main
 *
 *  */
import React, { useEffect, useState} from "react";
import ManagerMainLeft from "../../../components/Manager/Main/ManagerMainLeft";
import ManagerMainRight from "../../../components/Manager/Main/ManagerMainRight";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
import { listConsultantApi } from '../../../api/consultantApi'

function ManagerMain() {
  
  // const [list, setList] = useState([]);

  // useEffect(()=>{
  //   listConsultantApi()
  //   .then((res)=>{
  //     console.log("after API:" + res.data[0].consultantId);
  //     setList(res.data);

  //     console.log("consultantList: " + list[0].consultantId)
  //   })  
  //   .catch((err)=>{
  //     console.log("err:" + err.data);
  //   })
  // },[])
  

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