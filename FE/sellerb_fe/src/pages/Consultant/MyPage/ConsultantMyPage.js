import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/Footer/Footer";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
import "./ConsultantMyPage.css";

import AttendanceLog from "../../../components/Log/AttendanceLog";
import ConsultingLog from "../../../components/Log/ConsultingLog";
import { MediButton } from "../../../components/Common/MediButton";
import {
  detailConsultantApi,
  modifyConsultantApi,
} from "../../../api/consultantApi";

function ConsultantMyPage() {
  const { id } = useParams();
  const seq = id;
  console.log(seq);

  const navigate = useNavigate();
  const [logOption, setLogOption] = useState("출결이력");

  const [consultant, setConsultant] = useState([]);
  const [isModify, setModify] = useState(false);

  const [imgBase64, setImgBase64] = useState([]); // 미리보기를 구현할 state
  const [imgFile, setImgFile] = useState("");
  const [previewUrl, setPreviewUrl] = useState(
    `${process.env.PUBLIC_URL}/img/default_img.png`,
  );

  useEffect(() => {
    detailConsultantApi(seq)
      .then((res) => {
        console.log(JSON.stringify(res.data));
        setConsultant({ ...res.data, consultantPass: "" });
      })
      .catch((err) => {
        console.log("Error");
      });
  }, []);

  const onHandleLogOption = (event) => {
    setLogOption(event.currentTarget.value);

    console.log(logOption);
  };

  function ConsultantLog(props) {
    if (logOption === "출결이력") {
      // console.log(params.consultants
      return <AttendanceLog consultant_id={seq} />;
    } else {
      return <ConsultingLog consultant_id={consultant.consultantId} />;
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
    modifyConsultantApi(seq, consultant)
      .then((res) => {
        alert("수정완료!");
      })
      .catch((err) => {
        alert("수정실패! ERROR CODE : " + err);
        console.log("ERROR!!!!!!!!!!!!!!!!!");
        console.error(err);
      });
    ChangeToModify();
  };

  const handleChange = (e) => {
    setConsultant({
      ...consultant,
      [e.target.name]: e.target.value,
    });
  };

  const Main = () => {
    return (
      <>
        <div className='con-mypage-wrapper'>
          <div className='con-mypage-left-wrapper'>
            <img
              className='con-mypage-default-img'
              alt='#'
              src={consultant.consultantImageUrl}
            />
          </div>

          <div className='InfoTextField'>
            <TextField
              id='outlined-read-only-input'
              label='사번'
              defaultValue={consultant.consultantId}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='사원명'
              defaultValue={consultant.consultantName}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='사원 Email'
              defaultValue={consultant.consultantEmail}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='PW'
              defaultValue={consultant.consultantPass}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='제품군'
              defaultValue={consultant.productGroupName}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='Button'>
            <MediButton label='수정하기' onClick={ChangeToModify} />
          </div>
        </div>
      </>
    );
  };

  return (
    <>
      <NavBar />

      <div className='wrapper'>
        {/* 왼쪽 */}
        <div id='left'>
          <div className='topText'>{/* <h2>My Page</h2> */}</div>
          {/* 상담사 이미지 */}

          {/* 상담사 Info */}
          <div>
            <div>
              {isModify ? (
                <form onSubmit={handleSubmit}>
                  <div className='InfoTextField'>
                    <TextField
                      id='outlined-read-only-input'
                      label='사번'
                      value={consultant.consultantId}
                      fullWidth={true}
                      InputProps={{ readOnly: true }}
                      disabled='true'
                      variant='filled'
                    />
                  </div>
                  <div className='InfoTextField'>
                    <TextField
                      label='사원명'
                      value={consultant.consultantName}
                      fullWidth={true}
                      InputProps={{ readOnly: true }}
                      disabled='true'
                      variant='filled'
                    />
                  </div>
                  <div className='InfoTextField'>
                    <TextField
                      label='사원 Email'
                      value={consultant.consultantEmail}
                      fullWidth={true}
                      onChange={handleChange}
                      name='consultantEmail'
                    />
                  </div>
                  <div className='InfoTextField'>
                    <TextField
                      label='PW'
                      value=''
                      fullWidth={true}
                      onChange={handleChange}
                      name='consultantPass'
                    />
                  </div>
                  <div className='InfoTextField'>
                    <TextField
                      label='제품군'
                      value={consultant.productGroupName}
                      fullWidth={true}
                      onChange={handleChange}
                      readOnly={true}
                    />
                  </div>
                  <div className='Button'>
                    <Button variant='contained' size='large' type='submit'>
                      수정 완료
                    </Button>
                    <Button
                      variant='contained'
                      color='error'
                      size='large'
                      onClick={cancelModify}
                    >
                      취소
                    </Button>
                  </div>
                </form>
              ) : (
                <Main />
              )}
            </div>
          </div>
        </div>
        {/* 오른쪽 */}
        <div id='consultant-mypage-right'>
          {/* 상담 이력 LIST */}
          <div className='profile-right'>
            <div className='consultant-detail-select-wrapper'>
              <select onChange={onHandleLogOption} value={logOption}>
                <option>출결이력</option>
                <option>상담이력</option>
              </select>
            </div>
            <div>
              <ConsultantLog consultant_id={seq} />
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default ConsultantMyPage;
