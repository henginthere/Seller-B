import { api } from './api';

// GET : List : ManagerMainLeft.js
export const listConsultantApi = async (success, fail) => {
    return await api.get(`/consultant`).then(success).catch(fail);
}

// get : Detail : ConsultantDetail.js : 상담사 
export const detailConsultantApi= async ( consultant_id, success, fail) => {
    return await api.get(`/consultant/${consultant_id}`).then(success).catch(fail);
}



// post : Register : ConsultantWrite.js
export const registerConsultantApi = async ( consultant_info, success, fail ) => {
    return await api.post("/consultant", consultant_info).then(success).catch(fail);
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