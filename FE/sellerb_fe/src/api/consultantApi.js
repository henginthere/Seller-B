import { api } from './api';

// GET : List : ManagerMainLeft.js
export const listConsultantApi = async (success, fail) => {
    return await api.get(`/consultant/list`).then(success).catch(fail);
}

// get : Detail : ConsultantDetail.js : 상담사 
export const detailConsultantApi= async ( consultantSeq, success, fail) => {
    return await api.get(`/consultant/${consultantSeq}`).then(success).catch(fail);
}


// post : Register : ConsultantWrite.js
export const registerConsultantApi = async ( consultant, success, fail ) => {
    return await api.post("/consultant/register", consultant).then(success).catch(fail);
}

// PUT : Modify : ConsultantModify.js
export const modifyConsultantApi = async ( consultant, success, fail ) => {
    console.log("in API: " + consultant);
    return await api.put(`/consultant/${consultant.consultantSeq}`, consultant).then(success).catch(fail);
}

// DELTE 
export const deleteConsultant = async ( consultantSeq, success, fail) => {
    
    return await api.delete(`/consultant/${consultantSeq}`).then(success).catch(fail);
}

// GET : Search : ManagerMainRight.js
export const searchConsultantApi = async ( consultantName, success, fail) => {
    console.log("SEARCH API: " + consultantName);
    return await api.get(`/consultant/search/${consultantName}`).then(success).catch(fail);
}

// GET : List : AttendanceLog.js 
export const listAttendanceApi = async ( success, fail) => {
    return await api.post("/consultant-attendance/list").then(success).catch(fail);
}

// GET : List : ConsultingLog.js 
export const listConsultingApi = async ( success, fail) => {
    return await api.post("/consulting/list").then(success).catch(fail);
}





// get : List : AttendanceLog.js
// export const listConsultantAttendanceApi = async ( success, fail) => {
//     return await api.post("/consultant-attendance/list").then(success).catch(fail);
// }

// // get : List : ConsultingLog.js
// export const listConsultantAttendanceApi = async ( success, fail) => {
//     return await api.post("/consultant-attendance/list").then(success).catch(fail);
// }