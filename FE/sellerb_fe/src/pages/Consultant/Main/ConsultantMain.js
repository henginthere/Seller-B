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

function ConsultantMain() {
  const navigate = useNavigate();
  const [conSeq, setConSeq] = useState(""); // 상담사 seq
  const [conName, setConName] = useState(""); // 상담사 이름

  // const [today, setToday] = useState(new Date().getFullYear() + "-" + (new Date().getMonth()+1) + "-" + new Date().getDate());
  const [today, setToday] = useState(new Date());
  const [items, setItems] = useState([]);
  const [noticeList, setNoticeList] = useState([]); //

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
    console.log("Today : " + today);
    listNoticeApi()
      .then((res) => {
        // console.log(res.data);
        setItems(res.data);

        // console.log(items);

        const items = res.data.filter(
          (it) => it.noticeRegDate.getDate() === today.getDate(),
        );
        console.log("오늘 새로운 공지사항 갯수:");
        console.log(items);
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
    leaveWorkApi(conSeq)
      .then((res) => {
        console.log("Success");

        alert("퇴근완료!");
      })
      .catch((err) => {
        console.log("Error");
      });
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
            <div className='notice-title'>공지사항</div>
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
                              {value.noticeRegDate}
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
