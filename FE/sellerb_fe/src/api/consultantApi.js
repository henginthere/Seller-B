import api from "./api";

// GET : List : ManagerMainLeft.js
export const listConsultantApi = async (success, fail) => {
  return await api.get(`/consultant/list`).then(success).catch(fail);
};

// GET : List : ManagerMainRight.js : 제품군으로 해당하는 상담사 리스트 불러오기
export const listGroupConsultantApi = async (
  productGroupName,
  success,
  fail,
) => {
  return await api
    .get(`/consultant/list/${productGroupName}`)
    .then(success)
    .catch(fail);
};

// get : Detail : ConsultantDetail.js : 상담사
export const detailConsultantApi = async (consultantSeq, success, fail) => {

  return await api
    .get(`/consultant/${consultantSeq}`)
    .then(success)
    .catch(fail);
};

// post : Register : ConsultantWrite.js
export const registerConsultantApi = async (consultant, success, fail) => {

  return await api
    .post("/consultant/register", consultant)
    .then(success)
    .catch(fail);
};

// PUT : Modify : ConsultantModify.js
export const modifyConsultantApi = async (seq, consultant, success, fail) => {
  return await api
    .put(`/consultant/${seq}`, consultant, {
      header: {
        "Content-Type": `application/json`,
      },
    })
    .then(success)
    .catch(fail);
};

// DELTE
export const deleteConsultantApi = async (consultantSeq, success, fail) => {
  return await api
    .delete(`/consultant/${consultantSeq}`, {
      header: {
        "Content-Type": `application/json`,
      },
    })
    .then(success)
    .catch(fail);
};

// GET : Search : ManagerMainRight.js
export const searchConsultantApi = async (consultantName, success, fail) => {
  return await api
    .get(`/consultant/search/${consultantName}`)
    .then(success)
    .catch(fail);
};

// GET : List : ManagerMainRight.js : 소속 브랜드에 해당하는 상담사들만 보여주기
export const brandConsultantListApi = async (brandName, success, fail) => {

  return await api
    .get(`/consultant/list/brand/${brandName}`)
    .then(success)
    .catch(fail);
};

// 상담사 출결기록 & 상담기록

/* 상담사 출근버튼, 퇴근버튼  */
// POST : ConsultantMain.js
export const goWorkApi = async (consultantSeq, success, fail) => {
  return await api
    .post("/consultant-attendance", consultantSeq)
    .then(success)
    .catch(fail);
};

// PUT : ConsultantMain.js
export const leaveWorkApi = async (consultantSeq, success, fail) => {
  return await api
    .put(`/consultant-attendance/${consultantSeq}`)
    .then(success)
    .catch(fail);
};

/* 출결이력, 상담이력 List */
// GET : List : AttendanceLog.js
export const listAttendanceApi = async (consultantSeq, success, fail) => {
  return await api
    .get(`/consultant-attendance/list/${consultantSeq}`)
    .then(success)
    .catch(fail);
};

// GET : List : ConsultingLog.js
export const listConsultingApi = async (consultantSeq, success, fail) => {
  return await api
    .get(`/consulting/consultant/list/${consultantSeq}`)
    .then(success)
    .catch(fail);
};
