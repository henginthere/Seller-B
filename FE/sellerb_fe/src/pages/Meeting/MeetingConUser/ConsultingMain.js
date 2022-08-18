import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { NavBar, Footer } from "../../../components/index";
import {
  waitingCostomerListApi,
  startConsultingApi,
  endConsultingApi,
} from "../../../api/consultingApi";
import axios from "axios";
import { productDetailApi } from "../../../api/productApi";

import { List, ListItem, ListItemText, Button, Skeleton } from "@mui/material";
import ConsultingVideoRoomComponent from "../../../components/ConsultingVideoRoomComponent";

function ConsultingMain() {
  const style_Container = {
    width: "100%",
    height: "100%",
    minHeight: "500px",
    padding: "30px",
    border: "1px solid black",
    borderRadius: "10px",
  };
  const style_topText = {
    display: "flex",
    justifyContent: "flex-start",
    alignItem: "flex-start",
    marginTop: "30px",
    marginLeft: "30px",
  };
  const style_list = {
    padding: "100px",
  };
  const [waitingList, setWaitingList] = useState([]);
  const [session, setSession] = useState();
  const sessionUserName = sessionStorage.getItem("consultantName");
  const [sessionId, setSessionId] = useState("");
  const [consultingSeq, setConsultingSeq] = useState();
  const [product, setProduct] = useState([]);
  const [productSeq, setProductSeq] = useState();
  const navigate = useNavigate();

  const productGroupSeq = sessionStorage.getItem("productGroupSeq");
  useEffect(() => {
    if (sessionStorage.getItem("accessToken") === null) {
      alert("접근 권한이 없습니다.");
      navigate("/");
    }
    if (session === undefined) {
      setTimeout(
        () =>
          waitingCostomerListApi(productGroupSeq)
            .then((res) => {
              setWaitingList(res.data);
            })
            .catch((err) => {
              console.log(err);
            }),
        2000,
      );
    }
  });

  const startSession = () => {
    setSession(true);
  };
  const endConsulting = async () => {
    setSession(undefined);
    console.log("ENDING consultingSeq... : " + consultingSeq);
    axios
      .put(
        `https://i7d105.p.ssafy.io/api/consulting/state/${consultingSeq}`,
        {
          consultingState: "end",
        },
        {
          header: {
            "Content-Type": `application/json`,
          },
        },
      )
      .then((res) => {
        console.log("상담 정보 입력 성공!");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <>
      <NavBar />
      <div className='consultant-main-image-wrapper'>
        <img
          src={`${process.env.PUBLIC_URL}/img/consultantMainPageImage.jpg`}
        />
      </div>
      <div style={style_Container}>
        <h1 style={style_topText}>상담</h1>
        <hr />
        {/* 상담중이 아닐 경우 */}
        {session === undefined ? (
          <div style={style_list}>
            <h2>상담 목록</h2>
            <div>
              <List>
                {waitingList.map((values, i) => (
                  <ListItem alignItems='flex-start'>
                    <ListItemText
                      primary={values.waitingCustomerSeq}
                      secondary={
                        <React.Fragment>
                          <h3>{values.productName}</h3>
                          <h4>{values.customerName}</h4>
                          <h4>{values.productId}</h4>
                        </React.Fragment>
                      }
                    ></ListItemText>
                    <Button
                      onClick={async () => {
                        await productDetailApi(values.productSeq)
                          .then((res) => {
                            setProduct({ ...res.data }, function () {
                              alert("제품명 : " + product.productName);
                              console.log("!!!ON CLICKsetProduct CALL!!!");
                            });
                          })
                          .catch((err) => {
                            alert("제품정보 로드 실패! in onClick : " + err);
                          });

                        setSessionId(values.customerId + "-session");
                        startConsultingApi({
                          customerId: values.customerId,
                          productSeq: values.productSeq,
                          consultantSeq: sessionStorage.getItem("seq"),
                          consultingState: "waiting",
                        })
                          .then((res) => {
                            setConsultingSeq(res.data.consultingSeq);
                          })
                          .catch((err) => {
                            console.log("상담 정보 입력 실패!   " + err);
                          });
                        alert("상담을 시작합니다.");
                        startSession();
                      }}
                      variant='contained'
                    >
                      상담 시작
                    </Button>
                  </ListItem>
                ))}
                <Skeleton variant='rounded' height={50} />
              </List>
            </div>
          </div>
        ) : (
          // 상담중일 경우
          <ConsultingVideoRoomComponent
            user={sessionUserName}
            sessionName={sessionId}
            leaveSession={endConsulting}
            product={product}
          />
        )}
      </div>
      <Footer />
    </>
  );
}

export default ConsultingMain;
