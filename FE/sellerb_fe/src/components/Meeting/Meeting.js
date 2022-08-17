import axios from "axios";

import React, { Component } from "react";

import { getManagerInfoApi } from "../../api/managerApi";
import { detailConsultantApi } from "../../api/consultantApi";

import VideoRoomComponent from "../VideoRoomComponent";

import "./Meeting.css";

class Meeting extends Component {
  constructor(props) {
    super(props);
    this.state = {
      mySessionId: undefined,
      myUserName: undefined,
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
      this.setState({
        session: true,
      });

      event.preventDefault();
    }
  }

  leaveSession() {
    const mySession = this.state.session;

    if (mySession) {
      mySession.disconnect();
    }

    this.setState({
      session: undefined,
    });
  }

  render() {
    const mySessionId = this.state.mySessionId;
    const myUserName = this.state.myUserName;

    return (
      <>
        <div id='meeting-container'>
          {this.state.session === undefined ? (
            <div id='joinFormWrapper'>
              <div className='joinning-session-wrapper'>
                <h1> 화상 회의 </h1>
                <form className='form-group' onSubmit={this.joinSession}>
                  <p className='text-center'>
                    {sessionStorage.getItem("adminCheck") === "ROLE_ADMIN" ? (
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
}

export default Meeting;
