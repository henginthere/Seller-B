import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { listConsultingApi } from "../../api/consultantApi";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  TableContainer,
} from "@mui/material";
import { PaginationBox } from "../../components/Common/PaginationBox";
import Pagination from "react-js-pagination";
const styleObj_center = {
  display: "flex",
  margin: "20px",
  justifyContent: "flex-start",
  flexDirection: "column",
};

function ConsultingLog({ consultant_id }) {
  const [logData, setLogData] = useState([]);
  const [page, setPage] = useState(1);
  const handlePageChange = (page) => {
    setPage(page);
  };
  const [it, setIt] = useState(9);

  // axios : 상담사 출결이력 표시
  useEffect(() => {
    listConsultingApi(consultant_id)
      .then((res) => {
        setLogData(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  const parsingDate = (date) => {
    var parsedDate = new Date(date);
    var yyyy = parsedDate.getFullYear();
    var MM = parsedDate.getMonth() + 1;
    var dd = parsedDate.getDate();
    var hh = parsedDate.getHours() + 9;
    var mm = parsedDate.getMinutes();

    if (hh > 24) {
      hh = hh - 24;
      dd = dd + 1;
    }

    return (
      yyyy +
      "-" +
      addZero(MM) +
      "-" +
      addZero(dd) +
      "-" +
      addZero(hh) +
      ":" +
      addZero(mm)
    );
  };
  const addZero = (n) => {
    return n < 10 ? "0" + n : n;
  };

  return (
    <>
      <div style={styleObj_center}>
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell style={{fontSize:"13px"}}>상담 날짜</TableCell>
                <TableCell style={{fontSize:"13px"}}>상담 시작시각 </TableCell>
                <TableCell style={{fontSize:"13px"}}>상담 종료시각</TableCell>
                <TableCell style={{fontSize:"13px"}}>상담 제품</TableCell>
                <TableCell style={{fontSize:"13px"}}>상담 고객 이름</TableCell>
                <TableCell style={{fontSize:"13px"}}>상담 고객 ID</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {logData
                .slice(it * (page - 1), it * (page - 1) + it)
                .map(function (ele, i) {
                  return (
                    <>
                      <TableRow>
                        <TableCell>
                          {parsingDate(ele.consultingStartDate).slice(0, 10)}
                        </TableCell>
                        <TableCell>
                          {parsingDate(ele.consultingStartDate).slice(11, 19)}
                        </TableCell>
                        <TableCell>
                          {parsingDate(ele.consultingEndDate).slice(11, 19)}
                        </TableCell>
                        <TableCell>{ele.product.productName}</TableCell>
                        <TableCell>{ele.customer.customerName}</TableCell>
                        <TableCell>{ele.customer.customerId}</TableCell>
                      </TableRow>
                    </>
                  );
                })}
            </TableBody>
          </Table>
        </TableContainer>
        <PaginationBox>
          <Pagination
            activePage={page}
            itemsCountPerPage={it}
            totalItemsCount={logData.length - 1}
            pageRangeDisplayed={3}
            onChange={handlePageChange}
          ></Pagination>
        </PaginationBox>
      </div>
    </>
  );
}

export default ConsultingLog;
