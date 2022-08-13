import React, { useState, useEffect } from "react";
import "./ConsultantModify.css";
import { Footer, NavBar } from "../../../components/index";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";

import { Link, useNavigate, useParams } from "react-router-dom";

import { modifyConsultantApi, detailConsultantApi } from "../../../api/consultantApi"

function ConsultantModify() {
  const navigate = useNavigate();
  const { id } = useParams();
  const seq = id; 

  const [consultant, setConsultant] = useState({
    consultantId: "",
    consultantName: "",
    consultantEmail:"",
    consultantTel :"",
    productGroupName: "",
    productGroupSeq: "",
    consultantImageUrl: "",
    brandName: ""
  });

  const product_list = [
    { value: "TV", label: "TV" },
    { value: "phone", label: "Phone" },
    { value: "airConditioner", label: "에어컨" },
    { value: "Refrigerator", label: "냉장고" },
    { value: "airCleaner", label: "공기청정기" },
  ];

  useEffect(() => {
    detailConsultantApi(seq)
    .then((res)=>{
        console.log("res.data:" + res.data.consultantId);
        
        // consultant = res.data;
        setConsultant(res.data); 
        console.log("img: "+ res.data.consultantImageUrl)
        console.log("consultant:" + consultant.consultantId);
    })
    .catch((err)=>{
        console.log(err);
    })
  }, []);

  // 최종적으로 수정 완료 버튼을 누르면 api로 DB에 반영하기 위한 함수
  const onHandleSubmit = (e) => {
    e.preventDefault();

    const info = {
      "consultantId" : consultant.consultantId,
      "consultantName": consultant.consultantId,
      "consultantEmail": consultant.consultantEmail,
      "consultantTel": consultant.consultantTel,
      "consultantPass": consultant.consultantPass,
      "productGroupSeq": "",
      "consultantImageUrl": ""
    }

    modifyConsultantApi(info)
    .then((res)=>{
      console.log(res.data);
    })
    .catch((err)=>{
      console.log("Error");
    })

    alert("수정하시겠습니까?");

    navigate(`/manager/consultantDetail/${seq}`);

  };
  // 바뀔때마다 setData로 수정된 데이터로 바꿔줌
  const onChange = (e) => {
    e.preventDefault();

    const { value, name } = e.target;

    setConsultant({
      ...consultant,
      [name] : value,
    });

  };
  return (
    <>
      <NavBar />
      <div className='wrapper'>
        <div id='left'>
          <div className='imageWrapper'>
            <img
              src={consultant.consultantImageUrl}
              alt='NOIMAGE'
            ></img>
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
                autocomplete="on"
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
                // defaultValue={consultant.productGroupName}
                SelectProps={{
                  native: true,
                }}
                onChange={onChange}
              >
                {product_list.map((option) => (
                  option.value === consultant.productGroupName 
                  ? ""
                  :
                  <option key={option.value} value={option.value}>
                    {option.label}
                  </option>
                ))}
              </TextField>
            </div>
            <div className='Button'>
              <Button onClick={onHandleSubmit} className='registerBtn' variant='contained' type='submit'>
                수정완료
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
