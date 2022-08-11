import React, { useEffect, useState } from "react";
import { NavBar, Footer } from "../../../components/index";
import { waitingCostomerListApi } from "../../../api/consultingApi";
import { detailConsultantApi } from "../../../api/consultantApi";
import {
  List,
  ListItem,
  ListItemText,
  ListItemIcon,
  Button,
  Skeleton,
} from "@mui/material";
import VideoRoomComponent from "../../../components/VideoRoomComponent";

function ConsultingMain() {
  const style_Container = {
    width: "100%",
    height: "100%",
    minHeight: "500px",
  };
  const style_topText = {
    display: "flex",
    justifyContent: "flex-start",
    alignItem: "flex-start",
    marginTop: "30px",
    marginLeft: "30px",
  };
  const style_list = {
    padding: "50px",
  };
  const [waitingList, setWaitingList] = useState([]);
  const [session, setSession] = useState();
  const [sessionUserName, setSessionUserName] = useState(
    sessionStorage.getItem("consultantName"),
  );
  const [sessionId, setSessionId] = useState("");

  const productGroupSeq = sessionStorage.getItem("productGroupSeq");
  useEffect(() => {
    // console.log("컴포넌트 화면 나타남!!!");
    if (session === undefined) {
      setTimeout(
        () =>
          waitingCostomerListApi(productGroupSeq)
            .then((res) => {
              setWaitingList(res.data);
              console.log("API CALL");
            })
            .catch((err) => {
              console.log(err);
            }),
        2000,
      );
    }
  });

  const startSession = () => {
    console.log("sessionUserName : " + sessionUserName);
    console.log("sessionId : " + sessionId);
    setSession(true);
  };
  const endConsulting = () => {
    setSession(undefined);
    console.log("!!!!endConsulting CALL!!!");
    console.log("session");
  };

  return (
    <>
      <NavBar />
      <div style={style_Container}>
        <h1 style={style_topText}>상담</h1>
        {session === undefined ? (
          <div style={style_list}>
            <h2>상담 목록</h2>
            <div>
              <List sx={{ width: "90%" }}>
                {waitingList.map((values, i) => (
                  <ListItem alignItems='flex-start'>
                    <ListItemText
                      primary={values.productName}
                      secondary={
                        <React.Fragment>
                          <h4>{values.customerName}</h4>
                          <h4>{values.productId}</h4>
                        </React.Fragment>
                      }
                    ></ListItemText>
                    <Button
                      onClick={() => {
                        setSessionId(values.customerId + "-session");
                        startSession();
                      }}
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
          <VideoRoomComponent
            user={sessionUserName}
            sessionName={sessionId}
            leaveSession={endConsulting}
          />
        )}
      </div>
      <Footer />
    </>
  );
}

export default ConsultingMain;
