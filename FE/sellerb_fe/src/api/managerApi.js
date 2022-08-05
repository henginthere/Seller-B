import { api } from "./api";

export const managerRegisterApi = async (managerData, success, fail) => {
  return await api
    .post(`/manager/register`, managerData)
    .then(success)
    .catch(fail);
};
