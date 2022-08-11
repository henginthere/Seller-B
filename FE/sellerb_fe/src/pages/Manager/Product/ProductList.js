import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

import "./ProductList.css";
import { Footer, NavBar, ProdcutOption } from "../../../components/index";
import { productGroupListApi, productGroupItemsApi, brandProductListApi  } from "../../../api/productApi";

import { SearchOutlined, PlusCircleOutlined } from '@ant-design/icons'

function ProductList() {
  const navigate = useNavigate();

  const [items, setItems] = useState([]);
  const [totalItems, setTotalItems] = useState([]);

  const [groupList, setGroupList] = useState([]);
  const [managerBrand, setManagerBrand] = useState(sessionStorage.getItem("brandNameKor"));
  const [brandSeq, setBrandSeq] = useState(sessionStorage.getItem("brandSeq"));

  const [searchWord, setSearchWord] = useState(""); // 제품 검색어 
  const [groupOption, setGroupOption] = useState(""); 

  useEffect(()=>{
    brandProductListApi(brandSeq)
    .then((res)=>{
      setTotalItems(res.data)
      console.log("처음 전체 아이템들 : " + JSON.stringify(res.data));
    })
  }, [])

  useEffect(() => {
    productGroupListApi()
      .then((res) => {
        console.log(JSON.stringify(res.data));
        setGroupList(res.data); // groupList
        console.log("groupList: " + groupList);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const onGroupChange = (e) => {
  
    e.preventDefault();
    setGroupOption(e.target.value)
    
    // option에 해당하는 제품군의 제품들 불러오기
    const selectGroupSeq = groupList.find((it)=> it.brandName === managerBrand
                              && it.productGroupName === e.target.value);

    productGroupItemsApi(selectGroupSeq.productGroupSeq)
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
    //
  };

  const onRegisterBtn = () => {
    navigate("/manager/productRegister");
  };

  // 선택한 제품군 option에 따라, 나타낼 해당 제품군 리스트 컴포넌트
  function GroupOptionList({props}) {
    console.log("items:" + props)
    return <ProdcutOption items={props} />;
  }

  return (
    <>
      <NavBar />
      <div className="page-title">제품 목록</div>
      <div className="product-list">
        <div className="page-navi-wrapper">
          <div className="navi-left">
            <select onChange={onGroupChange} defaultValue={groupOption}>
                <option />
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
              onClick={()=> navigate("/")}
            />

              <div>제품등록</div>
              <PlusCircleOutlined
                id="prod-create-btn" 
                onClick={()=> navigate("/manager/productRegister")} 
              />
          </div>
        </div>
        <div className="product-list-wrapper">
          {groupOption === ""
          ? <GroupOptionList props={totalItems} />
          : <GroupOptionList props={items} />
        }
          
        </div>
        {/* end : page-navi-wrapper */}
      </div>
      <Footer />
    </>
  );
}

export default ProductList;
