import api from "./api";
import axios from "axios";

export const LoginApi = async (userInfo, success, fail) => {
  return await api.post("/auth/login");
};

export const registerApi = async (userInfo, success, fail) => {
  // console.timeLog("in registerAPI :" + JSON.stringify(userInfo))
  return await api
    .post("/manager/register", userInfo)
    .then(success)
    .catch(fail);
};
