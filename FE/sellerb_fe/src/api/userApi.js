import api from "./api";

import fileapi from "./fileapi";

import axios from "axios";

export const LoginApi = async (userInfo, success, fail) => {
  return await api.post("/auth/login");
};

export const modifyManagerApi = async (userInfo, seq, success, fail) => {
  return await api
    .put(`/manager/${seq}`, userInfo, {
      header: {
        "Content-Type": `application/json`,
      },
    })
    .then(success)
    .catch(fail);
};
export const registerApi = async (userInfo, success, fail) => {
  // console.timeLog("in registerAPI :" + JSON.stringify(userInfo))
  return await api
    .post("/manager/register", userInfo)
    .then(success)
    .catch(fail);
};
