import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import "./ConsultantDetail.css";
import { Footer, NavBar } from "../../../components/index";
import AttendanceLog from "../../../components/Log/AttendanceLog";
import ConsultingLog from "../../../components/Log/ConsultingLog";
import { SmallButton } from '../../../components/Common/SmallButton'
import { DangerSmallButton } from '../../../components/Common/DangerSmallButton'

import {
  detailConsultantApi,
  deleteConsultantApi
} from "../../../api/consultantApi";

function ConsultantDetail() {
  const { id } = useParams();
  const seq = id;
  console.log(seq);

  const navigate = useNavigate();

  // useEffect - 상담사 프로필 정보 / 상담사 출결이력 정보
  const [consultant, setConsultant] = useState([]);
  const [attendance, setAttendance] = useState("");

  const [logOption, setLogOption] = useState("출결이력");
  const [groupName, setGroupName] = useState("");


  const onHandleLogOption = (event) => {
    setLogOption(event.currentTarget.value);

    console.log(logOption);
  };

  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
    detailConsultantApi(seq)
      .then((res) => {
        setConsultant(res.data);
        console.log(JSON.stringify(res.data));
        const test = consultant.productGroupName;

        console.log("test :" + test);
        console.log("groupName: " + groupName);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  function ConsultantLog(props) {
    if (logOption === "출결이력") {
      return <AttendanceLog consultant_id={seq} />;
    } else {
      return <ConsultingLog consultant_id={seq} />;
    }
  }

  const onDeleteBtn = () => {
    deleteConsultantApi(seq)
      .then((res) => {
        console.log("onDelete Btn:" + res.data);

      navigate("/manager/main");
    })
    .catch((err)=>{
      console.log("Error")
    })
 }

 const onEditBtn = () => {
  navigate(`/manager/consultantModify/${consultant.consultantSeq}`)
 }

  return (
    <>
      <NavBar />
      <div className="notice-title">상담사 프로필</div>
      <div className="consultant-profile-container">
        <div className="profile-wrapper">
          <div className="con-mypage-left-wrapper">
            <img
              className="con-mypage-default-img"
              alt="#"
              src={consultant.consultantImageUrl}
            />
          </div>
      <div className="con-mypage-profile-left">
      <div className="con-profile-element">
            <p>사번</p>
            <div>{consultant.consultantId}</div>
          </div>
          <div className="con-profile-element">
            <p>사원명</p>
            <div>{consultant.consultantName}</div>
          </div>
          <div className="con-profile-element">
            <p>사원 Email</p>
            <div>{consultant.consultantEmail}</div>
          </div>
          <div className="con-profile-element">
           <p>사원 Pnum</p>
            <div>{consultant.consultantTel}</div>
          </div>
          <div className="con-profile-element">
            <p>제품군</p>
            <div>{consultant.productGroupName}</div>
          </div>
          <div style={{display:"flex", width:"300px"}}>
            <SmallButton label="수정하기" onClick={onEditBtn} />
          </div>
        </div>
        </div>
        <div className="profile-right">
          <div className="consultant-detail-select-wrapper">

            <select onChange={onHandleLogOption} value={logOption}>
              <option>출결이력</option>
              <option>상담이력</option>
            </select>
          </div>
          <div className='attendance-log'>
            <ConsultantLog consultant_id={seq} />
          </div>
        
      </div>
      </div>
      <Footer />
    </>
  );
}

export default ConsultantDetail;
