import React, { useState , useEffect} from "react";
import { Link, useNavigate} from "react-router-dom";

// import Axios from 'axios'
import { registerNoticeApi } from '../../api/noticeApi' 
import "./NoticeWrite.css";
import { Footer, NavBar } from "../../components/index";
// calculate Date Util
import getStringDate from "../../utils/date";


function NoticeWrite() {
    
    const navigate = useNavigate();
    const [noticeTitle, setNoticeTitle] = useState('');
    const [noticeContent, setNoticeContent] = useState('');

    const onNoticeTitleHandler = ((event)=>{
        setNoticeTitle(event.currentTarget.value)
        // test
        console.log(noticeTitle);
    })

    const onNoticeContentHandler = ((event)=>{
        setNoticeContent(event.currentTarget.value)
        // test
        console.log(noticeContent);
    })

    const onSubmitBtnHandler = (event)=>{
        event.preventDefault();

        // axios
        let noticePostBody = {
            notice_title : noticeTitle,
            notice_content: noticeContent,
            notice_reg_date: getStringDate(new Date()),
        }

        registerNoticeApi(noticePostBody)
        .then((res)=>{
            console.log(res.data);
            navigate('/manager/noticeList');
        })
        .catch((err)=>{
            console.log(err.data);
        })

        // Axios.post('/notice', noticePostBody)
        // .then((res)=> console.log("success"))
        // .catch((err) => console.log("err"))
    };

    const onResetBtnHandler = () =>{
        setNoticeTitle("");
        setNoticeContent("");
    }

    const onCloseBtnHandler = () => {
        navigate('/manager/noticeList', {replace: true})
    }


  return (
    <>
    <NavBar />  
        <div className="notice-write-header">공지사항 작성</div>
        <div className="write-form-wrapper">
            <div className="write-input-title">
                <input 
                    className="write-title"
                    placeholder="제목을 입력해주세요.." 
                    value={noticeTitle} 
                    onChange={onNoticeTitleHandler} />
                 <hr />
            </div>
            
            <div className="write-input-content">
                <input
                    className="write-content"
                    placeholder="내용을 입력해주세요 .."
                    value={noticeContent}
                    onChange={onNoticeContentHandler} 
                />
            </div>
            <div className="btns-wrapper">
                <button className="back-btn" onClick={onCloseBtnHandler} >뒤로</button>
                <button className="register-btn" onClick={onSubmitBtnHandler}>등록</button>
                <button className="reset-btn" onClick={onResetBtnHandler}>초기화</button>
            </div>
        
        </div> 
      
    <Footer />
    </>
  )
}

export default NoticeWrite