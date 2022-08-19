import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import "./ManagerMyPage.css";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import { getManagerInfoApi } from "../../../api/managerApi";
import { indigo } from "@mui/material/colors";

import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";
import { BackMediButton } from "../../../components/Common/BackMediButton"

function ManagerMyPage() {
    const navigate = useNavigate(); 
    const [manager, setManager] = useState([]);
    const [managerSeq, setManagerSeq] = useState(sessionStorage.getItem("seq"));
    const [brandNameKor, setBrandNameKor] = useState("");

    useEffect(() => {
        console.log("useEffect:" + sessionStorage.getItem("seq"));
        getManagerInfoApi(managerSeq)
          .then((res) => {
            setManager(res.data);
            setBrandNameKor(res.data.brand.brandNameKor);
          })
          .catch((err) => {
            console.log(err);
          });
    }, []);


  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
    console.log("useEffect:" + sessionStorage.getItem("seq"));
    getManagerInfoApi(managerSeq)
      .then((res) => {
        setManager(res.data);
        setBrandNameKor(res.data.brand.brandNameKor);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  // 수정 페이지로 이동
  const onMoveEditBtn = () => {
    navigate(`/manager/mypage/edit/${managerSeq}`);
  };

  return (
    <>
        <NavBar />
        <div className="manager-profile-title">매니저 프로필</div>
        <div className="consultant-profile-container">
        <div className="manager-profile-wrapper">
          <div className="con-mypage-left-wrapper">
            <img
              className='con-mypage-default-img'
              alt='#'
              src={manager.managerImageUrl}
            />
          </div>
          <div className='con-mypage-profile-left'>
            <div className='con-profile-element'>
              <p>아이디</p>
              <div>{manager.managerId}</div>
            </div>
            <div className='con-profile-element'>
              <p>이름</p>
              <div>{manager.managerName}</div>
            </div>
            <div className='con-profile-element'>
              <p>Email</p>
              <div>{manager.managerEmail}</div>
            </div>
            <div className='con-profile-element'>
              <p>Pnum</p>
              <div>{manager.managerTel}</div>
            </div>
            <div style={{ display: "flex", marginLeft: "15px" }}>
              <BackMediButton label="수정하기" onClick={onMoveEditBtn} />
            </div>
          </div>

        </div>
      </div>
      <Footer />
    </>
  );
}

export default ManagerMyPage;
