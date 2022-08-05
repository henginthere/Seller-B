import openviduLayout from "openvidu-react/dist/layout/openvidu-layout";
import React, { Component } from "react";
import { NavBar, Footer } from "../../../components/index";
import { OpenVidu } from "react";
import UserVideoComponent from "../../../components/Meeting/UserVideoComponent";

const flexDisplay = {
  display: "flex",
  justifyContent: "center",
  alignItem: "center",
  flexDirection: "column",
};
const container = {
  textAlign: "center",
  justifyContent: "center",
  alignItem: "center",
  flexDirection: "column",
  backgroundColor: "lightGray",
};
const TopText = {
  display: "flex",
  justifyContent: "center",
  alignItem: "center",
};

const OPENVIDU_SERVER_URL = "https://i7d105.p.ssafy.io:8443/";
const OPENVIDU_SERVER_SECRET = "SELLERB";

class MeetingManCon extends Component {
  constructor(props) {
    super(props);

    this.state = {
      mySessionId: "SessionA",
      myUserName: "Test" + Math.floor(Math.random() * 100),
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
    };
    this.joinSession = this.joinSession.bind(this);
  }
  componentDidMount() {
    window.addEventListener("beforeunload", this.onbeforeunload);
  }

  componentWillUnmount() {
    window.removeEventListener("beforeunload", this.onbeforeunload);
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
        mainStreamManger: stream,
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
  joinSession() {
    this.OV = new OpenVidu();
    this.setState(
      {
        session: this.OV.initSession(),
      },
      () => {
        var mySession = this.state.session;
        mySession.on("streamCreated", (e) => {
          var subscriber = mySession.subscribe(e.stream, undefined);
          var subscribers = this.state.subscribers;
          subscribers.push(subscriber);

          this.setState({
            subscribers: subscribers,
          });
        });
        mySession.on("streamDestroyed", (e) => {
          this.deleteSubscriber(e.stream.streamManager);
        });
        this.getToken()
          .then((token) => {
            mySession
              .connect(token, { clientData: this.state.myUserName })
              .then(async () => {
                var devices = await this.OV.getDevices();
                var videoDevices = devices.filter(
                  (device) => device.kind === "videoinput",
                );
                let publisher = this.OV.initPublisher(undefined, {
                  audioSource: undefined, // The source of audio. If undefined default microphone
                  videoSource: videoDevices[0].deviceId, // The source of video. If undefined default webcam
                  publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                  publishVideo: true, // Whether you want to start publishing with your video enabled or not
                  resolution: "640x480", // The resolution of your video
                  frameRate: 30, // The frame rate of your video
                  insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                  mirror: false, // Whether to mirror your local video or not
                });

                mySession.publish(publisher);
                this.setState({
                  currendVideoDevice: videoDevices[0],
                  mainStreamManager: publisher,
                  publisher: publisher,
                });
              });
          })
          .catch((error) => {
            console.log(error);
          });
      },
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
      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
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
        <div style={flexDisplay}>
          <div style={TopText}>
            <h1>Meeting</h1>
          </div>
          <div style={container}>
            {this.state.session === undefined ? (
              <div>
                <form onSubmit={this.joinSession}>
                  <label>참가자 이름 : </label>
                  <input
                    type='text'
                    id='userName'
                    value={myUserName}
                    onChange={this.handleChangeUserName}
                    required
                  />
                  <label>세션 : </label>
                  <input
                    type='text'
                    id='sessionId'
                    value={mySessionId}
                    onChange={this.handleChangeSessionId}
                    required
                  />
                  <button type='submit'> JOIN SESSION</button>
                </form>
              </div>
            ) : null}
            {this.state.session !== undefined ? (
              <div>
                <UserVideoComponent
                  streamManager={this.state.mainStreamManager}
                />
              </div>
            ) : null}
            <div>
              {this.state.publisher !== undefined ? (
                <div>
                  <UserVideoComponent streamManager={this.state.publisher} />
                </div>
              ) : null}
            </div>
          </div>
        </div>
        <Footer />
      </>
    );
  }
}
export default MeetingManCon;
