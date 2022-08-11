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
  listGroupConsultantApi,
  brandConsultantListApi
} from "../../../api/consultantApi";
import { productGroupListApi } from "../../../api/productApi";
import { getManagerInfoApi } from "../../../api/managerApi"
import { SmallButton } from '../../Common/SmallButton'
import { MediButton } from "../../Common/MediButton";
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

  const [groupList, setGroupList] = useState([]); // api에서 받아올 전~체 제품군 리스트 
  const [brandGroupList, setBrandGroupList] = useState([]);
  const [selectGroup, setSelectGroup] = useState("");

  // ManagerBrand
  const [managerBrandKor, setManagerBrandKor] = useState(
    sessionStorage.getItem("brandNameKor")
  );
  const [managerBrandEng, setManagerBrandEng] = useState(
    sessionStorage.getItem("brandNameEng")
  )

  // 처음에 받아온 컨설턴트 전체 리스트 / 입력된 상담사 검색어 / 검색결과를 담을 해당 컨설턴트 state  
  const [consultantList, setConsultantList] = useState([]);
  const [searchName, setSearchName] = useState();
  const [searchCon, setSearchCon] = useState([]); // 검색결과 해당 컨설턴트

  // 검색된 상담사를 보일 건지, 상담사 목록리스트를 보일건지 관리하는 state
  const [listState, setListState] = useState(true);

  useEffect(() => {
    const name = sessionStorage.getItem("brandNameKor");
    brandConsultantListApi(name)
      .then((res) => {
        // console.log("after API:" + res.data[0].consultantId);
        // 소속브랜드의 상담사들만 보이기
        console.log(JSON.stringify(res.data));
        setConsultantList(res.data);
      })
      .catch((err) => {
        console.log("err:" + err.data);
      });
  }, []);


  useEffect(() => {
    productGroupListApi()
      .then((res) => {
        // const item = brandList.find((it) => it.brandNameKor === value)
        setGroupList(res.data); // groupList

        console.log("찐 grouplist:" + groupList)
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);



  const onGroupChange = (e) => {
    e.preventDefault();

    console.log("e.target.value:" + e.target.value)

    setSelectGroup(e.target.value);

    // data(전체 컨설턴트)에서, 브랜드네임이랑 && 선택한 제품군에 일치하는 상담사만 뽑기
      const item = consultantList.filter(
      it => it.brandName === managerBrandEng );
    
  };

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
        console.log(searchCon.consultantName);
        setListState(false);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const onSearchNameChane = (e) => {
    setSearchName(e.target.value);
  };


  return (
    <div style={styleObj}>
      <div style={styleObj_right}>
        <input
          type="text"
          placeholder="상담사 이름을 검색하세요"
          value={searchName}
          onChange={onSearchNameChane}
        />
        {/* <button onClick={(e) => onSearchBtn()}>검색하기</button> */}
        <SmallButton onClick={onSearchBtn} size="sm" label="검색하기" />
        <select onChange={onGroupChange} defaultValue={selectGroup}>
          <option />
          {groupList.map((option) =>
            option.brandName === managerBrandKor // grouplist정보에는 각 ele마다 한글이름으로 들어있음
            ? (
              <option value={option.productGroupName}>{option.productGroupName}</option>
            ) : (
              ""
            )
          )}
        </select>
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
              consultantList.map(function (ele, i) {
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
                <tr>
                  <td
                    onClick={() =>
                      navigate(
                        `/manager/consultantDetail/${searchCon.consultantSeq}`
                      )
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
          {/* <Button variant="contained">상담사 추가</Button> */}
          <MediButton size="md" label="상담사추가" />
        </Link>
      </div>
    </div>
  );
}

export default ManagerMainRight;
