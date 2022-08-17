import api from "./api";
// import { useSelector } from 'react-redux'

// post : Register : NoticeWrite.js
export const registerNoticeApi = async (content, success, fail) => {
  return await api.post("/notice", content).then(success).catch(fail);
};

// get : Detail : NoticeDetail.js
export const detailNoticeApi = async (noticeId, success, fail) => {
  return await api.get(`/notice/${noticeId}`).then(success).catch(fail);
};

// get : List : NoticeList.js
export const listNoticeApi = async (success, fail) => {
  return await api.get(`/notice/list`).then(success).catch(fail);
};

export const searchNoticeApi = async (noticeTitle, success, fail) => {
  return await api
    .get(`/notice/search/${noticeTitle}`)
    .then(success)
    .catch(fail);
};

export const modifyNoticeApi = async (Info, success, fail) => {
  // console.log("in modifyNoticeAPI:" + JSON.stringify(Info.post));
  // console.log("in modifyNoticeAPI: id :" + Info.noticeSeq);
  return await api
    .put(`/notice/${Info.noticeSeq}`, Info.post)
    .then(success)
    .catch(fail);
};

export const delNoticeApi = async (noticeSeq, success, fail) => {
  return await api.delete(`/notice/${noticeSeq}`).then(success).catch(fail);
};
