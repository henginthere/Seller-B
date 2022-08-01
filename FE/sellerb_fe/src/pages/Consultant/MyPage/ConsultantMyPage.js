import React, { useState } from "react";
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
function ConsultantMyPage() {
  // 초기 더미 데이터
  const dummy_data = {
    id: "U2022012",
    name: "셀러비",
    email: "sellerjy@sellovy.com",
    pw: "U2022012",
    tel: "010-5555-5555",
    product: "TV",
  };
  const dummy_data_consultInfo = [
    {
      no: 1,
      userId: "user_1",
      productCode: "ABC12345",
      startTime: "11:11",
      endTime: "11:22",
    },
    {
      no: 2,
      userId: "user_2",
      productCode: "ABC12345",
      startTime: "11:11",
      endTime: "11:22",
    },
    {
      no: 3,
      userId: "user_3",
      productCode: "ABC12345",
      startTime: "11:11",
      endTime: "11:22",
    },
    {
      no: 4,
      userId: "user_4",
      productCode: "ABC12345",
      startTime: "11:11",
      endTime: "11:22",
    },
    {
      no: 5,
      userId: "user_5",
      productCode: "ABC12345",
      startTime: "11:11",
      endTime: "11:22",
    },
    {
      no: 6,
      userId: "user_6",
      productCode: "ABC12345",
      startTime: "11:11",
      endTime: "11:22",
    },
  ];
  const [isModify, setModify] = useState(false);
  // 수정 된 데이터
  var data = { ...dummy_data };
  // 수정 페이지 인지 아닌지 바꿔주는 함수
  const ChangeToModify = () => {
    setModify(!isModify);
    console.log("isModify : " + isModify);
  };
  const cancelModify = () => {
    data = { ...dummy_data };
    ChangeToModify();
  };
  // 최종적으로 수정 완료 버튼을 누르면 api로 DB에 반영하기 위한 함수
  const handleSubmit = (e) => {
    e.preventDefault();

    alert("수정하시겠습니까?");

    // 임시로 메인으로 돌아가게 함
    console.log("이전 데이터 : " + dummy_data);
    console.log("바뀐 데이터 : " + data);
    ChangeToModify();
  };
  // 바뀔때마다 setData로 수정된 데이터로 바꿔줌
  const handleChange = (e) => {
    e.preventDefault();
    // console.log("handleChange!");
    data[e.target.name] = e.target.value;
    // console.log(e.target.name + " : " + e.target.value);
  };

  const Main = () => {
    return (
      <>
        <div>
          <div className='InfoTextField'>
            <TextField
              id='outlined-read-only-input'
              label='사번'
              defaultValue={dummy_data.id}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='사원명'
              defaultValue={dummy_data.name}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='사원 Email'
              defaultValue={dummy_data.email}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='PW'
              defaultValue={dummy_data.pw}
              fullWidth={true}
              InputProps={{ readOnly: true }}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='제품군'
              defaultValue={dummy_data.product}
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
              defaultValue={dummy_data.id}
              fullWidth={true}
              InputProps={{ readOnly: true }}
              disabled='true'
              variant='filled'
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='사원명'
              defaultValue={dummy_data.name}
              fullWidth={true}
              InputProps={{ readOnly: true }}
              disabled='true'
              variant='filled'
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='사원 Email'
              defaultValue={dummy_data.email}
              fullWidth={true}
              onChange={handleChange}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='PW'
              defaultValue={dummy_data.pw}
              fullWidth={true}
              onChange={handleChange}
            />
          </div>
          <div className='InfoTextField'>
            <TextField
              label='제품군'
              defaultValue={dummy_data.product}
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
            <img
              src={`${process.env.PUBLIC_URL}/img/ManagerImage.png`}
              alt='NO IMAGE'
            />
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
                <TableBody>
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
                </TableBody>
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
