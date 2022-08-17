import React, { useState, useEffect } from "react";
import "./ConsultantModify.css";
import { Footer, NavBar } from "../../../components/index";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
import { SmallButton } from "../../../components/Common/SmallButton";
import { MediButton } from "../../../components/Common/MediButton";
import { Link, useNavigate, useParams } from "react-router-dom";
import { productGroupListApi } from "../../../api/productApi";
import { DangerMediButton} from '../../../components/Common/DangerMediButton'

import {
  modifyConsultantApi,
  detailConsultantApi,
} from "../../../api/consultantApi";

function ConsultantModify() {
  const navigate = useNavigate();
  const { id } = useParams();
  const seq = id;

  const [consultant, setConsultant] = useState({});
  const [groupList, setGroupList] = useState([]);
  const [managerBrand, setManagerBrand] = useState(
    sessionStorage.getItem("brandNameKor"),
  );

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
  const onHandleSubmit = (e) => {
    e.preventDefault();

    modifyConsultantApi(consultant.consultantSeq, consultant)
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log("Error");
      });

    alert("수정하시겠습니까?");

    navigate(`/manager/consultantDetail/${seq}`);
  };

  const onChange = (e) => {
    e.preventDefault();
    const { value, name } = e.target;

    setConsultant({
      ...consultant,
      [name]: value,
    });
  };
  return (
    <>
      <NavBar />
      <div className='wrapper'>
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
              {/* 이 부분 Form 으로 바꿔주기 */}
              <TextField
                required
                value={consultant.consultantId || ""}
                fullWidth='true'
                name='consultantId'
                // onChange={onChange}
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                id='outlined-disabled'
                value={consultant.consultantName || ""}
                fullWidth='true'
                name='consultantName'
                // onChange={onChange}
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
                name='productGroupName'
                value={consultant.productGroupName}
                selected={consultant.productGroupName}
                onChange={onChange}
              >
                <option value='' />
                {groupList.map((option) =>
                  option.brandName === managerBrand ? (
                    <option>{option.productGroupName}</option>
                  ) : (
                    ""
                  ),
                )}
              </TextField>
            </div>
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
      </div>
      <Footer />
    </>
  );
}

export default ConsultantModify;
