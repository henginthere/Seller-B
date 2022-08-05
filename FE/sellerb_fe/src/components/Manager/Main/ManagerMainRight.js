import React, { useEffect, useState } from "react";
import Button from "@mui/material/Button";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import { ListItemText } from "@mui/material";
import { Divider } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import {
  listConsultantApi,
  searchConsultantApi,
} from "../../../api/consultantApi";

import "./ManagerMain.css";

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
};

const tableRow = {};

const tableData = {
  justifyContent: "start",
};

function ManagerMainRight() {
  const navigate = useNavigate();

  const [data, setData] = useState([]);
  const [searchName, setSearchName] = useState();
  const [searchCon, setSearchCon] = useState([]); // 검색결과 해당 컨설턴트
  // 검색된 상담사를 보일 건지, 상담사 목록리스트를 보일건지 관리하는 state
  const [listState, setListState] = useState(true);

  console.log("MainRight: " + data);

  const goDetail = (seq) => {
    console.log("seq:" + seq);
    navigate(`/manager/consultantDetail/${seq}`);
  };

  const onSearchBtn = () => {
    searchConsultantApi(searchName)
      .then((res) => {
        console.log(res.data);
        // 받아온 해당 컨설턴트 출력하기
        setSearchCon(res.data);
        console.log(searchCon.consultantName)
        setListState(false)
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const onSearchNameChane = (e)=>{
    setSearchName(e.target.value)
  }

  useEffect(() => {
    listConsultantApi()
      .then((res) => {
        console.log("after API:" + res.data[0].consultantId);

        setData(res.data);
      })
      .catch((err) => {
        console.log("err:" + err.data);
      });
  }, []);

  return (
    <div style={styleObj}>
      <div style={styleObj_right}>

        <input
          type="text"
          placeholder="상담사 이름을 검색하세요"
          value={searchName}
          onChange={onSearchNameChane}
        />
        <button onClick={(e) => onSearchBtn()}>검색하기</button>
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
            {listState ? (
              data.map(function (ele, i) {
                return (
                  <>
                    <tr>
                      <td
                        onClick={() =>
                          navigate(
                            `/manager/consultantDetail/${ele.consultantSeq}`
                          )
                        }
                      >
                        {ele.consultantName}
                      </td>
                      <td>{ele.consultantId}</td>
                      <td>{ele.consultantSeq}</td>
                    </tr>
                  </>
                );
              })
            ) : (
              <>
              {/* <div>TESTSSSSSSSSSSSSSSS</div> */}
                <tr>
                  <td
                    onClick={() =>
                      navigate(`/manager/consultantDetail/${searchCon.consultantSeq}`)
                    }
                  >
                    {searchCon[0].consultantName}
                  </td>
                  <td>{searchCon[0].consultantId}</td>
                  <td>{searchCon[0].consultantSeq}</td>
                </tr>
              </>
            )}
          </tbody>
        </table>
        <Link to="/manager/consultantRegister">
          <Button variant="contained">상담사 추가</Button>
        </Link>
      </div>
    </div>
  );
}

export default ManagerMainRight;
