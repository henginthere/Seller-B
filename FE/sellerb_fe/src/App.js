import "./App.css";
import { Routes, Route } from "react-router-dom";
import React from "react";

// import Main from './pages/Main/Main';
import {
  Main,
  ManagerMain,
  ConsultantMain,
  ManagerMyPage,
  ManageRegister,
  NoticeList,
  NoticeWrite,
  NoticeDetail,
  NoticeEdit,
  ConsultantList,
  ConsultantDetail,
  ConsultantMyPage,
  ConsultantRegister,
  ConsultantModify,
  ProductList,
  ProductRegister,
  ProductDetail,
  ProductEdit,
  WaitingPage,
  MeetingManCon,
} from "./pages/index";

function App() {
  return (
    <>
      <body>
        <Routes>
          <Route path='/' element={<Main />} />
          <Route path='/manager/main' element={<ManagerMain />} />
          <Route path='/consultant/main' element={<ConsultantMain />} />
          <Route path='/manager/mypage' element={<ManagerMyPage />} />
          <Route path='/manager/register' element={<ManageRegister />} />
          <Route path='/manager/noticeList' element={<NoticeList />} />
          <Route path='/manager/noticeWrite' element={<NoticeWrite />} />
          <Route path='/noticeDetail/:id' element={<NoticeDetail />} />
          <Route path='/manager/noticeEdit' element={<NoticeEdit />} />
          <Route path='/manager/consultantList' element={<ConsultantList />} />
          <Route
            path='/manager/consultantDetail/:id'
            element={<ConsultantDetail />}
          />
          <Route
            path='/manager/consultantRegister'
            element={<ConsultantRegister />}
          />
          <Route
            path='/manager/consultantModify'
            element={<ConsultantModify />}
          />
          <Route path='/consultant/mypage' element={<ConsultantMyPage />} />
          <Route path='/manager/productList' element={<ProductList />} />
          <Route
            path='/manager/productRegister'
            element={<ProductRegister />}
          />
          <Route
            path='/manager/productDetail/:seq'
            element={<ProductDetail />}
          />
          <Route path='/manager/productEdit/:seq' element={<ProductEdit />} />
          <Route path='/manager/waitingPage/:seq' element={<WaitingPage />} />
	  {/* <Route path='/meeting/mancon' element={<MeetingManCon />} /> */}
        </Routes>
      </body>
    </>
  );
}

export default App;
