import React from "react";
import "./ConsultantModify.css";
import { Footer, NavBar } from "../../../components/index";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";

function ConsultantModify() {
  const dummy_data = {
    id: "U20220730",
    name: "임상담",
    email: "dlacogus5239@naver.com",
    tel: "010-9999-8888",
    pw: "U20220730",
    product: "Phone",
  };
  const product_list = [
    { value: "TV", label: "TV" },
    { value: "phone", label: "Phone" },
    { value: "airConditioner", label: "에어컨" },
    { value: "Refrigerator", label: "냉장고" },
    { value: "airCleaner", label: "공기청정기" },
  ];
  var data = { ...dummy_data };

  const cancelModify = () => {
    data = { ...dummy_data };
  };
  // 최종적으로 수정 완료 버튼을 누르면 api로 DB에 반영하기 위한 함수
  const handleSubmit = (e) => {
    e.preventDefault();

    alert("수정하시겠습니까?");

    // 임시로 메인으로 돌아가게 함
    console.log("이전 데이터 : " + dummy_data.product);
    console.log("바뀐 데이터 : " + data.product);
  };
  // 바뀔때마다 setData로 수정된 데이터로 바꿔줌
  const handleChange = (e) => {
    e.preventDefault();
    // console.log("handleChange!");
    data[e.target.name] = e.target.value;
    console.log(e.target.name + " : " + e.target.value);
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
            <h2>상담사 수정</h2>
          </div>
          <form className='InfoWrapper' onSubmit={handleSubmit}>
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
                defaultvalue={data.product}
                fullWidth='true'
                name='product'
                SelectProps={{
                  native: true,
                }}
                onChange={handleChange}
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
                수정
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

export default ConsultantModify;
