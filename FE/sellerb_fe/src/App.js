import "./App.css";
import { Routes, Route } from "react-router-dom";
import React from "react";

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
  ConsultingMain,

  MainTwo,
  ConsultantMyPageEdit,
  ManagerMyPageEdit,
} from "./pages/index";

function App() {
  return (
    <>
      <Routes>
        <Route path='/' element={<MainTwo />} />
        <Route path="/main" element={<Main />} />
        <Route path='/manager/main' element={<ManagerMain />} />
        <Route path='/consultant/main' element={<ConsultantMain />} />
        <Route path='/manager/mypage/:id' element={<ManagerMyPage />} />
        <Route path='/manager/register' element={<ManageRegister />} />
        <Route path='/manager/noticeList' element={<NoticeList />} />
        <Route path='/manager/noticeWrite' element={<NoticeWrite />} />
        <Route path='/noticeDetail/:id' element={<NoticeDetail />} />
        <Route path='/manager/noticeEdit/:id' element={<NoticeEdit />} />
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
          path='/manager/consultantModify/:id'
          element={<ConsultantModify />}
        />
        <Route path='/consultant/mypage/:id' element={<ConsultantMyPage />} />
        <Route path='/consultant/mypage/edit/:id' element={<ConsultantMyPageEdit />} />

        <Route path='/manager/mypage/edit/:id' element={<ManagerMyPageEdit />} />

        <Route path='/manager/productList' element={<ProductList />} />
        <Route path='/manager/productRegister' element={<ProductRegister />} />
        <Route path='/manager/productDetail/:seq' element={<ProductDetail />} />
        <Route path='/manager/productEdit/:seq' element={<ProductEdit />} />
        <Route path='/manager/waitingPage/:id' element={<WaitingPage />} />
        <Route path='/meeting/mancon' element={<MeetingManCon />} />
        <Route path='/meeting/consultingMain' element={<ConsultingMain />} />
      </Routes>
    </>
  );
}

export default App;
