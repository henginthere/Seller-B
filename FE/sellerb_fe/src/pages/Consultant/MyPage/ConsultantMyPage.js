import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
// import { TextField } from "@mui/material";
// import Button from "@mui/material/Button";
import "./ConsultantMyPage.css";
// import Table from "@mui/material/Table";
// import TableBody from "@mui/material/TableBody";
// import TableCell from "@mui/material/TableCell";
// import TableContainer from "@mui/material/TableContainer";
// import TableHead from "@mui/material/TableHead";
// import TableRow from "@mui/material/TableRow";
// import Paper from "@mui/material/Paper";

import AttendanceLog from "../../../components/Log/AttendanceLog";
import ConsultingLog from "../../../components/Log/ConsultingLog";
// import { MediButton } from "../../../components/Common/MediButton";
import { SmallButton } from "../../../components/Common/SmallButton";
import { DangerSmallButton } from "../../../components/Common/DangerSmallButton";
import { detailConsultantApi } from "../../../api/consultantApi";

function ConsultantMyPage() {
  const navigate = useNavigate();
  const { id } = useParams();
  const seq = id;

  const [consultant, setConsultant] = useState([]);
  const [logOption, setLogOption] = useState("출결이력");

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState("");
  const [previewUrl, setPreviewUrl] = useState(
    `${process.env.PUBLIC_URL}/img/default_img.png`,
  );

  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
    detailConsultantApi(seq)
      .then((res) => {
        console.log(JSON.stringify(res.data));
        setConsultant(res.data);
      })
      .catch((err) => {
        console.log("Error");
      });
  }, []);

  const onHandleLogOption = (event) => {
    setLogOption(event.currentTarget.value);
  };

  // 출결이력 or 상담이력
  function ConsultantLog(props) {
    if (logOption === "출결이력") {
      return <AttendanceLog consultant_id={seq} />;
    } else {
      return <ConsultingLog consultant_id={consultant.consultantId} />;
    }
  }

  // 이미지 파일 관련
  const handleChangeFile = (event) => {
    setImgFile(event.target.files);

    setImgBase64([]);
    for (var i = 0; i < event.target.files.length; i++) {
      if (event.target.files[i]) {
        let reader = new FileReader();
        reader.readAsDataURL(event.target.files[i]); // 파일을 읽어서 버퍼에 저장중

        // 파일 상태업데이트
        reader.onloadend = () => {
          const base64 = reader.result;
          console.log(base64);
          if (base64) {
            var base64Sub = base64.toString();

            setImgBase64((imgBase64) => [...imgBase64, base64Sub]);
          }
        };
      }
    }
  };

  const deleteImage = () => {
    setImgFile("");
    setImgBase64("");
  };

  // 수정 페이지로 이동
  const onMoveEditBtn = () => {
    console.log(`/consultant/mypage/edit/${seq}`);
    navigate(`/consultant/mypage/edit/${seq}`);
  };

  return (
    <>
      <NavBar />
      <div className='notice-title'>상담사 프로필</div>
      {/* Left Content */}
      <div className='consultant-profile-container'>
        <div className='profile-wrapper'>
          <div className='con-mypage-left-wrapper'>
            <img
              className='con-mypage-default-img'
              alt='#'
              src={consultant.consultantImageUrl}
            />
          </div>

          <div className='con-mypage-profile-left'>
            <div className='con-profile-element'>
              <p>사번</p>
              <div>{consultant.consultantId}</div>
            </div>
            <div className='con-profile-element'>
              <p>사원명</p>
              <div>{consultant.consultantName}</div>
            </div>
            <div className='con-profile-element'>
              <p>사원 Email</p>
              <div>{consultant.consultantEmail}</div>
            </div>
            <div className='con-profile-element'>
              <p>사원 Pnum</p>
              <div>{consultant.consultantTel}</div>
            </div>
            <div className='con-profile-element'>
              <p>제품군</p>
              <div>{consultant.productGroupName}</div>
            </div>
            <div style={{ display: "flex", marginLeft: "5px" }}>
              <SmallButton label='수정하기' onClick={onMoveEditBtn} />
            </div>
          </div>
        </div>
        <div className='profile-right'>
          <div className='consultant-detail-select-wrapper'>
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

export default ConsultantMyPage;
