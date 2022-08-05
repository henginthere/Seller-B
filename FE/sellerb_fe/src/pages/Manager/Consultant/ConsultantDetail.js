import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import Axiso from "axios";


import "./ConsultantDetail.css";
import { Footer, NavBar } from "../../../components/index";
import AttendanceLog from "../../../components/Log/AttendanceLog";
import ConsultingLog from "../../../components/Log/ConsultingLog";
// import Test from '../../../components/Log/Test'

import {
  detailConsultantApi,
} from "../../../api/consultantApi";

function ConsultantDetail() {
  const { id } = useParams();
  const seq = id;
  console.log(seq)
  // console.log("params:" + params.consultantSeq)

  const navigate = useNavigate();

  // useEffect - 상담사 프로필 정보 / 상담사 출결이력 정보
  const [consultant, setConsultant] = useState("");
  const [attendance, setAttendance] = useState("");
  const [logOption, setLogOption] = useState("출결이력");

  const onHandleLogOption = (event)=>{
    setLogOption(event.currentTarget.value);

    console.log(logOption);
  }
 
    useEffect(() => {
      detailConsultantApi(seq)
      .then((res)=>{
          console.log("res.data:" + res.data);
          
          // consultant = res.data;
          
          setConsultant(res.data); 
          console.log("consultant:" + consultant.consultantId);
      })
      .catch((err)=>{
          console.log(err);
      })
    }, []);

 function ConsultantLog (props) {
    if(logOption === '출결이력'){
        // console.log(params.consultants
        return <AttendanceLog consultant_id = {seq} />
    }
    else{
        return <ConsultingLog consultant_id = {seq} />
    }
 }


  return (

    <>
      <NavBar />
      <div className="notice-title">상담사 프로필</div>
      <div className="profile-wrapper">
        <div className="profile-left">
          <div className="profile-element">
            사번
            <div>{consultant.consultantId}</div>
          </div>
          <div className="profile-element">
            사원명
            <div>{consultant.consultantName}</div>
          </div>
          <div className="profile-element">
            사원 Email
            <div>{consultant.consultantEmail}</div>
          </div>
          <div className="profile-element">
            사원 Pnum
            <div>{consultant.consultantTel}</div>
          </div>
          <div className="profile-element">
            제품군
            <div>{consultant.consultantGroup}</div>
          </div>
        </div>
        <div className="profile-right">
          <div className="select-wrapper">
            <select onChange={onHandleLogOption} value={logOption}>
              <option >출결이력</option>
              <option>상담이력</option>
            </select>
          </div>
          <div className="attendance-log">
            <ConsultantLog consultant_id = {seq} />
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default ConsultantDetail;
