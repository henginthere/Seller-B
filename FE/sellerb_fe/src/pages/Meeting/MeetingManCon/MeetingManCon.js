import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import React, { Component } from "react";

import { api } from "../../../api/api";
import { getManagerInfoApi } from "../../../api/managerApi";
import { detailConsultantApi } from "../../../api/consultantApi";

import OpenViduVideoComponent from "../../../components/Meeting/OvVideo";
import UserVideoComponent from "../../../components/Meeting/UserVideoComponent";

import { NavBar, Footer } from "../../../components/index";
import "../Meeting.css";

const OPENVIDU_SERVER_URL = "https://i7d105.p.ssafy.io:8443";
const OPENVIDU_SERVER_SECRET = "SELLERB";

class MeetingManCon extends Component {
  constructor(props) {
    super(props);
    this.state = {
      mySessionId: "SessionA",
      myUserName: "Manager or Consultant+num",
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
    };

    this.joinSession = this.joinSession.bind(this);
    this.leaveSession = this.leaveSession.bind(this);

    this.handleChangeSessionId = this.handleChangeSessionId.bind(this);
    this.handleChangeUserName = this.handleChangeUserName.bind(this);
    this.handleMainVideoStream = this.handleMainVideoStream.bind(this);
    this.onbeforeunload = this.onbeforeunload.bind(this);
  }

  componentDidMount() {
    if (sessionStorage.getItem("adminCheck") === "ROLE_ADMIN") {
      // 관리자 일 경우
      getManagerInfoApi(sessionStorage.getItem("seq"))
        .then((res) => {
          this.setState({
            myUserName: res.data.managerName,
            mySessionId: res.data.brand.brandNameEng + "-session",
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
            mySessionId: res.data.brandName + "-session",
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

  handleChangeSessionId(e) {
    this.setState({
      mySessionId: e.target.value,
    });
  }

  handleChangeUserName(e) {
    this.setState({
      myUserName: e.target.value,
    });
  }

  handleMainVideoStream(stream) {
    if (this.state.mainStreamManager !== stream) {
      this.setState({
        mainStreamManager: stream,
      });
    }
  }

  deleteSubscriber(streamManager) {
    let subscribers = this.state.subscribers;
    let index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      subscribers.splice(index, 1);
      this.setState({
        subscribers: subscribers,
      });
    }
  }
  // 참여할 세션 정보 받아오기 위한 함수
  // 세션이름을 (brand영문이름) + Session 이런식으로 만들어서
  // 같은 브랜드 관리자와 상담사 전체 회의 관리

  joinSession() {
    this.OV = new OpenVidu();

    this.setState(
      {
        session: this.OV.initSession(),
      },
      () => {
        var mySession = this.state.session;

        mySession.on("streamCreated", (event) => {
          // Subscribe to the Stream to receive it. Second parameter is undefined
          // so OpenVidu doesn't create an HTML video by its own
          var subscriber = mySession.subscribe(event.stream, undefined);
          var subscribers = this.state.subscribers;
          subscribers.push(subscriber);

          this.setState({
            subscribers: subscribers,
          });
        });

        mySession.on("streamDestroyed", (event) => {
          this.deleteSubscriber(event.stream.streamManager);
        });

        mySession.on("exception", (exception) => {
          console.warn(exception);
        });

        this.getToken().then((token) => {
          mySession
            .connect(token, { clientData: this.state.myUserName })
            .then(async () => {
              var devices = await this.OV.getDevices();
              var videoDevices = devices.filter((device) => device.kind === "videoinput");

              let publisher = this.OV.initPublisher(undefined, {
                audioSource: undefined,
                videoSource: undefined,
                publishAudio: true,
                publishVideo: true,
                resolution: "640x480",
                frameRate: 30,
                insertMode: "APPEND",
                mirror: false,
              });

              mySession.publish(publisher);

              // Set the main video in the page to display our webcam and store our Publisher
              this.setState({
                currentVideoDevice: videoDevices[0],
                mainStreamManager: publisher,
                publisher: publisher,
              });
            })
            .catch((error) => {
              console.log(
                "There was an error connecting to the session:",
                error.code,
                error.message
              );
            });
        });
      }
    );
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
      subscribers: [],
      mainStreamManager: undefined,
      publisher: undefined,
    });
  }

  render() {
    const mySessionId = this.state.mySessionId;
    const myUserName = this.state.myUserName;

    return (
      <>
        <NavBar />

        <div id='wrapper'>
          <div id='manager-video'>
            {this.state.mainStreamManager !== undefined ? (
              <div id='main-video'>
                <h2>Manager 화면</h2>
                <h1 id='session-title'>{mySessionId}</h1>
                <UserVideoComponent streamManager={this.state.mainStreamManager} />
              </div>
            ) : null}
          </div>
          <div id='consultant-video'>
            {this.state.mainStreamManager !== undefined ? (
              <div id='sub-video-wrapper'>
                {this.state.subscribers.map((sub, i) => (
                  <div id='sub-video' key={i}>
                    <UserVideoComponent streamManager={sub} />
                  </div>
                ))}
              </div>
            ) : null}
          </div>
        </div>
        <div id='meeting-container'>
          {this.state.session === undefined ? (
            <div id='joinFormWrapper'>
              <div className='joinning-session-wrapper'>
                <h1> 화상 회의 </h1>
                <form className='form-group' onSubmit={this.joinSession}>
                  {/* <p>
                    <label> 참가자 이름 </label>
                    <input
                      className='form-control'
                      type='text'
                      id='userName'
                      value={myUserName}
                      readOnly={true}
                      onChange={this.handleChangeUserName}
                      required
                    />
                  </p>
                  <p>
                    <label> 참가 세션 정보 </label>
                    <input
                      className='form-control'
                      type='text'
                      id='sessionId'
                      readOnly={true}
                      value={mySessionId}
                      onChange={this.handleChangeSessionId}
                      required
                    />
                  </p> */}
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
          ) : null}

          {this.state.session !== undefined ? (
            <div id='session'>
              <div id='session-header'>
                <input
                  className='btn btn-large btn-danger'
                  type='button'
                  id='buttonLeaveSession'
                  onClick={this.leaveSession}
                  value='세션 떠나기'
                />
              </div>

              {/* {this.state.publisher !== undefined ? (
                  <div
                    className='stream-container col-md-6 col-xs-6'
                    onClick={() =>
                      this.handleMainVideoStream(this.state.publisher)
                    }
                  >
                    <UserVideoComponent streamManager={this.state.publisher} />
                  </div>
                ) : null} */}
            </div>
          ) : null}
        </div>
        <Footer />
      </>
    );
  }

  /**
   * --------------------------
   * SERVER-SIDE RESPONSIBILITY
   * --------------------------
   * These methods retrieve the mandatory user token from OpenVidu Server.
   * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
   * the API REST, openvidu-java-client or openvidu-node-client):
   *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
   *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
   *   3) The Connection.token must be consumed in Session.connect() method
   */

  getToken() {
    console.log("this.state.mySessionId in getToken" + this.state.mySessionId);
    return this.createSession(this.state.mySessionId).then((sessionId) =>
      this.createToken(sessionId)
    );
  }

  createSession(sessionId) {
    return new Promise((resolve, reject) => {
      var data = JSON.stringify({ customSessionId: sessionId });
      axios
        .post(OPENVIDU_SERVER_URL + "/openvidu/api/sessions", data, {
          headers: {
            Authorization: "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
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
                OPENVIDU_SERVER_URL
            );
            if (
              window.confirm(
                'No connection to OpenVidu Server. This may be a certificate error at "' +
                  OPENVIDU_SERVER_URL +
                  '"\n\nClick OK to navigate and accept it. ' +
                  'If no certificate warning is shown, then check that your OpenVidu Server is up and running at "' +
                  OPENVIDU_SERVER_URL +
                  '"'
              )
            ) {
              window.location.assign(OPENVIDU_SERVER_URL + "/accept-certificate");
            }
          }
        });
    });
  }

  createToken(sessionId) {
    return new Promise((resolve, reject) => {
      var data = {};
      axios
        .post(OPENVIDU_SERVER_URL + "/openvidu/api/sessions/" + sessionId + "/connection", data, {
          headers: {
            Authorization: "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
            "Content-Type": "application/json",
          },
        })
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

export default MeetingManCon;
