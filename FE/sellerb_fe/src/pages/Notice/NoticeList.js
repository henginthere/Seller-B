import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { listNoticeApi, searchNoticeApi } from "../../api/noticeApi";
import "./NoticeList.css";
import { Footer, NavBar } from "../../components/index";

import { SmallButton} from '../../components/Common/SmallButton'

function NoticeList() {
  const navigate = useNavigate();

  const [noticeList, setNoticeList] = useState([]); // -> api res.data 로 값 갱신해주기
  const [searchTitle, setSearchTitle] = useState("");
  var isManager = true;
  if (sessionStorage.getItem("adminCheck") === "ROLE_ADMIN") {
    isManager = true;
  } else {
    isManager = false;
  }

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

  const goWriteBtn = () =>{
    navigate("/manager/noticeWrite");
  }

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
      <div className="notice-list-wrapper">
        <div className="notice-title-wrapper">
          <h4 className="notice-title-text">공지사항</h4>
        </div>
        <div className="notice-wrapper">
          <div className="search-byTitle-wrapper">
            <input
              className="search-byTitle"
              placeholder="제목으로 검색하기"
              value={searchTitle}
              onChange={onSearchByTitleHandler}
            />
            <SmallButton 
              label="검색"
              onClick={submitBtnSearchByTitle}
            />
          </div>
            <br />
            <table className="notice-table-list">
              <thead className="notice-table-thead">
                <tr className="notice-th-tr">
                  <th className="notice-th-tr-No">No</th>
                  <th className="notice-th-tr-title">Title</th>
                  <th className="notice-th-tr-manager">작성자</th>
                  <th className="notice-th-tr-date">Date</th>
                </tr>
              </thead>
              <tbody className="notice-body">
                {noticeList.map((list) => {
                  return (
                    <tr className="notice-tbody-tr">
                      <td className="notice-seq">{list.noticeSeq}</td>
                      <td 
                        className="notice-title"
                        onClick={() =>
                          navigate(`/noticeDetail/${list.noticeSeq}`)
                        }
                      >
                        {list.noticeTitle}
                      </td>
                      <td className="notice-manager">sellerB관리자</td>
                      <td className="notice-regdate">{list.noticeRegDate}</td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
     
          <div className="notice-write-wrapper">
          <div className="notice-detail-bottom">
                <button 
                  className="detail-button"
                  onClick={(e)=> navigate('/manager/noticeWrite')}
                  >글 작성하기</button>
                
            </div>
            {/* {isManager ? (
              <Link to="/manager/noticeWrite">
                <button className="write-btn">글작성</button>
              </Link>
            ) : null} */}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default NoticeList;
