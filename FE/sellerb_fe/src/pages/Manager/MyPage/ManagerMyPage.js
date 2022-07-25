import React from "react";
import NavBar from "../../../components/Common/NavBar/NavBar";
import Footer from "../../../components/Common/footer";
import ManagerMyPageMain from "../../../components/Manager/MyPage/ManagerMyPageMain"



function ManagerMyPage(){
    const dummy_data ={
        brand:"SAMSUNG",
        id:"admin",
        name:"관리자",
        pw:"admin",
        tel:"010-9999-9999",
        email:"admin@admin.com",

    }
    const styleObj = {
        margin:"10px",
    }

    return <>
        <NavBar></NavBar>
        <div style={styleObj}>
        <h1>MyPage</h1>
        <ManagerMyPageMain data={dummy_data}/>

        </div>

        <Footer></Footer>
        </>
}

export default ManagerMyPage;