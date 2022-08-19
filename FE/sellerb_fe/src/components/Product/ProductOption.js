import React, { useEffect, useState } from "react";
import Pagination from 'react-js-pagination'
import styled from 'styled-components'
import { useNavigate, Link } from "react-router-dom";

import { PaginationBox } from "../Common/PaginationBox";
import { productLineItemsApi } from "../../api/productApi";
import "./ProductOption.css";

function ProductOption({ items }) {
  // 해당 제품군에 대한 상품들 -> 리스트로 받기
  const [data, setData] =  useState(items);
  const [page, setPage] = useState(1);
  const [it, setIt] = useState(8);
  const navigate = useNavigate();

  
  const handlePageChange = (page) => { setPage(page); };

  const onNavigate = (product_seq) => {
    navigate(`/manager/productDetail/${product_seq}`);
  };

  useEffect(()=>{
    data.map((ele, i)=>{
    })

  })

  return (
    <>
      {/* 각 제품(div) 여러 개 만들기 */}
      <div className="product-list-total-count">
        총 {items.length} 건 
      </div>
      
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
                <img className="prod-option-img"
                  alt="#"
                  src={ele.productThumbnail}
                />
                <div className="product-info-id">
                  {ele.productId}
                </div>
                <div className="product-info-name" >
                  {ele.productName}
                </div>
                <div className="product-info-price">
                  {ele.productPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}원
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

export default ProductOption;
