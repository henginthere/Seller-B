import React from "react";
import Button from "@mui/material/Button";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import { ListItemText } from "@mui/material";
import { Divider } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";

import './ManagerMain.css'

function ManagerMainRight() {
  const navigate = useNavigate();

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

  // Name, 사번, 담당 제품군
  const dummyNoticeList = [
    {
      consultant_name: "김상담",
      consultant_id: "SPA123AA",
      product_group_name: "에어컨",
    },
    {
      consultant_name: "박상담",
      consultant_id: "SPA1CCC",
      product_group_name: "전자레인지",
    },
    {
      consultant_name: "임상담",
      consultant_id: "SPMK332",
      product_group_name: "냉장고",
    },
  ];

  const goDetail = (id)=>{
    console.log(id)
    navigate(`/manager/consultantDetail/${id}`);
  }

  return (
    <div style={styleObj}>
      <div style={styleObj_right}>
        <Link to="/manager/consultantList">
          <Button variant="contained">상담사 관리</Button>
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
            {dummyNoticeList.map(function (ele, i) {
              return (
                <>
                  <tr>
                  {/* <td  onClick={() => navigate(`/noticeDetail/${ele.notice_seq}`)}>{ele.notice_title}</td> */}
                    {/* <td onClick={()=> navigate(`/manager/consultantDetail/${ele.consultant_id}`)}>{ele.consultant_name}</td> */}
                    <td onClick={()=> goDetail(ele.consultant_id)}>{ele.consultant_name}</td>
                    <td>{ele.consultant_id}</td>
                    <td>{ele.product_group_name}</td>
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
