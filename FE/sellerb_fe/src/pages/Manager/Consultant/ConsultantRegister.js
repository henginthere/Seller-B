import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Footer, NavBar } from "../../../components/index";
import "./ConsultantRegister.css";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
import "./ConsultantRegister.css";
import { registerConsultantApi } from "../../../api/consultantApi";
import { productGroupListApi } from "../../../api/productApi";

function ConsultantRegister() {
  const navigate = useNavigate();
  const [groupList, setGroupList] = useState([]);
  const [testList, setTestList] = useState([]);

  const [brandGroupList, setBrandGroupList] = useState([]);
  const [managerBrand, setManagerBrand] = useState(
    localStorage.getItem("brandNameKor")
  );

  const [consultant, setConsultant] = useState({
    consultantId: "",
    consultantName: "",
    consultantEmail: "",
    consultantPass: "",
    consultantTel: "",
    prodcutGroupSeq: "",
    productGroupName: "",
    consultantImageUrl: "",
  });

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  //const [imgFile, setImgFile] = useState(null); // 파일 그 자체를 받을 state
  const [imgFile, setImgFile] = useState({
    image_file: "",
    preview_URL: `${process.env.PUBLIC_URL}/img/default_img.png`,
  });

  const {
    consultantId,
    consultantName,
    consultantEmail,
    consultantPass,
    consultantTel,
    productGroupName,
  } = consultant;

  // 이미지 파일 관련
  const handleChangeFile = (event) => {
    console.log(event.target.files);
    setImgFile(event.target.files);

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
            var base64Sub = base64.toString();

            setImgBase64((imgBase64) => [...imgBase64, base64Sub]);
          }
        };
      }
    }
  };
  // 이미지 파일 삭제
  const deleteImage = () => {
    setImgFile({
      image_file: "",
      preview_URL: `${process.env.PUBLIC_URL}/img/default_img.png`,
    });

    setImgBase64("");
  };

  // 서버에 파일 & 제품정보 전송 : FormData()
  const onProductSubmitBtn = async (e) => {
    const formData = new FormData(); // FormData객체 생성

    // Form객체에 파일값 추가 : append(key, value) or append(key, value, filename)
    formData.append("file", e.target.files[0]);
    console.log(formData);

    // const config = {
    //   Headers: {
    //     "content-type": "multipart/form-data",
    //   },
    // };

    // 제품 정보
    setConsultant({
      ...consultant,
      formData: formData,
    });
  };

  useEffect(() => {
    productGroupListApi()
      .then((res) => {
        // const item = brandList.find((it) => it.brandNameKor === value)
        setGroupList(res.data); // groupList
        // console.log("groupListData:" + groupList[0].productGroupName)
        // setGroupList(groupList.find((it)=> it.brandName === managerBrand));
        // console.log("managerBrand State:" + managerBrand);
        // const sample = groupList.find (data => data.brandName === managerBrand);
        // console.log("sample:" + sample)
        // setTestList(sample);
        // console.log(testList[0].productGroupName)
        // const managerBrandName = localStorage.getItem("brandNameKor");
        // console.log(groupList[0])
        // const list = groupList.find((it)=> it.brandNameKor === managerBrandName) // list : 매니저 브랜드의! 제품군 목록
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const onRegisterBtn = (event) => {
    event.preventDefault();
    // productGroupName에 맞는 Seq찾기

    // const item = groupList.find(
    //     (it) => it.brandName === managerBrand
    //     && it.productGroupName === consultant.productGroupName
    //     );

    // console.log("item: " + item.brandName + " , " + item.productGroupSeq);

    // const name = "productGroupSeq";
    // const value = item.productGroupSeq;
    // setConsultant({
    //   ...consultant,
    //   [name]: value,
    // });

    // console.log(consultant.prodcutGroupSeq)

    // const info = {
    //   consultantId: consultant.consultantId,
    //   consultantName : consultant.consultantName,
    //   consultantEmail : consultant.consultantEmail,
    //   consultantPass : consultant.consultantPass,
    //   consultantTel : consultant.consultantTel,
    //   prodcutGroupSeq : item.prodcutGroupSeq,
    //   consultantImageUrl:"",
    // }

    registerConsultantApi(consultant)
      .then((res) => {
        console.log(res.data);
        navigate("/manager/main");
      })
      .catch((err) => {
        console.log(err.data);
      });
  };

  const onChange = (e) => {
    e.preventDefault();

    const { value, name } = e.target;
    setConsultant({
      ...consultant,
      [name]: value,
    });

    console.log("setConsultant:" + consultant.productGroupName);
  };

  const onGroupChange = (e) => {
    e.preventDefault();

    const item = groupList.find(
      (it) =>
        it.brandName === managerBrand &&
        it.productGroupName === consultant.productGroupName
    );

    console.log("item: " + item.brandName + " , " + item.productGroupSeq);

    const { value, name } = e.target;
   
    setConsultant({
      ...consultant,
      [name]: value,
    });

    console.log("onGroupChange:" + consultant.productGroupName);
  };

  return (
    <>
      <NavBar />

      <div className="wrapper">
        <div id="left">
          <div className="imageWrapper">
            {imgFile.image_file === "" ? (
              <img className="preview-img" alt="#" src={imgFile.preview_URL} />
            ) : null}

            {imgBase64.map((item) => {
              return (
                <div className="img-wrapper">
                  <img src={item} alt="Frist Slide" />
                </div>
              );
            })}
          </div>

          <input
            className="img-btn"
            type="file"
            accept="image/*"
            id="file"
            onChange={handleChangeFile}
          />
          {/* <button onClick={deleteImage}>이미지 삭제</button> */}
          <button className="bottom-btn" onClick={onProductSubmitBtn}>
            업로드하기
          </button>
        </div>
        <div id="right">
          <div className="topText">
            <h2>상담사 등록</h2>
          </div>
          <form className="InfoWrapper">
            <div className="registerField">
              {/* 이 부분 Form 으로 바꿔주기 */}
              <TextField
                required
                label="사번"
                defaultValue={consultant.consultantId}
                fullWidth="true"
                name="consultantId"
                onChange={onChange}
              />
            </div>
            <div className="registerField">
              <TextField
                required
                id="outlined-disabled"
                label="사원명"
                defaultValue={consultant.consultantName}
                fullWidth="true"
                name="consultantName"
                onChange={onChange}
              />
            </div>
            <div className="registerField">
              <TextField
                required
                label="사원Email"
                defaultValue={consultant.consultantEmail}
                fullWidth="true"
                name="consultantEmail"
                type="email"
                onChange={onChange}
              />
            </div>
            <div className="registerField">
              <TextField
                required
                label="초기 비밀번호"
                defaultValue={consultant.consultantPass}
                type="password"
                fullWidth="true"
                name="consultantPass"
                onChange={onChange}
                autoComplete="on"
              />
            </div>

            <div className="registerField">
              <TextField
                required
                label="사원 핸드폰 번호"
                defaultValue={consultant.consultantTel}
                fullWidth="true"
                name="consultantTel"
                onChange={onChange}
              />
            </div>

            <div className="registerField">
              <TextField
                required
                select
                fullWidth="true"
                name="productGroupName"
                value={consultant.productGroupName}
                onChange={onChange}
                // onChange={onGroupChange}
                SelectProps={{
                  native: true,
                }}
                label="제품군을 선택하세요"
              >
                <option value="" />
                {groupList.map((option) =>
                  option.brandName === managerBrand ? (
                    <option>{option.productGroupName}</option>
                  ) : (
                    ""
                  )
                )}
              </TextField>
            </div>
            <div className="Button">
              <Button
                onClick={onRegisterBtn}
                className="registerBtn"
                variant="contained"
                type="submit"
              >
                등록
              </Button>
              <Button className="registerBtn" variant="contained" color="error">
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
