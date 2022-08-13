import api from './api';

export const managerRegisterApi = async (managerData, success, fail) => {
  return await api
    .post("/manager/register", managerData)
    .then(success)
    .catch(fail);
};

// GET : 관리자 마이페이지 
export const getManagerInfoApi = async (managerSeq, success, fail) => {
  console.log("API method: " + managerSeq)
  return await api.get(`/manager/${managerSeq}`)
  .then(success)
  .catch(fail);
}