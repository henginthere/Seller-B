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
  console.log(id)

  const navigate = useNavigate();

  // useEffect - 상담사 프로필 정보 / 상담사 출결이력 정보
  const [consultant, setConsultant] = useState("");
  const [attendance, setAttendance] = useState("");
  const [logOption, setLogOption] = useState("출결이력");

  const onHandleLogOption = (event)=>{
    setLogOption(event.currentTarget.value);

    console.log(logOption);
  }
 
  const dummyData = [];
  // useEffect : axios 상담사 프로필에 넣을 정보
  //   useEffect(() => {
  //     detailConsultantApi(id)
  //     .then((res)=>{
  //         console.log(res.data);
          
  //         // consultant = res.data;
  //         setConsultant(res.data); 
          
  //     })
  //     .catch((err)=>{
  //         console.log(err);
  //     })
  //   }, []);

 function ConsultantLog (props) {
    if(logOption === '출결이력'){
        // console.log(params.consultants
        return <AttendanceLog consultant_id = {id} />
    }
    else{
        return <ConsultingLog consultant_id = {id} />
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
            <div>ABC</div>
          </div>
          <div className="profile-element">
            사원명
            <div>김상담</div>
          </div>
          <div className="profile-element">
            사원 Email
            <div>abc@abc.com</div>
          </div>
          <div className="profile-element">
            사원 Pnum
            <div>010-1234-5678</div>
          </div>
          <div className="profile-element">
            제품군
            <div>에어컨</div>
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
            <ConsultantLog consultant_id = {id} />
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default ConsultantDetail;
