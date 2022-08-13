import React, { useEffect, useState } from "react";
import Pagination from 'react-js-pagination'
import styled from 'styled-components'
import { useNavigate, Link } from "react-router-dom";

import { PaginationBox } from "../Common/PaginationBox";
import { productLineItemsApi } from "../../api/productApi";
import "./ProductOption.css";

// Product : product_id, name, price, thumbnail(이미지), reg_date(등록일시)


function ProdcutOption({ items }) {
  // 해당 제품군에 대한 상품들 -> 리스트로 받기
  const [data, setData] =  useState(items); // 더미데이터로 셋팅
  const [page, setPage] = useState(1);
  const [it, setIt] = useState(4);
  const navigate = useNavigate();
  
  const handlePageChange = (page) => { setPage(page); };

  const onNavigate = (product_seq) => {
    navigate(`/manager/productDetail/${product_seq}`);
  };

  return (
    <>
      {/* 각 제품(div) 여러 개 만들기 */}
      <div className="body-wrapper">
        {items.slice(
          it*(page-1),
          it*(page-1)+it
        ).map((ele, i) => {
          return (
            <>
              <div
                className="element-wrapper"
                onClick={() => navigate(`/manager/productDetail/${ele.productSeq}`)}>
                <img
                  alt="#"
                  src={ele.productThumbnail}
                />
                <div className="product-info">
                  <h5>품번 : </h5>
                  {ele.productId}
                </div>
                <div className="product-info" >
                  <h5>제품명 : </h5>
                  {ele.productName}
                </div>
                <div className="product-info">
                  <h5>가격 : </h5>
                  {ele.productPrice}
                </div>
              </div>
              
            </>
          );
        })}
        
      </div>
      <PaginationBox>
        <Pagination
          activePage={page}
          itemsCountPerPage={it}
          totalItemsCount={items.length-1}
          pageRangeDisplayed={5}
          onChange={handlePageChange}>
        </Pagination>
      </PaginationBox>
    </>
  );
}

export default ProdcutOption;
