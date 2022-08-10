import axios from "axios";

import React, { Component } from "react";

import { getManagerInfoApi } from "../../api/managerApi";
import { detailConsultantApi } from "../../api/consultantApi";

import OpenViduSession from "openvidu-react";

import VideoRoomComponent from "../VideoRoomComponent";
// import VideoRoomComponent from "../VideoRoomComponent";

import "./Meeting.css";

const OPENVIDU_SERVER_URL = "https://i7d105.p.ssafy.io:8443";
const OPENVIDU_SERVER_SECRET = "SELLERB";

class Meeting extends Component {
  constructor(props) {
    super(props);
    this.state = {
      mySessionId: "SessionA",
      myUserName: "OpenVidu_User_" + Math.floor(Math.random() * 100),
      token: undefined,
    };

    this.joinSession = this.joinSession.bind(this);
    this.leaveSession = this.leaveSession.bind(this);

    this.onbeforeunload = this.onbeforeunload.bind(this);

    // this.handlerJoinSessionEvent = this.handlerJoinSessionEvent.bind(this);
    this.handlerLeaveSessionEvent = this.handlerLeaveSessionEvent.bind(this);
  }

  componentDidMount() {
    if (sessionStorage.getItem("adminCheck") === "ROLE_ADMIN") {
      // 관리자 일 경우
      getManagerInfoApi(sessionStorage.getItem("seq"))
        .then((res) => {
          this.setState({
            myUserName: res.data.managerName + "-Manager",
            mySessionId: res.data.brand.brandNameEng,
          });
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      // 상담사 일 경우
      detailConsultantApi(sessionStorage.getItem("seq"))
        .then((res) => {
          this.setState({
            myUserName: res.data.consultantName,
            mySessionId: res.data.brandName,
          });
        })
        .catch((err) => {
          console.log(err);
        });
    }
    window.addEventListener("beforeunload", this.onbeforeunload);
  }

  componentWillUnmount() {
    window.removeEventListener("beforeunload", this.onbeforeunload);
    this.leaveSession();
  }

  onbeforeunload(event) {
    this.leaveSession();
  }

  handlerLeaveSessionEvent() {
    // console.log("Leave session");
    this.setState({
      session: undefined,
    });
    this.leaveSession();
  }

  // 참여할 세션 정보 받아오기 위한 함수
  // 세션이름을 (brand영문이름) + Session 이런식으로 만들어서
  // 같은 브랜드 관리자와 상담사 전체 회의 관리

  joinSession(event) {
    if (this.state.mySessionId && this.state.myUserName) {
      this.getToken().then((token) => {
        this.setState({
          token: token,
          session: true,
        });
      });
      event.preventDefault();
    }
  }

  leaveSession() {
    const mySession = this.state.session;

    if (mySession) {
      mySession.disconnect();
    }

    // Empty all properties...
    this.OV = null;
    this.setState({
      session: undefined,
    });
  }

  render() {
    const mySessionId = this.state.mySessionId;
    const myUserName = this.state.myUserName;
    const token = this.state.token;
    return (
      <>
        <div id='meeting-container'>
          {this.state.session === undefined ? (
            <div id='joinFormWrapper'>
              <div className='joinning-session-wrapper'>
                <h1> 화상 회의 </h1>
                <form className='form-group' onSubmit={this.joinSession}>
                  <p className='text-center'>
                    {sessionStorage.getItem("adminCheck") === "ROL" ? (
                      <input
                        className='btn btn-lg btn-success'
                        name='commit'
                        type='submit'
                        value='회의 시작하기'
                      />
                    ) : (
                      <input
                        className='btn btn-lg btn-success'
                        name='commit'
                        type='submit'
                        value='회의 참가하기'
                      />
                    )}
                  </p>
                </form>
              </div>
            </div>
          ) : (
            <div id='meeting-container'>
              {/* <OpenViduSession
                sessionName={mySessionId}
                user={myUserName}
                token={token}
                joinSession={this.handlerJoinSessionEvent}
                leaveSession={this.handlerLeaveSessionEvent}
                error={this.handlerErrorEvent}
              /> */}
              <VideoRoomComponent
                sessionName={mySessionId}
                user={myUserName}
                leaveSession={this.handlerLeaveSessionEvent}
                token={this.state.token}
              />
            </div>
          )}
        </div>
      </>
    );
  }

  getToken() {
    console.log("this.state.mySessionId in getToken" + this.state.mySessionId);
    return this.createSession(this.state.mySessionId).then((sessionId) =>
      this.createToken(sessionId),
    );
  }

  createSession(sessionId) {
    return new Promise((resolve, reject) => {
      var data = JSON.stringify({ customSessionId: sessionId });
      axios
        .post(OPENVIDU_SERVER_URL + "/openvidu/api/sessions", data, {
          headers: {
            Authorization:
              "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          console.log("CREATE SESION", response);
          resolve(response.data.id);
        })
        .catch((response) => {
          var error = Object.assign({}, response);
          if (error?.response?.status === 409) {
            resolve(sessionId);
          } else {
            console.log(error);
            console.warn(
              "No connection to OpenVidu Server. This may be a certificate error at " +
                OPENVIDU_SERVER_URL,
            );
            if (
              window.confirm(
                'No connection to OpenVidu Server. This may be a certificate error at "' +
                  OPENVIDU_SERVER_URL +
                  '"\n\nClick OK to navigate and accept it. ' +
                  'If no certificate warning is shown, then check that your OpenVidu Server is up and running at "' +
                  OPENVIDU_SERVER_URL +
                  '"',
              )
            ) {
              window.location.assign(
                OPENVIDU_SERVER_URL + "/accept-certificate",
              );
            }
          }
        });
    });
  }

  createToken(sessionId) {
    return new Promise((resolve, reject) => {
      var data = {};
      axios
        .post(
          OPENVIDU_SERVER_URL +
            "/openvidu/api/sessions/" +
            sessionId +
            "/connection",
          data,
          {
            headers: {
              Authorization:
                "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
              "Content-Type": "application/json",
            },
          },
        )
        .then((response) => {
          console.log("TOKEN", response);
          resolve(response.data.token);
        })
        .catch((error) => {
          alert("Session 생성 오류");
          reject(error);
        });
    });
  }
}

export default Meeting;
