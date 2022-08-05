import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Footer, NavBar } from "../../../components/index";
import "./ConsultantRegister.css";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
import "./ConsultantRegister.css";
import { registerConsultantApi } from '../../../api/consultantApi'
import { productGroupListApi  } from '../../../api/productApi'


function ConsultantRegister() {

  const navigate = useNavigate();
  const [groupList, setGroupList] = useState([]);

  const [consultant, setConsultant] = useState({
    consultantId: "",
    consultantName: "",
    consultantEmail: "",
    consultantPass: "",
    consultantTel: "",
    productGroupName: "",
  });

  const { consultantId, consultantName, consultantEmail, consultantPass, consultantTel, productGroupName } = consultant;

  useEffect(()=>{
    productGroupListApi()
    .then((res)=>{
      console.log(res.data);

      setGroupList(res.data);
      console.log("groupList :" + groupList[0]);
    })
    .catch((err)=>{
      console.log(err);
    })
  }, [])

  const onRegisterBtn = (event)=>{
    event.preventDefault();

    registerConsultantApi(consultant)
    .then((res)=>{
      console.log(res.data);
      navigate('/manager/main');
    })
    .catch((err)=>{
      console.log(err.data);
    })
  }

  const onChange = (e) => {
    e.preventDefault();
    const { value, name } = e.target;
    setConsultant({
      ...consultant,
      [name]: value,
    });

    console.log("setConsultant:" + consultant.productGroupName);
  };


  return (
    <>
      <NavBar />

      <div className='wrapper'>
        <div id='left'>
          <div className='imageWrapper'>
            <img
              src={`${process.env.PUBLIC_URL}/img/ManagerImage.png`}
              alt='NOIMAGE'
            ></img>
          </div>
        </div>
        <div id='right'>
          <div className='topText'>
            <h2>상담사 등록</h2>
          </div>
          <form className='InfoWrapper'>
            <div className='registerField'>
              {/* 이 부분 Form 으로 바꿔주기 */}
              <TextField
                required
                label='사번'
                defaultValue={consultant.consultantId}
                fullWidth='true'
                name='consultantId'
                onChange={onChange}
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                id='outlined-disabled'
                label='사원명'
                defaultValue={consultant.consultantName}
                fullWidth='true'
                name='consultantName'
                onChange={onChange}
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                label='사원Email'
                defaultValue={consultant.consultantEmail}
                fullWidth='true'
                name='consultantEmail'
                type='email'
                onChange={onChange}
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                label='초기 비밀번호'
                defaultValue={consultant.consultantPass}
                type='password'
                fullWidth='true'
                name='consultantPass'
                onChange={onChange}
                autoComplete="on"
              />
            </div>

            <div className='registerField'>
              <TextField
                required
                label='사원 핸드폰 번호'
                defaultValue={consultant.consultantTel}
                fullWidth='true'
                name='consultantTel'
                onChange={onChange}
              />
            </div>

            <div className='registerField'>
              <TextField
                required
                select
                fullWidth='true'
                name='productGroupName'
                value={consultant.productGroupName}
                onChange={onChange}
                SelectProps={{
                  native: true,
                }}
                label="제품군을 선택하세요"
              >
                <option value="" />
                {groupList.map((option) => (
                  <option>
                    {option.productGroupName}
                  </option>
                ))}
              </TextField>
            </div>
            <div className='Button'>
              <Button onClick={onRegisterBtn} className='registerBtn' variant='contained' type='submit'>
                등록
              </Button>
              <Button className='registerBtn' variant='contained' color='error'>
                취소
              </Button>
            </div>
          </form>
        </div>
      </div>

      <Footer />
    </>
  );
}
export default ConsultantRegister;
