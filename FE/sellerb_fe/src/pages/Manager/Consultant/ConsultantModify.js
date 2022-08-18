import React, { useState, useEffect } from "react";
import "./ConsultantModify.css";
import { Footer, NavBar } from "../../../components/index";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";
import { Link, useNavigate, useParams } from "react-router-dom";
import { DangerSmallButton } from '../../../components/Common/DangerSmallButton'
import { DangerMediButton} from '../../../components/Common/DangerMediButton'
import AttendanceLog from "../../../components/Log/AttendanceLog";
import ConsultingLog from "../../../components/Log/ConsultingLog";
import { productGroupListApi } from "../../../api/productApi";
import axios from 'axios'
import {
  modifyConsultantApi,
  detailConsultantApi,
} from "../../../api/consultantApi";
function ConsultantModify() {
  const navigate = useNavigate();
  const { id } = useParams();
  const seq = id;
  const [editPass, setEditPass] = useState();
  const [consultant, setConsultant] = useState([]);
  const [groupList, setGroupList] = useState([]);
  const [logOption, setLogOption] = useState("출결이력");
  const product_list = [
    { value: "TV", label: "TV" },
    { value: "phone", label: "Phone" },
    { value: "airConditioner", label: "에어컨" },
    { value: "Refrigerator", label: "냉장고" },
    { value: "airCleaner", label: "공기청정기" },
  ];

  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
    detailConsultantApi(seq)
      .then((res) => {
        console.log("res.data:" + res.data.consultantId);

        // consultant = res.data;
        setConsultant(res.data);
        console.log("img: " + res.data.consultantImageUrl);
        console.log("consultant:" + consultant.consultantId);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffect(()=>{
    productGroupListApi()
      .then((res) => {
        setGroupList(res.data); // groupList
      })
      .catch((err) => {
        console.log(err);
      });
  }, [])

  // 최종적으로 수정 완료 버튼을 누르면 api로 DB에 반영하기 위한 함수
  // const onHandleSubmit = (e) => {
  //   e.preventDefault();
    
  //   console.log("컨설턴트 수정 :" + JSON.stringify(consultant))

  //   modifyConsultantApi(consultant.consultantSeq, consultant)
  //     .then((res) => {
  //       console.log(res.data);
  //     })
  //     .catch((err) => {
  //       console.log("Error");
  //     });

  //   alert("수정하시겠습니까?");

  //   navigate(`/manager/consultantDetail/${seq}`);
  // };
  // 바뀔때마다 setData로 수정된 데이터로 바꿔줌
  const onChange = (e) => {
    e.preventDefault();

    const { value, name } = e.target;
    setConsultant({
      ...consultant,
      [name]: value,
    });
  };

  function ConsultantLog(props) {
    if (logOption === "출결이력") {
      // console.log(params.consultants
      return <AttendanceLog consultant_id={seq} />;
    } else {
      return <ConsultingLog consultant_id={seq} />;
    }
  }

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
  



    navigate("/main");


  };

  const onHandleLogOption = (event) => {
    setLogOption(event.currentTarget.value);

    console.log(logOption);
  };

  const onDeleteBtn = () =>{
    detailConsultantApi(seq)
    .then((res)=>{
      console.log("Success");
    })
    .catch((err)=>{
      console.log("Error");
    })
  }

    // editPass Handling
    const onChangePass = (e) => {
      setEditPass(e.target.value);
    };
  // state Handling
  const onChangeInfo = (e) => {
    const { name, value } = e.target;
    setConsultant({
      ...consultant,
      [name]: value,
    });
  };


  return (
    <>
      <NavBar />
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
            <input
                defaultValue={consultant.consultantEmail}
                name='consultantEmail'
                onChange={onChangeInfo}
              />
          </div>
          <div className="con-profile-element">
           <p>사원 Pnum</p>
           <input
                defaultValue={consultant.consultantTel}
                name='consultantTel'
                onChange={onChangeInfo}
              />
          </div>
          <div className="con-profile-element">
            <p>비밀번호</p>
            <input value={editPass} onChange={onChangePass} />
          </div>         
          <div style={{display:"flex", width:"300px"}}>
            <SmallButton label="수정완료" onClick={onEditCompleteBtn} />
            <DangerSmallButton label="삭제하기" onClick={onDeleteBtn} />
          </div>
        </div>
        {/*  */}
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

export default ConsultantModify;
