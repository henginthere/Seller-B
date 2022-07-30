import React from "react";
import { Footer, NavBar } from "../../../components/index";
import "./ConsultantRegister.css";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
import "./ConsultantRegister.css";
function ConsultantRegister() {
  const data = {
    id: "",
    name: "",
    eamil: "",
    tel: "",
    pw: "",
    product: "",
  };
  const product_list = [
    { value: "TV", label: "TV" },
    { value: "phone", label: "Phone" },
    { value: "airConditioner", label: "에어컨" },
    { value: "Refrigerator", label: "냉장고" },
    { value: "airCleaner", label: "공기청정기" },
  ];

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
                defaultValue={data.id}
                fullWidth='true'
                name='id'
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                id='outlined-disabled'
                label='사원명'
                defaultValue={data.name}
                fullWidth='true'
                name='name'
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                label='사원Email'
                defaultValue={data.email}
                fullWidth='true'
                name='email'
                type='email'
              />
            </div>
            <div className='registerField'>
              <TextField
                required
                label='초기 비밀번호'
                defaultValue={data.pw}
                type='password'
                fullWidth='true'
                name='pw'
              />
            </div>

            <div className='registerField'>
              <TextField
                required
                label='사원 핸드폰 번호'
                defaultValue={data.tel}
                fullWidth='true'
                name='tel'
              />
            </div>

            <div className='registerField'>
              <TextField
                required
                select
                label='제품군'
                value={data.product}
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
              <Button className='registerBtn' variant='contained' type='submit'>
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
