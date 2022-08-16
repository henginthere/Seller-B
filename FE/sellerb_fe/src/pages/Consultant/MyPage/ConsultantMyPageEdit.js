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

import axios from "axios";
import AttendanceLog from "../../../components/Log/AttendanceLog";
import ConsultingLog from "../../../components/Log/ConsultingLog";
import { MediButton } from "../../../components/Common/MediButton";
import { SmallButton } from "../../../components/Common/SmallButton";
import {
  detailConsultantApi,
  deleteConsultant,
  modifyConsultantApi,
} from "../../../api/consultantApi";

function ConsultantMyPageEdit() {
  const { id } = useParams();
  console.log("params 확인 : " + id);
  const navigate = useNavigate();

  const [consultant, setConsultant] = useState([]);

  const [editPass, setEditPass] = useState();
  const [groupName, setGroupName] = useState("");

  useEffect(() => {
    detailConsultantApi(id)
      .then((res) => {
        setConsultant(res.data);
        console.log(JSON.stringify(res.data));
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  // state Handling
  const onChangeInfo = (e) => {
    const { name, value } = e.target;
    setConsultant({
      ...consultant,
      [name]: value,
    });
  };

  // editPass Handling
  const onChangePass = (e) => {
    setEditPass(e.target.value);
  };

  // 수정완료
  const onEditCompleteBtn = async () => {
    const EditInfo = {
      consultantPass: editPass,
      consultantEmail: consultant.consultantEmail,
      consultantTel: consultant.consultantTel,
      productGroupSeq: consultant.productGroupSeq,
      consultantImageUrl: consultant.consultantImageUrl,
    };

    await axios
      .put(`https://i7d105.p.ssafy.io/api/consultant/${id}`, EditInfo, {
        header: {
          "Content-Type": `multipart/form-data`,
        },
      })
      .then((response) => {
        console.log("success");
      })
      .catch((error) => {
        console.log("Error!!!");
      });
  };

  return (
    <>
      <NavBar />
      <div className="notice-title">상담사 프로필</div>
      {/* Left Content */}
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
           
              <input
                defaultValue={consultant.consultantEmail}
                name="consultantEmail"
                onChange={onChangeInfo}
              />
          </div>
          <div className="con-profile-element">
            <p>사원 Pnum</p>
              <input
                defaultValue={consultant.consultantTel}
                name="consultantTel"
                onChange={onChangeInfo}
              />
          </div>
          <div className="con-profile-element">
            <p>비밀번호</p>
              <input value={editPass} onChange={onChangePass} />
          </div>
          <div className="con-profile-element">
            <p>제품군</p>
            <div>{consultant.productGroupName}</div>
          </div>
          <div style={{ display: "flex", marginLeft: "5px" }}>
            <SmallButton label="수정완료" onClick={onEditCompleteBtn} />
          </div>

        {/*  */}
      </div>
      </div>
      </div>
      <Footer />
    </>
  );
}

export default ConsultantMyPageEdit;
