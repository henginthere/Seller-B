import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { listNoticeApi, searchNoticeApi } from "../../api/noticeApi";
import "./NoticeList.css";
import { Footer, NavBar } from "../../components/index";
import { SmallButton } from "../../components/Common/SmallButton";
import { MediButton } from "../../components/Common/MediButton";
import { PaginationBox } from "../../components/Common/PaginationBox";
import Pagination from "react-js-pagination";

function NoticeList() {
  const navigate = useNavigate();

  const [noticeList, setNoticeList] = useState([]); // -> api res.data 로 값 갱신해주기
  const [searchTitle, setSearchTitle] = useState("");
  var isManager = true;
  var isManager = false;
  if (sessionStorage.getItem("adminCheck") === "ROLE_ADMIN") {
    isManager = true;
  } else {
    isManager = false;
  }
  // pagination
  const [page, setPage] = useState(1);
  const handlePageChange = (page) => {
    console.log("현재 페이지: " + page);
    setPage(page);
  };
  const [it, setIt] = useState(7);

  const onSearchByTitleHandler = (e) => {
    setSearchTitle(e.target.value);
  };

  const submitBtnSearchByTitle = (e) => {
    // Test
    console.log(searchTitle);
    // Axios
    if (searchTitle === "") {
      listNoticeApi()
        .then((res) => {
          setNoticeList(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    }
    searchNoticeApi(searchTitle)
      .then((res) => {
        setNoticeList(res.data.reverse());
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const goWriteBtn = () => {
    navigate("/manager/noticeWrite");
  };

  useEffect(() => {
    listNoticeApi()
      .then((res) => {
        // console.log(res.data);
        setNoticeList(res.data.reverse());
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
      <NavBar />

      <div className='consultant-main-image-wrapper'>
        <img
          src={`${process.env.PUBLIC_URL}/img/consultantMainPageImage.jpg`}
        />
      </div>
      <div className='notice-list-wrapper'>
        <div className='notice-title-wrapper'>
          <h4 className='notice-title-text'>공지사항</h4>
        </div>
        <div className='notice-wrapper'>
          <div className='search-byTitle-wrapper'>
            <input
              className='search-byTitle'
              placeholder='제목으로 검색하기'
              value={searchTitle}
              onChange={onSearchByTitleHandler}
            />
            <SmallButton label='검색' onClick={submitBtnSearchByTitle} />
          </div>
          <br />
          {/*  */}
          <table className='notice-table-list'>
            <thead className='notice-table-thead'>
              <tr className='notice-th-tr'>
                <th className='notice-th-tr-No'>No</th>
                <th className='notice-th-tr-title'>Title</th>
                <th className='notice-th-tr-date'>Date</th>
              </tr>
            </thead>
            {/* body 시작 */}
            <tbody className='notice-body'>
              {noticeList
                .slice(it * (page - 1), it * (page - 1) + it)
                .map((list) => {
                  return (
                    <tr className='notice-tbody-tr'>
                      <td className='notice-seq'>{list.noticeSeq}</td>
                      <td
                        className='notice-list-title'
                        onClick={() =>
                          navigate(`/noticeDetail/${list.noticeSeq}`)
                        }
                      >
                        {list.noticeTitle}
                      </td>
                      <td className='notice-regdate'>
                        {parsingDate(list.noticeRegDate).slice(0, 10)}
                      </td>
                    </tr>
                  );
                })}
            </tbody>
          </table>

          {/* <div className='notice-write-wrapper'> */}
            <div className='notice-list-bottom-wrapper'>
              <div className="notice-list-paginationBox">
              <PaginationBox>
                <Pagination
                  activePage={page}
                  itemsCountPerPage={it}
                  totalItemsCount={noticeList.length - 1}
                  pageRangeDisplayed={3}
                  onChange={handlePageChange}
                ></Pagination>
              </PaginationBox>
              </div>
              <div>

              <SmallButton onClick={goWriteBtn} size='sm' label='글작성하기' />
              </div>
            </div>  

            {/* {isManager ? (
              <Link to="/manager/noticeWrite">
                <button className="write-btn">글작성</button>
              </Link>
            ) : null} */}
          {/* </div> */}


        </div>
      </div>
      <Footer />
    </>
  );
}

export default NoticeList;
