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
  brandConsultantListApi,
} from "../../../api/consultantApi";
import { productGroupListApi } from "../../../api/productApi";
import { getManagerInfoApi } from "../../../api/managerApi";
import { SmallButton } from "../../Common/SmallButton";
import { MediButton } from "../../Common/MediButton";
import "./ManagerMain.css";
import "./ManagerMainRight.css";
import Pagination from "react-js-pagination";
import { PaginationBox } from "../../Common/PaginationBox";
import { SyncOutlined } from "@ant-design/icons";

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
  const btnStyle = {
    display: "flex",
    justifyContent: "flex-end",
  };
  const navigate = useNavigate();

  const [groupList, setGroupList] = useState([]); // api에서 받아올 전~체 제품군 리스트
  const [brandGroupList, setBrandGroupList] = useState([]);
  const [selectGroup, setSelectGroup] = useState("");

  // pagination
  const [page, setPage] = useState(1);
  const handlePageChange = (page) => {
    setPage(page);
  };
  const [it, setIt] = useState(5);
  // ManagerBrand
  const [managerBrandKor, setManagerBrandKor] = useState(
    sessionStorage.getItem("brandNameKor"),
  );
  const [managerBrandEng, setManagerBrandEng] = useState(
    sessionStorage.getItem("brandNameEng"),
  );

  // 처음에 받아온 컨설턴트 전체 리스트 / 입력된 상담사 검색어 / 검색결과를 담을 해당 컨설턴트 state
  const [consultantList, setConsultantList] = useState([]);
  const [searchName, setSearchName] = useState("");
  const [searchCon, setSearchCon] = useState([]); // 검색결과 해당 컨설턴트

  // 검색된 상담사를 보일 건지, 상담사 목록리스트를 보일건지 관리하는 state
  const [listState, setListState] = useState(true);
  const [isLoaded, setIsLoaded] = useState(false);

  const getBrandConsultantList = async () => {
    const name = sessionStorage.getItem("brandNameKor");
    console.log("LOADING");
    await brandConsultantListApi(name)
      .then((res) => {
        // console.log("전체컨설턴트 :" + JSON.stringify(res.data));
        setConsultantList(res.data);
      })
      .catch((err) => {
        console.log("err:" + err.data);
      });
  };

  useEffect(() => {
    getBrandConsultantList();
    productGroupListApi()
      .then((res) => {
        // const item = brandList.find((it) => it.brandNameKor === value)
        setGroupList(res.data); // groupList
        setIsLoaded(true);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [isLoaded]);

  const onGroupChange = (e) => {
    e.preventDefault();

    setSelectGroup(e.target.value);

    // 바뀐 제품군 이름에 따라 -> 상담사 리스트 보여주기
    // 제품군 이름 : e.target.value // 상담사 리스트 : consultantList

    const items = consultantList.filter(
      (it) =>
        it.brandName === managerBrandEng &&
        it.productGroupName === e.target.value,
    );

    setConsultantList(items);
    console.log("filtered Items : " + JSON.stringify(items));
  };

  const goDetail = (seq) => {
    console.log("seq:" + seq);
    navigate(`/manager/consultantDetail/${seq}`);
  };

  const onSearchBtn = () => {
    if (searchName === "") {
      productGroupListApi()
        .then((res) => {
          // const item = brandList.find((it) => it.brandNameKor === value)
          setGroupList(res.data); // groupList

          console.log("찐 grouplist:" + groupList);
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      searchConsultantApi(searchName)
        .then((res) => {
          console.log(res.data);
          // 받아온 해당 컨설턴트 출력하기
          setSearchCon(res.data);
          setListState(false);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  };

  const onSearchNameChane = (e) => {
    setSearchName(e.target.value);
  };

  const ResetConsultantList = () => {
    const name = sessionStorage.getItem("brandNameKor");
    setListState(true);
    brandConsultantListApi(name)
      .then((res) => {
        console.log("리셋 버튼 전체컨설턴트 :" + JSON.stringify(res.data));
        // setConsultantList([]);
        setConsultantList(res.data);
      })
      .catch((err) => {
        console.log("err:" + err.data);
      });
  };

  return (
    <div style={styleObj}>
      <div style={styleObj_right}>
        <input
          type='text'
          placeholder='상담사 이름을 검색하세요'
          value={searchName}
          onChange={onSearchNameChane}
        />
        {/* <button onClick={(e) => onSearchBtn()}>검색하기</button> */}
        <SmallButton onClick={onSearchBtn} size='sm' label='검색' />

        <select onChange={onGroupChange} defaultValue={selectGroup}>
          <option />
          {groupList.map((option) =>
            option.brandName === managerBrandKor ? ( // grouplist정보에는 각 ele마다 한글이름으로 들어있음
              <option value={option.productGroupName}>
                {option.productGroupName}
              </option>
            ) : (
              ""
            ),
          )}
        </select>
        <SyncOutlined
          style={{ fontSize: "20px", marginLeft: "15px", marginTop: "5px" }}
          onClick={ResetConsultantList}
        />
      </div>

      <div style={styleObj_center}>
        <table className='con-table-list'>
          <thead className='con-table-thead'>
            <tr className='con-th-tr'>
              <th className='con-th-tr-name'>상담사 이름</th>
              <th className='con-th-tr-id'>사번</th>
              <th className='con-th-tr-group'>담당 제품군</th>
            </tr>
          </thead>
          <tbody className='con-list-body'>
            {listState ? (
              consultantList
                .slice(it * (page - 1), it * (page - 1) + it)
                .map(function (ele, i) {
                  return (
                    <>
                      <tr className='con-tbody-tr'>
                        <td
                          className='con-name'
                          onClick={() =>
                            navigate(
                              `/manager/consultantDetail/${ele.consultantSeq}`,
                            )
                          }
                        >
                          {ele.consultantName}
                        </td>
                        <td className='con-id'>{ele.consultantId}</td>
                        <td className='con-seq'>{ele.consultantSeq}</td>
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
                        `/manager/consultantDetail/${searchCon.consultantSeq}`,
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
        <PaginationBox>
          <Pagination
            activePage={page}
            itemsCountPerPage={it}
            totalItemsCount={consultantList.length - 1}
            pageRangeDisplayed={3}
            onChange={handlePageChange}
          ></Pagination>
        </PaginationBox>
        <div style={btnStyle}>
          <Link to='/manager/consultantRegister'>
            <MediButton size='md' label='상담사추가' />
          </Link>
        </div>
      </div>
    </div>
  );
}

export default ManagerMainRight;
