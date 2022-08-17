import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
// import Axiso from "axios";

import "./ConsultantDetail.css";
import { Footer, NavBar } from "../../../components/index";
import AttendanceLog from "../../../components/Log/AttendanceLog";
import ConsultingLog from "../../../components/Log/ConsultingLog";
<<<<<<< HEAD
import { SmallButton } from '../../../components/Common/SmallButton'
import { DangerSmallButton } from '../../../components/Common/DangerSmallButton'

=======
import { SmallButton } from "../../../components/Common/SmallButton";
import { DangerSmallButton } from "../../../components/Common/DangerSmallButton";
// import Test from '../../../components/Log/Test'
>>>>>>> 96376293e59ced092643372075f992b07c8a2e10

import {
  detailConsultantApi,
  deleteConsultant,
} from "../../../api/consultantApi";

function ConsultantDetail() {
  const { id } = useParams();
  const seq = id;
  console.log(seq);
  // console.log("params:" + params.consultantSeq)

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
        //////////////////////////////////////////////////////////////
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
      // console.log(params.consultants
      return <AttendanceLog consultant_id={seq} />;
    } else {
      return <ConsultingLog consultant_id={seq} />;
    }
  }

  const onDeleteBtn = () => {
    deleteConsultant(seq)
      .then((res) => {
        console.log("onDelete Btn:" + res.data);

<<<<<<< HEAD
      navigate("/manager/main");
    })
    .catch((err)=>{
      console.log("Error")
    })
 }

 const onEditBtn = () => {
  navigate(`/manager/consultantModify/${consultant.consultantSeq}`)
 }
=======
        navigate("/manager/main");
      })
      .catch((err) => {
        console.log("Error");
      });
  };

  const onEditBtn = () => {
    navigate(`/manager/consultantModify/${consultant.consultantSeq}`);
  };
>>>>>>> 96376293e59ced092643372075f992b07c8a2e10

  return (
    <>
      <NavBar />
<<<<<<< HEAD
      <div className="notice-title">상담사 프로필</div>
        {/*  */}
      <div className="consultant-profile-container">
        <div className="profile-wrapper">
        <div className="con-mypage-left-wrapper">
            <img
              className="con-mypage-default-img"
              alt="#"
              src={consultant.consultantImageUrl}
            />
          </div>
      {/*  */}
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
            <DangerSmallButton label="삭제하기" onClick={onDeleteBtn} />
          </div>
        </div>
        {/*  */}
        </div>
        <div className="profile-right">
          <div className="consultant-detail-select-wrapper">
=======
      <div className='notice-title'>상담사 프로필</div>
      <div className='profile-wrapper'>
        {/*  */}
        <div className='profile-left'>
          <div className='profile-element'>
            <p>사번</p>
            <div>{consultant.consultantId}</div>
          </div>
          <div className='profile-element'>
            <p>사원명</p>
            <div>{consultant.consultantName}</div>
          </div>
          <div className='profile-element'>
            <p>사원 Email</p>
            <div>{consultant.consultantEmail}</div>
          </div>
          <div className='profile-element'>
            <p>사원 Pnum</p>
            <div>{consultant.consultantTel}</div>
          </div>
          <div className='profile-element'>
            <p>제품군</p>
            <div>{consultant.productGroupName}</div>
          </div>
          <div style={{ display: "flex", marginLeft: "5px" }}>
            <SmallButton label='수정하기' onClick={onEditBtn} />
            <DangerSmallButton label='삭제하기' onClick={onDeleteBtn} />
          </div>
        </div>
        {/*  */}
        <div className='profile-right'>
          <div className='consultant-detail-select-wrapper'>
>>>>>>> 96376293e59ced092643372075f992b07c8a2e10
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
