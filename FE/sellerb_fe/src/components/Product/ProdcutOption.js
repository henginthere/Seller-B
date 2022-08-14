import React, { useEffect, useState } from "react";
import Pagination from 'react-js-pagination'
import styled from 'styled-components'
import { useNavigate, Link } from "react-router-dom";

import { PaginationBox } from "../Common/PaginationBox";
import { productLineItemsApi } from "../../api/productApi";
import "./ProductOption.css";

// Product : product_id, name, price, thumbnail(이미지), reg_date(등록일시)

// const PaginationBox = styled.div`
//   .pagination { display: flex; justify-content: center; margin-top: 15px;}
//   ul { list-style: none; padding: 0; }
//   ul.pagination li {
//     display: inline-block;
//     width: 30px;
//     height: 30px;
//     border: 1px solid #e2e2e2;
//     display: flex;
//     justify-content: center;
//     align-items: center;
//     font-size: 1rem; 
//   }
//   ul.pagination li:first-child{ border-radius: 5px 0 0 5px; }
//   ul.pagination li:last-child{ border-radius: 0 5px 5px 0; }
//   ul.pagination li a { text-decoration: none; color: #337ab7; font-size: 1rem; }
//   ul.pagination li.active a { color: white; }
//   ul.pagination li.active { background-color: #337ab7; }
//   ul.pagination li a:hover,
//   ul.pagination li a.active { color: blue; }
//   `

function ProdcutOption({ items }) {
  // 해당 제품군에 대한 상품들 -> 리스트로 받기
  const [data, setData] =  useState(items); // 더미데이터로 셋팅
  const [page, setPage] = useState(1);
  const [it, setIt] = useState(8);
  const navigate = useNavigate();
  
  const handlePageChange = (page) => { setPage(page); };

  const onNavigate = (product_seq) => {
    navigate(`/manager/productDetail/${product_seq}`);
  };

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
