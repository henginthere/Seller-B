import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
import "./ConsultantMyPage.css";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import "./ConsultantMyPage.css";

import AttendanceLog from "../../../components/Log/AttendanceLog";
import ConsultingLog from "../../../components/Log/ConsultingLog";
import { MediButton } from "../../../components/Common/MediButton";
import {detailConsultantApi, deleteConsultant, modifyConsultantApi } from "../../../api/consultantApi";

function ConsultantMyPageEdit() {
    const { seq } = useParams();
    console.log("params 확인 : " + seq);
    const navigate = useNavigate();

    const [consultant, setConsultant] = useState([]);

    const [editPass, setEditPass] = useState("");
    const [groupName, setGroupName] = useState("");

    useEffect(() => {
        detailConsultantApi(seq)
        .then((res)=>{
          setConsultant(res.data); 
          //////////////////////////////////////////////////////////////
          console.log(JSON.stringify(res.data));
  
        })
        .catch((err)=>{
            console.log(err);
        })
      }, []);

      const onDeleteBtn = ()=>{
        deleteConsultant(seq)
        .then((res)=>{
          console.log("onDelete Btn:" + res.data);
    
          navigate("/manager/main");
    
        })
        .catch((err)=>{
          console.log("Error")
        })
     }
     
     const onEdiCompleteBtn = () => {
        
        const EditInfo = {
            consultantPass: editPass,
            consultantEmail:"",
            consultantTel:"",
            productGroupSeq:"",
            consultantImageUrl:""
        }




        modifyConsultantApi()
        .then((res)=>{
            console.log(JSON.stringify(res.data));
        })
        .catch((err)=>{
            console.log("Error");
        })
    }

  return (
    <>
    <NavBar />
      <div className="notice-title">상담사 프로필</div>
                  {/* Left Content */}
                  <div className="con-mypage-left-wrapper">
              <img
                className="con-mypage-default-img"
                alt="#"
                src={consultant.consultantImageUrl}
              />
            </div>
      <div className="profile-wrapper">
        
        {/*  */}
        <div className="profile-left">
          <div className="profile-element">
            <p>사번</p>
            <div>{consultant.consultantId}</div>
          </div>
          <div className="profile-element">
            <p>사원명</p>
            <div>{consultant.consultantName}</div>
          </div>
          <div className="profile-element">
            <p>사원 Email</p>
            <div>{consultant.consultantEmail}</div>
          </div>
          <div className="profile-element">
           <p>사원 Pnum</p>
            <div>{consultant.consultantTel}</div>
          </div>
          <div className="profile-element">
            <p>제품군</p>
            <div>{consultant.productGroupName}</div>
          </div>
          <div style={{display: "flex", marginLeft:"5px"}}>

            <SmallButton label="수정하기" onClick={onMoveEditBtn} />
          </div>
        </div>
        {/*  */}
        <div className="profile-right">
          <div className="consultant-detail-select-wrapper">
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
  )
}

export default ConsultantMyPageEdit