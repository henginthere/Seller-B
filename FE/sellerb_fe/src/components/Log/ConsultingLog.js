import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import Axios from "axios";



const styleObj_center = {
  textAlign: "center",
  margin: "50px",
};

const dummyData = [
  {
    date: "2022-07-25",
    login_time: "08:30",
    logout_time: "18:10",
  },
  {
    date: "2022-07-26",
    login_time: "08:35",
    logout_time: "18:21",
  },
];

function ConsultingLog({consultant_id}) {
  console.log(consultant_id)
  const params = useParams();
  const [data, setData] = useState("");

  // axios : 상담사 출결이력 표시
  // useEffect(() => {
  //   listConsultingApi({consultant_id})
  //     .then((res) => {
  //       console.log(res.data);

  //       // attendance = res.data;
  //       setData(res.data);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // }, []);

  return (
    <>
      <div style={styleObj_center}>
        <table className="table-wrapper">
          <thead className="table-header-wrapper">
            <tr>
              <th>{consultant_id}상담날짜</th>
              <th>상담 시작시각 </th>
              <th>상담 종료시각</th>
            </tr>
          </thead>
          <tbody>
            {dummyData.map(function (ele, i) {
              return (
                <>
                  <tr>
                    {/* <td  onClick={() => navigate(`/noticeDetail/${ele.notice_seq}`)}>{ele.notice_title}</td> */}
                    <td>{ele.date}</td>
                    <td>{ele.login_time}</td>
                    <td>{ele.logout_time}</td>
                  </tr>
                </>
              );
            })}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default ConsultingLog;
