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

import {
  detailConsultantApi 
} from "../../../api/consultantApi";


function ConsultantMyPage() {
  const { id } = useParams();
  const seq = id;
  console.log(seq)

  const navigate = useNavigate();

  const [consultant, setConsultant] = useState("");


  const [isModify, setModify] = useState(false);

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
        <div>
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
              defaultValue=""
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='제품군'
              defaultValue={consultant.productGroup}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='Button'>
            <Button variant='contained' size='large' onClick={ChangeToModify}>
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
          <div className='InfoTextField'>
            <TextField
              id='outlined-read-only-input'
              label='사번'
              defaultValue={consultant.consultantId}
              fullWidth={true}
              InputProps={{ readOnly: true }}
              disabled='true'
              variant='filled'
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='사원명'
              defaultValue={consultant.consultantName}
              fullWidth={true}
              InputProps={{ readOnly: true }}
              disabled='true'
              variant='filled'
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='사원 Email'
              defaultValue={consultant.consultantEmail}
              fullWidth={true}
              onChange={handleChange}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='PW'
              defaultValue=""
              fullWidth={true}
              onChange={handleChange}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='제품군'
              defaultValue={consultant.productGroup}
              fullWidth={true}
              onChange={handleChange}
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
      </>
    );
  };
  return (
    <>
      <NavBar />
      <div className='wrapper'>
        {/* 왼쪽 */}
        <div id='left'>
          <div className='topText'>
            <h2>My Page</h2>
          </div>
          {/* 상담사 이미지 */}
          <div className='imageWrapper'>
          </div>
          {/* 상담사 Info */}
          <div>
            <div>{isModify ? <Modify /> : <Main />}</div>
          </div>
        </div>
        {/* 오른쪽 */}
        <div id='right'>
          <div className='topText'>
            <h2>상담 이력</h2>
          </div>

          <div>
            {/* 상담 이력 LIST */}

            <TableContainer component={Paper}>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>No</TableCell>
                    <TableCell>유저ID</TableCell>
                    <TableCell>제품코드</TableCell>
                    <TableCell>시작시간</TableCell>
                    <TableCell>종료시간</TableCell>
                  </TableRow>
                </TableHead>
                {/* <TableBody>
                  {dummy_data_consultInfo.map((dummy_data_consultInfo) => (
                    <TableRow key={dummy_data_consultInfo.name}>
                      <TableCell>{dummy_data_consultInfo.no}</TableCell>
                      <TableCell>{dummy_data_consultInfo.userId}</TableCell>
                      <TableCell>
                        {dummy_data_consultInfo.productCode}
                      </TableCell>
                      <TableCell>{dummy_data_consultInfo.startTime}</TableCell>
                      <TableCell>{dummy_data_consultInfo.endTime}</TableCell>
                    </TableRow>
                  ))}
                </TableBody> */}
              </Table>
            </TableContainer>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default ConsultantMyPage;
