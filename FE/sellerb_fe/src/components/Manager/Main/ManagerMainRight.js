import React, { useEffect, useState } from "react";
import Button from "@mui/material/Button";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import { ListItemText } from "@mui/material";
import { Divider } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import { listConsultantApi } from '../../../api/consultantApi'

import './ManagerMain.css'

const styleObj = {
  width: "75%",
  border: "solid 2px black",
  borderRadius: "10px",
  margin: "10px",
};
const styleObj_center = {
  textAlign: "center",
  margin: "50px",
};
const styleObj_right = {
  display: "flex",
  justifyContent: "flex-end",
  margin: "50px",
};

const tableHeader = {
  display: "flex",
  // textAlign: "center",
  // justifyContent: "center",
  backgroundColor: "grey",
}

const tableRow = {
  
}

const tableData = {
  justifyContent: "start"
}


function ManagerMainRight() {
  const navigate = useNavigate();

  const [data, setData] = useState([]);
  console.log("MainRight: " + data);

  const goDetail = (seq)=>{
    console.log("seq:" + seq);
    navigate(`/manager/consultantDetail/${seq}`);
  }

  useEffect(()=>{
    listConsultantApi()
    .then((res)=>{
      console.log("after API:" + res.data[0].consultantId);
      setData(res.data);
      // console.log("consultantList: " + data[0].consultantId)
    })  
    .catch((err)=>{
      console.log("err:" + err.data);
    })
  },[])

  return (
    <div style={styleObj}>
      <div style={styleObj_right}>
        <Link to="/manager/consultantRegister">
          <Button variant="contained">상담사 추가</Button>
        </Link>
      </div>
      <div style={styleObj_center}>
        <table className="table-wrapper">
          <thead className="table-header-wrapper">
            <tr>
              <th>상담사 이름</th>
              <th>사번</th>
              <th>담당 제품군</th>
            </tr>
          </thead>
          <tbody>
            {data.map(function (ele, i) {
              return (
                <>
                  <tr>
                  {/* <td  onClick={() => navigate(`/noticeDetail/${ele.notice_seq}`)}>{ele.notice_title}</td> */}
                    {/* <td onClick={()=> navigate(`/manager/consultantDetail/${ele.consultant_id}`)}>{ele.consultant_name}</td> */}
                    {/* <td onClick={()=>goDetail(ele.consultant_id)}>{ele.consultant_name}</td> */}
                    <td onClick={() =>
                        navigate(`/manager/consultantDetail/${ele.consultantSeq}`)
                      }>{ele.consultantName}</td>
                    <td>{ele.consultantId}</td>
                    <td>{ele.consultantSeq}</td>
                  </tr>
                  
                </>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ManagerMainRight;
