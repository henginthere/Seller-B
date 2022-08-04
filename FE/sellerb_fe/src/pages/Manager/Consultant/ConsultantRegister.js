import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Footer, NavBar } from "../../../components/index";
import "./ConsultantRegister.css";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
import "./ConsultantRegister.css";
import { registerConsultantApi } from '../../../api/consultantApi'


function ConsultantRegister() {

  // const data= ({
  //   id: "",
  //   name: "",
  //   email: "",
  //   pass: "",
  //   tel: "",
  //   // product: "",
  // });

  const [consultant, setConsultant] = useState({
    id: "",
    name: "",
    email: "",
    pass: "",
    tel: "",
    // product: "",
  });

  const { id, name, email, pass, tel } = consultant;

  const product_list = [
    { value: "TV", label: "TV" },
    { value: "phone", label: "Phone" },
    { value: "airConditioner", label: "에어컨" },
    { value: "Refrigerator", label: "냉장고" },
    { value: "airCleaner", label: "공기청정기" },
  ];

  const onRegisterBtn = ()=>{

    console.log("onRegisterBtn: " + consultant)

    registerConsultantApi(consultant)
    .then((res)=>{
      console.log(res.data);
    })
    .catch((err)=>{
      console.log(err.data);
    })
  }

  // const handleChange = (e) => {
  //   e.preventDefault();
  //   // console.log("handleChange!");
  //   consultant[e.target.name] = e.target.value;
  //   // console.log(e.target.name + " : " + e.target.value);
  // };

  const onChange = (e) => {
    e.preventDefault();
    const { value, name } = e.target;
    setConsultant({
      ...consultant,
      [name]: value,
    });

    console.log("setConsultant:" + consultant);
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
                defaultValue={consultant.id}
                fullWidth='true'
                name='id'
                onChange={onChange}
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                id='outlined-disabled'
                label='사원명'
                defaultValue={consultant.name}
                fullWidth='true'
                name='name'
                onChange={onChange}
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                label='사원Email'
                defaultValue={consultant.email}
                fullWidth='true'
                name='email'
                type='email'
                onChange={onChange}
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                label='초기 비밀번호'
                defaultValue={consultant.pass}
                type='password'
                fullWidth='true'
                name='pass'
                onChange={onChange}
                autoComplete="on"
              />
            </div>

            <div className='registerField'>
              <TextField
                required
                label='사원 핸드폰 번호'
                defaultValue={consultant.tel}
                fullWidth='true'
                name='tel'
                onChange={onChange}
              />
            </div>

            <div className='registerField'>
              <TextField
                required
                select
                label='제품군'
                value={consultant.product}
                fullWidth='true'
                name='product'
                SelectProps={{
                  native: true,
                }}
              >
                {product_list.map((option) => (
                  <option key={option.value} value={option.value}>
                    {option.label}
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
