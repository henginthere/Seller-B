import React, { useState, Component } from "react";
import axios from "axios";
import { NavBar, Footer } from "../../../components/index";
import "../Meeting.css";
import OpvSession from "openvidu-react";
import UserVideoComponent from "../../../components/Meeting/UserVideoComponent";
import { OpenVidu } from "openvidu-browser";
import { TextField } from "@mui/material";
import Button from "@mui/material/Button";
const OPENVIDU_SERVER_URL = "https://i7d105.p.ssafy.io:8443/";
const OPENVIDU_SERVER_SECRET = "SELLERB";
const dummy_data = {
  sessionId: "Manager",
  userName: "Consultant1",
  session: undefined,
  mainStreammanager: undefined,
};

class MeetingManCon extends Component {
  constructor(props) {
    super(props);
    this.state = {
      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
    };
  }
  componentDidMount() {
    window.addEventListener("beforeunload", this.onbeforeunload);
  }

  componentWillUnmount() {
    window.removeEventListener("beforeunload", this.onbeforeunload);
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
  joinSession() {
    this.OV = new OpenVidu();

    this.setState(
      {
        session: this.OV.initSession(),
      },
      () => {
        var mySession = this.state.session;

        mySession.on("streamCreated", (event) => {
          var subscriber = mySession.subscribe(event.stream, undefined);
          var subscribers = this.state.subscirbers;
          subscribers.push(subscriber);

          this.setState({
            subscribers: subscribers,
          });
        });
        mySession.on("streamDestroyed", (event) => {
          this.deleteSubscriber(event.stream.streamManager);
        });

        mySession.on("exception", (exception) => {
          console.log(exception);
        });

        this.getToken().then((token) => {
          mySession
            .connect(token, { clinetData: this.state.myUserName })
            .then(async () => {
              var devices = await this.OV.getDevices();
              var videoDevices = devices.filter(
                (device) => device.kind === "videoinput",
              );

              let publisher = this.OV.initPublisher(undefined, {
                audioSource: undefined,
                videoSource: videoDevices[0].deviceId,
                publicshAudio: true,
                publishVideo: true,
                resolution: "640x480",
                frameRate: 30,
                insertMode: "APPEND",
                mirror: true,
              });
              mySession.publish(publisher);

              this.setState({
                currentVideoDevice: videoDevices[0],
                mainStreamManager: publisher,
                publisher: publisher,
              });
            })
            .catch((error) => {
              console.log("ERROR!", error.code, error.message);
            });
        });
      },
    );
  }
  leaveSession() {
    const mySession = this.state.session;
    if (mySession) {
      mySession.disconnect();
    }
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
  isManager() {
    if (this.state.userName === "manager") {
      return (
        <div className='managerVideo'>
          <h2>
            manager
            <UserVideoComponent streamManager={this.state.mainStreamManager} />
          </h2>
        </div>
      );
    } else {
      return (
        <div className='consultantVideo'>
          <h2>
            consultant
            {this.state.subscribers.map((sub, i) => (
              <div
                key={i}
                className='stream-container col-md-6 col-xs-6'
                onClick={() => this.handleMainVideoStream(sub)}
              >
                <UserVideoComponent streamManager={sub} />
              </div>
            ))}
          </h2>
        </div>
      );
    }
  }
  render() {
    return (
      <>
        <NavBar />
        <div id='wrapper'>
          <div className='TopText'>
            <h1>Meeting Room</h1>
          </div>
          {this.state.session === undefined ? (
            <div className='joinningSession'>
              <form onSubmit={this.joinSession}>
                <div className='innerInput'>
                  <TextField
                    label='userName'
                    name='userName'
                    defaultValue={this.state.myUserName}
                  />
                </div>
                <div className='innerInput'>
                  <TextField
                    label='SessionID'
                    name='sessionId'
                    defaultValue={this.state.mySessionId}
                  />
                </div>
                <div className='Button'>
                  <Button variant='contained' size='large' type='submit'>
                    JoinSession
                  </Button>
                </div>
              </form>
            </div>
          ) : null}
          {this.state.session !== undefined ? (
            <div>
              <div className='managerVideo'>
                <h1>ManagerVideo</h1>
              </div>
              <div className='consultantVideo'></div>
            </div>
          ) : null}
        </div>

        <Footer />
      </>
    );
  }
  getToken() {
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
              "Basic" + btoa("OPENVIDUAPP: " + OPENVIDU_SERVER_SECRET),
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          console.log("CREATE SESSION", response);
          resolve(response.data.id);
        })
        .catch((response) => {
          var error = Object.assign({}, response);
          alert(error);
        });
    });
  }
  createToken(sessionId) {
    return new Promise((resolve, reject) => {
      var data = {};
      axios
        .post(
          OPENVIDU_SERVER_URL +
            "/openvidu/api/sessions" +
            sessionId +
            "/connection",
          data,
          {
            headers: {
              Authorization:
                " Basic" + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
              "Content-Type": "application/json",
            },
          },
        )
        .then((response) => {
          console.log("TOKEN", response);
          resolve(response.data.token);
        })
        .catch((error) => reject(error));
    });
  }
}
export default MeetingManCon;
