import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
import "./ConsultantMyPage.css";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import "./ConsultantMyPage.css"

import AttendanceLog from "../../../components/Log/AttendanceLog";
import ConsultingLog from "../../../components/Log/ConsultingLog";

import { detailConsultantApi } from "../../../api/consultantApi";

function ConsultantMyPage() {
  const { id } = useParams();
  const seq = id;
  console.log(seq);

  const navigate = useNavigate();
  const [logOption, setLogOption] = useState("출결이력");


  const [consultant, setConsultant] = useState([]);
  const [isModify, setModify] = useState(false);

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState({
    image_file: "",
    preview_URL: `${process.env.PUBLIC_URL}/img/default_img.png`,
  });

  useEffect(() => {
    detailConsultantApi(seq)
      .then((res) => {
        console.log(JSON.stringify(res.data));
        setConsultant(res.data);
      })
      .catch((err) => {
        console.log("Error");
      });
  }, []);

  const onHandleLogOption = (event)=>{
    setLogOption(event.currentTarget.value);

    console.log(logOption);
  }

  function ConsultantLog (props) {
    if(logOption === '출결이력'){
        // console.log(params.consultants
        return <AttendanceLog consultant_id = {seq} />
    }
    else{
        return <ConsultingLog consultant_id = {seq} />
    }
 }


  // 이미지 파일 관련
  const handleChangeFile = (event) => {
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

  const deleteImage = () => {
    setImgFile({
      image_file: "",
      preview_URL: `${process.env.PUBLIC_URL}/img/default_img.png`,
    });

    setImgBase64("");
  };

  // 수정 페이지 인지 아닌지 바꿔주는 함수
  const ChangeToModify = () => {
    setModify(!isModify);
  };

  const cancelModify = () => {
    ChangeToModify();
  };

  // 최종적으로 수정 완료 버튼을 누르면 api로 DB에 반영하기 위한 함수
  const handleSubmit = (e) => {
    e.preventDefault();

    alert("수정하시겠습니까?");

    ChangeToModify();
  };

  const handleChange = (e) => {
    e.preventDefault();

    // [e.target.name] = e.target.value;
  };

  const Main = () => {
    return (
      <>
        <div className="con-mypage-wrapper">
          <div className="con-mypage-left-wrapper">
            {imgFile.image_file === "" ? (
              <img 
              className="con-mypage-default-img"  
              alt="#" src={imgFile.preview_URL} />
            ) : null}
            {imgBase64.map((item) => {
              return (
                <div>
                  <img src={item} alt="First Slide" />
                </div>
              );
            })}
          </div>

          <div className="InfoTextField">
            <TextField
              id="outlined-read-only-input"
              label="사번"
              defaultValue={consultant.consultantId}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="사원명"
              defaultValue={consultant.consultantName}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="사원 Email"
              defaultValue={consultant.consultantEmail}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="PW"
              defaultValue=""
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="제품군"
              defaultValue={consultant.productGroupName}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className="Button">
            <Button variant="contained" size="large" onClick={ChangeToModify}>
              수정
            </Button>
          </div>
        </div>
      </>
    );
  };

  const Modify = () => {
    return (
      <>
        <form onSubmit={handleSubmit}>
          <div className="InfoTextField">
            <TextField
              id="outlined-read-only-input"
              label="사번"
              defaultValue={consultant.consultantId}
              fullWidth={true}
              InputProps={{ readOnly: true }}
              disabled="true"
              variant="filled"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="사원명"
              defaultValue={consultant.consultantName}
              fullWidth={true}
              InputProps={{ readOnly: true }}
              disabled="true"
              variant="filled"
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="사원 Email"
              defaultValue={consultant.consultantEmail}
              fullWidth={true}
              onChange={handleChange}
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="PW"
              defaultValue=""
              fullWidth={true}
              onChange={handleChange}
            />
          </div>
          <div className="InfoTextField">
            <TextField
              label="제품군"
              defaultValue={consultant.productGroup}
              fullWidth={true}
              onChange={handleChange}
            />
          </div>
          <div className="Button">
            <Button variant="contained" size="large" type="submit">
              수정 완료
            </Button>
            <Button
              variant="contained"
              color="error"
              size="large"
              onClick={cancelModify}
            >
              취소
            </Button>
          </div>
        </form>
      </>
    );
  };
  return (
    <>
      <NavBar />
      <div className="wrapper">
        {/* 왼쪽 */}
        <div id="left">
          <div className="topText">
            <h2>My Page</h2>
          </div>
          {/* 상담사 이미지 */}
      
          {/* 상담사 Info */}
          <div>
            <div>{isModify ? <Modify /> : <Main />}</div>
          </div>
        </div>
        {/* 오른쪽 */}
        <div id="right">

            {/* 상담 이력 LIST */}
            <div className="profile-right">
          <div className="select-wrapper">
            <select onChange={onHandleLogOption} value={logOption}>
              <option >출결이력</option>
              <option>상담이력</option>
            </select>
          </div>
          <div className="attendance-log">
            <ConsultantLog consultant_id = {seq} />
          </div>
        </div>


        </div>
      </div>
      <Footer />
    </>
  );
}

export default ConsultantMyPage;
