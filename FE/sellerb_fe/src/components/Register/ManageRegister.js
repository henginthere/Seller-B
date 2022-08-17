import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { Formik, ErrorMessage } from "formik";
import { toast, ToastContainer } from "react-toastify";
import { Button, TextField } from "@mui/material";
import * as Yup from "yup";
import { useNavigate, Navigate } from "react-router-dom";
import { registerApi } from "../../api/userApi";
import { listBrandApi } from "../../api/brandApi";
import { SmallButton } from "../../components/Common/SmallButton"
import { MediButton } from "../../components/Common/MediButton"

import { Footer, NavBar } from "../index";
import "./ManageRegister.css";

function ManageRegister() {
  const navigate = useNavigate();
  const [brandList, setBrandList] = useState([]); // API로 받아올 현재 브랜드 목록
  const [selectBrand, setSelectBrand] = useState("브랜드"); // 관리자가 선택하는 brand option
  const [selectedBrandSeq, setSelectedBrandSeq] = useState("");

  const [password2, setPassWord2] = useState("");

  const [newInfo, setNewInfo] = useState({
      brandSeq: selectedBrandSeq,
      managerId: "",
      managerName: "",
      managerPass: "",
      managerTel: "",
      managerEmail: "",
      managerImageUrl: ""
  });

  const [resImg, setResImg] = useState("");
  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState("");
  const [previewUrl, setPreviewUrl] = useState(
    `${process.env.PUBLIC_URL}/img/default_img.png`
  );

  useEffect(() => {
    listBrandApi()
      .then((res) => {
        console.log("brandList:" + res.data[0]);
        setBrandList(res.data);
        console.log(brandList[0]);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const onBrandChange = (e) => {
    const { value } = e.target;
    console.log("onBrandChange: " + value);
    setSelectBrand(value);

    // 선택된 브랜드에 해당하는 BrandSeq 찾기
    const item = brandList.find((it) => it.brandNameKor === value);
    setSelectedBrandSeq(item.brandSeq);
  };

  // 가입 데이터 info
  const onHandleChange = (e) => {
    const { value, name } = e.target; 

    setNewInfo({
      ...newInfo,
      [name] : value
    })

  }

  // 이미지 관련
  const onHandleChangeFile = (event) => {
    console.log(event.target.files);
    setImgFile(event.target.files);

    // 미리보기 state
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
            // 변환해서 미리보기 이미지에 넣어주는 부분
            var base64Sub = base64.toString();
            setImgBase64((imgBase64) => [...imgBase64, base64Sub]);
          }
        };
      }
    }
  };

  const deleteImage = () => {
    setImgFile({
      image_file: "",
      preview_URL: `${process.env.PUBLIC_URL}/img/default_img.png`,
    });
    setImgBase64("");
  };

  const onImgRegisterBtn = async () => {
    const fd = new FormData();

    Object.values(imgFile).forEach((file) => fd.append("data", file));

    console.log("보낼 fd: " + fd);

    await axios
      .post("https://i7d105.p.ssafy.io/api/file/manager", fd, {
        header: {
          "Content-Type": `multipart/form-data`,
        },
      })
      .then((response) => {
        if (response.data) {
          console.log(response.data);
          setResImg(response.data);

          toast.success("이미지 등록 완료!", {
            autoClose: 800,
            position: toast.POSITION.TOP_CENTER
          });

        }
      })
      .catch((error) => {
        toast.error("이미지를 등록해주세요!", {
          autoClose: 1000,
          position: toast.POSITION.TOP_CENTER
        });
        console.log("Error");
      });



  };

  // 회원가입 버튼
  const registerBtn = () => {

    const userInfo = {
      brandSeq: selectedBrandSeq,
      managerId: newInfo.managerId,
      managerName: newInfo.managerName,
      managerPass: newInfo.managerPass,
      managerTel: newInfo.managerTel,
      managerEmail: newInfo.managerEmail,
      managerImageUrl: resImg
    };

    registerApi(userInfo)
      .then((res) => {
        console.log(res.data);  

        console.log("가입 완료")
        
        // navigate("/");
 
      })
      .catch((err) => {
        console.log(err);
      });

    // navigate("/main");
    navigate("/");

  };

  // Axios
  // const submit = async (values) => {
  //   const { brand, managerName, id, password, phone, email } = values;
  //   console.log(values.brand);
  // };

  return (
    <div className="manage-register-page">
      <div className="login">
        <div className="login-title-wrapper">
        <h3 className="login-title">Sign Up</h3>
        </div>
        <hr />
        <div className="signup-profile-img-wrapper">
        <div className="signUp-row">
          <div className="row-left-label">
            <div className="left-label-text">프로필</div>
          </div>
          <div className="row-right-profile">
            <div className="right-profile-wrapper">
              <div className="right-profile-content">
                {imgFile === "" ? (
                  <img
                    className="manage-preview-img"
                    alt="#"
                    src={previewUrl}
                  />
                ) : null}
                {imgBase64.map((item) => {
                  return (
                    <div>
                      <img
                        className="manage-profile-img"
                        src={item}
                        alt="First Slide"
                      />
                    </div>
                  );
                })}
              </div>
              <div className="product-img-bottom-wrapper">
                <input
                  // className="img-btn"
                  multiple="multiple"
                  type="file"
                  id="file"
                  name="file"
                  accept="image/*"
                  onChange={onHandleChangeFile}
                  style={{display:"none"}}
                />
                <div className="product-register-small-btn">
                  <label for="file">
                    <div className="find-file-btn">파일찾기</div>
                  </label>
                  <SmallButton label="이미지 등록" onClick={onImgRegisterBtn} />
                  <ToastContainer />
                </div>
              </div>
            </div>
          </div>
        </div>
        </div>


            <div className="signup-wrapper">
              {/* <ToastContainer /> */}
              <form
                // onSubmit={handleSubmit}
                autoComplete="off"
                className="login-form"
              >
                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">제품 브랜드</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <select onChange={onBrandChange} value={selectBrand}>
                          <option>--브랜드 선택--</option>
                          {brandList.map((ele, i) => {
                            return <option value={ele.brandNameKor} >{ele.brandNameKor}</option>;
                          })}
                        </select>
                      </div>
                    </div>
                  </div>
                  {/* </label> */}
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">이름</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={newInfo.managerName}
                          name="managerName"
                          type="text"
                          variant="outlined"
                          onChange={onHandleChange}
                          placeholder="이름"
                          className="size"
                        />
                      </div>
                    </div>
               
                  </div>
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">아이디</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={newInfo.managerId}
                          name="managerId"
                          type="text"
                          variant="outlined"
                          onChange={onHandleChange}
                          placeholder="아이디"
                          className="size"
                        />
                      </div>
                    </div>
                  </div>
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">비밀번호</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={newInfo.managerPass}
                          name="managerPass"
                          variant="outlined"
                          onChange={onHandleChange}
                          placeholder="비밀번호"
                          className="size"
                        />
                      </div>
                    </div>
                  </div>
                </div>

                {/* <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">비밀번호 확인</div>
                  </div> */}
                  {/* <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={password2}
                          name="password2"
                          type="text"
                          variant="outlined"
                          onChange={onHandleChange}
                          placeholder="비밀번호 확인"
                          className="size"
                        />
                      </div>
                    </div>
                  </div> */}
                  {/*  */}
                {/* </div> */}

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">Tel.</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={newInfo.managerTel}
                          name="managerTel"
                          type="text"
                          variant="outlined"
                          onChange={onHandleChange}
                          placeholder="핸드폰번호"
                          className="size"
                        />
                      </div>
                    </div>
                  </div>
                </div>

                <div className="signUp-row">
                  <div className="row-left-label">
                    <div className="left-label-text">Email</div>
                  </div>
                  <div className="row-right">
                    <div className="right-wrapper">
                      <div className="right-content">
                        <input
                          value={newInfo.managerEmail}
                          name="managerEmail"
                          type="text"
                          variant="outlined"
                          onChange={onHandleChange}
                          placeholder="Email"
                          className="size"
                        />
                      </div>
                    </div>
                  </div>
                </div>

                <Button
                  color="primary"
                  variant="contained"
                  fullWidth
                  type="submit"
                  onClick={registerBtn}
                >
                  회원가입
                </Button>
                {/* <MediButton 
                  label="회원가입"
                  // onClick={registerBtn(values)}
                /> */}
              </form>
            </div>
     
      </div>
      <Footer />
    </div>
  );
}

export default ManageRegister;
