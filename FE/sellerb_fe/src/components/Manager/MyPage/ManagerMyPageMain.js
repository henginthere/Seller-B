/**
 * 
 * 
 * 관리자 My Page
 * 
 * 
 */



import React from "react";
import "./ManagerMyPageMain.css";
function ManagerMyPageMain(props){
    // console.log(props);

    return <div id="wrapper">
    <div id="left">
        <div>사진</div>
        <div>정보들...</div>
        <div>{props.data.id}</div>
    </div>
    <div id="right">
        <h3>상담 이력</h3>
        <div>리스트 출력 </div>
    </div>
    </div>
}

export default ManagerMyPageMain;