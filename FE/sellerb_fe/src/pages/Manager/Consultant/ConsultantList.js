import React, { useState , useEffect} from 'react'
import { Link, useNavigate} from "react-router-dom";

// import Axios from 'axios'
// import css파일
import { Footer, NavBar } from "../../../components/index";

// Name, 사번, 담당 제품군
const dummyNoticeList = [
  {
    consultant_name: '김상담',
    consultant_id: "SPA123AA",
    product_group_name:"에어컨"
  },
  {
    consultant_name: '박상담',
    consultant_id: "SPA1CCC",
    product_group_name:"전자레인지"
  },
  {
    consultant_name: '임상담',
    consultant_id: "SPMK332",
    product_group_name:"냉장고"
  },
];


function ConsultantList() {
  return (
    <>
      <NavBar />
        <div className="page-title">상담사 목록</div>
        <div className="consultant-list">
          <table>
            <thead>
              <tr>
                <th>No</th>
                <th>Title</th>
                <th>Date</th>
              </tr>
            </thead>
            <tbody>
              {dummyNoticeList.map(function (ele, i) {
                return (
                  <tr>
                    <td>{ele.notice_seq}</td>
                    <td >{ele.notice_title}</td>
                    <td>{ele.notice_reg_date}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>

      <Footer />
    </>
  )
}

export default ConsultantList