import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  TableContainer,
} from "@mui/material";

import { Footer, NavBar } from "../../../components/index";
import "./ConsultantMain.css";
import {
  goWorkApi,
  leaveWorkApi,
  detailConsultantApi,
} from "../../../api/consultantApi";
import { listNoticeApi } from "../../../api/noticeApi";
import axios from "axios";

function ConsultantMain() {
  const navigate = useNavigate();
  const [conSeq, setConSeq] = useState(""); // 상담사 seq
  const [conName, setConName] = useState(""); // 상담사 이름


  const [today, setToday] = useState(new Date());
  const [items, setItems] = useState([]);
  const [noticeList, setNoticeList] = useState([]);
  var date = new Date();
  useEffect(() => {
    const val = sessionStorage.getItem("seq");
    setConSeq(val);

    detailConsultantApi(val)
      .then((res) => {
        setConName(res.data.consultantName);
        sessionStorage.setItem("productGroupSeq", res.data.productGroupSeq);
        sessionStorage.setItem("consultantName", res.data.consultantName);
      })
      .catch((err) => {
        console.log("Error");
      });
  }, []);

  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
    listNoticeApi()
      .then((res) => {
        setItems(res.data.reverse());

        console.log(res.data[0].noticeRegDate);
        var newDate = new Date(res.data[0].noticeRegDate);

        console.log(
          "TIMESTAMP 변환 DATA : " +
            newDate.toLocaleString("ko-KR", { timeZone: "Asia/Seoul" }),
        );
      })
      .catch((err) => {});
  }, []);

  const goWorkBtn = () => {
    const Info = {
      consultantSeq: conSeq,
    };

    goWorkApi(Info)
      .then((res) => {
        console.log("success");
        alert("출근완료!");
      })
      .catch((err) => {
        console.log("Error");
      });
  };

  const leaveWorkBtn = () => {
    axios
      .put(`https://i7d105.p.ssafy.io/api/consultant-attendance/${conSeq}`)
      .then((res) => {
        alert("퇴근완료!");
      })
      .catch((err) => {
        alert("퇴근오류");
      });
  };
  const parsingDate = (date) => {
    var parsedDate = new Date(date);
    var yyyy = parsedDate.getFullYear();
    var MM = parsedDate.getMonth() + 1;
    var dd = parsedDate.getDate();
    var hh = parsedDate.getHours() + 9;
    var mm = parsedDate.getMinutes();
    if (hh > 24) {
      hh = hh - 24;
      dd = dd + 1;
    }
    return (
      yyyy +
      "-" +
      addZero(MM) +
      "-" +
      addZero(dd) +
      "-" +
      addZero(hh) +
      ":" +
      addZero(mm)
    );
  };
  const addZero = (n) => {
    return n < 10 ? "0" + n : n;
  };

  return (
    <>
      <NavBar></NavBar>
      <div className='consultant-main-image-wrapper'>
        <img
          src={`${process.env.PUBLIC_URL}/img/consultantMainPageImage.jpg`}
        />
      </div>
      <hr className='hr-in-consultant-main' />
      <div className='consultant-main-wrapper'>
        <div className='main-header'>
          <div className='header-left'>
            <div className='header-topText'>
              <h1> {conName} 님, 환영합니다!</h1>
            </div>

            <div className='attend-wrapper'>
              <div className='go-btn-wrapper' onClick={goWorkBtn}>
                <div className='go-btn'>출근</div>
              </div>
              <div className='leave-btn-wrapper' onClick={leaveWorkBtn}>
                <div className='leave-btn'>퇴근</div>
              </div>
            </div>
          </div>
        </div>
        <div className='notice-consulting-wrapper'>
          <div className='notice'>
            <div
              className='notice-title'
              onClick={() => {
                navigate("/manager/noticeList");
              }}
            >
              공지사항
            </div>
            <hr />
            <div>
              <TableContainer>
                <Table>
                  <TableHead>
                    <TableRow>
                      <TableCell>No</TableCell>
                      <TableCell>제목</TableCell>
                      <TableCell>Date</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {items.slice(-6, -1).map((value) => {
                      return (
                        <>
                          <TableRow>
                            <TableCell
                              onClick={() => {
                                navigate(`/noticeDetail/${value.noticeSeq}`);
                              }}
                            >
                              {value.noticeSeq}
                            </TableCell>
                            <TableCell
                              onClick={() => {
                                navigate(`/noticeDetail/${value.noticeSeq}`);
                              }}
                            >
                              {value.noticeTitle}
                            </TableCell>
                            <TableCell
                              onClick={() => {
                                navigate(`/noticeDetail/${value.noticeSeq}`);
                              }}
                            >
                              {parsingDate(value.noticeRegDate)}
                            </TableCell>
                          </TableRow>
                        </>
                      );
                    })}
                  </TableBody>
                </Table>
              </TableContainer>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default ConsultantMain;
