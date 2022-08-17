import api from "./api";
<<<<<<< HEAD
import fileapi from "./fileapi";
import axios from 'axios';
=======
import axios from "axios";
>>>>>>> 96376293e59ced092643372075f992b07c8a2e10

export const LoginApi = async (userInfo, success, fail) => {
  return await api.post("/auth/login");
};

<<<<<<< HEAD
export const registerApi = async(userInfo, success, fail) => {
    console.log("in registerAPI :" + JSON.stringify(userInfo))
    return await api.post("/manager/register", userInfo).then(success).catch(fail);
}

export const modifyManagerApi = async(userInfo, seq, success, fail) => {
    

    return await api
        .put(`/manager/${seq}`, userInfo, {
            header:{
                "Content-Type": `application/json`,
            },
        })
        .then(success)
        .catch(fail);
}
=======
export const registerApi = async (userInfo, success, fail) => {
  // console.timeLog("in registerAPI :" + JSON.stringify(userInfo))
  return await api
    .post("/manager/register", userInfo)
    .then(success)
    .catch(fail);
};
>>>>>>> 96376293e59ced092643372075f992b07c8a2e10
