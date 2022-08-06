import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { listNoticeApi, searchNoticeApi } from "../../api/noticeApi";
import "./NoticeList.css";
import { Footer, NavBar } from "../../components/index";

function NoticeList() {
  const navigate = useNavigate();

  const [noticeList, setNoticeList] = useState([]); // -> api res.data 로 값 갱신해주기
  const [searchTitle, setSearchTitle] = useState("");
  var isManager = true;
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
          // console.log(res.data);

          setNoticeList(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    }
    searchNoticeApi(searchTitle)
      .then((res) => {
        // console.log(res.data);
        setNoticeList(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    listNoticeApi()
      .then((res) => {
        // console.log(res.data);

        setNoticeList(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <>
      <NavBar />

      <div className='notice-title'>공지사항</div>
      <div className='notice-wrapper'>
        <div className='search-byTitle-wrapper'>
          <input
            className='search-byTitle'
            placeholder='제목으로 검색하기'
            value={searchTitle}
            onChange={onSearchByTitleHandler}
          />
          <button onClick={submitBtnSearchByTitle}>검색</button>
        </div>
        <div className='notice-list'>
          <table>
            <thead>
              <tr>
                <th>No</th>
                <th>Title</th>
                <th>Date</th>
              </tr>
            </thead>
            <tbody>
              {noticeList.map((list) => {
                return (
                  <tr>
                    <td>{list.noticeSeq}</td>
                    <td
                      onClick={() =>
                        navigate(`/noticeDetail/${list.noticeSeq}`)
                      }
                    >
                      {list.noticeTitle}
                    </td>
                    <td>{list.noticeRegDate}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
        <div className='notice-write-wrapper'>
          {isManager ? (
            <Link to='/manager/noticeWrite'>
              <button className='write-btn'>글작성</button>
            </Link>
          ) : null}
        </div>
      </div>
      <Footer />
    </>
  );
}

export default NoticeList;
