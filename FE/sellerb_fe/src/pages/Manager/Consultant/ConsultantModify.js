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
      consultantTel: consultant.consultantTel,
      productGroupSeq: consultant.consultantEmail,
      consultantImageUrl: consultant.consultantImageUrl,
    };

    modifyConsultantApi(seq, EditInfo)
    .then((res)=>{
      console.log(JSON.stringify(res.data));
      console.log("success");
    })
    .catch((err)=>{

      console.log("Error");
    })

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
            <p>제품군</p>
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
      {/* <div className='wrapper'>
        <div id='left'>
          <div className='imageWrapper'>
            <img src={consultant.consultantImageUrl} alt='NOIMAGE'></img>
          </div>
        </div>
        <div id='right'>
          <div className='topText'>
            <h2>상담사 수정</h2>
          </div>
          <form className='InfoWrapper'>
            <div className='registerField'>
              <TextField
                required
                value={consultant.consultantId || ""}
                fullWidth='true'
                name='consultantId'
                onChange={onChange}
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                id='outlined-disabled'
                value={consultant.consultantName || ""}
                fullWidth='true'
                name='consultantName'
                onChange={onChange}
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                value={consultant.consultantEmail || ""}
                fullWidth='true'
                name='consultantEmail'
                onChange={onChange}
                type='email'
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                value={consultant.consultantPass || ""}
                type='password'
                onChange={onChange}
                fullWidth='true'
                name='consultantPass'
                autocomplete='on'
              />
            </div>

            <div className='registerField'>
              <TextField
                required
                value={consultant.consultantTel || ""}
                onChange={onChange}
                fullWidth='true'
                name='consultantTel'
              />
            </div>

            <div className='registerField'>
              <TextField
                required
                select
                fullWidth='true'
                name='productGroupSeq'
                value={consultant.productGroupName}
                selected={consultant.productGroupName}
                SelectProps={{
                  native: true,
                }}
                onChange={onChange}
              >
                {product_list.map((option) =>
                  option.value === consultant.productGroupName ? (
                    ""
                  ) : (
                    <option key={option.value} value={option.value}>
                      {option.label}
                    </option>
                  ),
                )}
              </TextField> */}
            {/* </div>
            <div className='Button'>
              <MediButton
                label="수정완료"
                onClick={onHandleSubmit}
                className='registerBtn'
                variant='contained'
               />
             
              <DangerMediButton className='registerBtn' label="취소" variant='contained' color='error' />
            </div>
          </form>
        </div>
      </div> */}
      <Footer />
    </>
  );
}

export default ConsultantModify;
