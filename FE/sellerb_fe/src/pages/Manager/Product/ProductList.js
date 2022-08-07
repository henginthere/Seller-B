import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

import Axios from "axios";
import "./ProductList.css";
import { Footer, NavBar, ProdcutOption } from "../../../components/index";
import { productGroupListApi, productGroupItemsApi, productSearchApi  } from "../../../api/productApi";

import { SearchOutlined, PlusCircleOutlined } from '@ant-design/icons'

function ProductList() {
  const navigate = useNavigate();

  const [items, setItems] = useState([]);

  const [groupList, setGroupList] = useState([]);
  const [managerBrand, setManagerBrand] = useState(
    localStorage.getItem("brandNameKor")
  );

  const [searchWord, setSearchWord] = useState("");
  const [groupOption, setGroupOption] = useState("");

  const onGroupChange = (e) => {
    e.preventDefault();
    setGroupOption(e.target.value)

    console.log("바귄 group state: " + groupOption)
    
    // option에 해당하는 제품군의 제품들 불러오기
    productGroupItemsApi(groupOption)
    .then((res)=>{
      console.log(res.data);
      setItems(res.data)
      console.log("itemslist :" + items);
    })
    .catch((err)=>{
      console.log("error")
    })

    console.log("groupOption : " + groupOption);
  };

  const onSearchWordHandler = (e) => {
    setSearchWord(e.target.value);
    console.log(searchWord);
  };

  const onSearchBtn = () => {
    // 입력된 검색어로 api 호출
    productSearchApi(searchWord)
    .then((res)=>{
      console.log(JSON.stringify(res.data));
      setItems(res.data);

    })
    .catch((err)=>{
      console.log(JSON.stringify(err.data));
    })

  };

  const onRegisterBtn = () => {
    navigate("/manager/productRegister");
  };

  // 선택한 제품군 option에 따라, 나타낼 해당 제품군 리스트 컴포넌트
  function GroupOptionList() {
    console.log("items:" + items)
    return <ProdcutOption items={items} />;
  }

  useEffect(() => {
    productGroupListApi()
      .then((res) => {
        setGroupList(res.data); // groupList
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <>
      <NavBar />
      <div className="page-title">제품 목록</div>
      <div className="product-list">
        <div className="page-navi-wrapper">
          <div className="navi-left">
            <select onChange={(e)=> onGroupChange(e)} key={groupOption} defaultValue={groupOption}>
                {/* <option value="" /> */}
                {groupList.map((option) =>
                  option.brandName === managerBrand ? (
                    <option value={option.productGroupName}>{option.productGroupName}</option>
                  ) : (
                    ""
                  )
                )}

            </select>
          </div>
          <div className="navi-right">
            <input
              placeholder="제품명으로 검색"
              value={searchWord}
              onChange={onSearchWordHandler}
            />
            <SearchOutlined 
              id="search-icon-btn"
              onClick={()=> onSearchBtn()}
            />

              <div>제품등록</div>
              <PlusCircleOutlined
                id="prod-create-btn" 
                onClick={()=> navigate("/manager/productRegister")} 
              />
          </div>
        </div>
        <div className="product-list-wrapper">
          <GroupOptionList />
        </div>
        {/* end : page-navi-wrapper */}
      </div>
      <Footer />
    </>
  );
}

export default ProductList;
