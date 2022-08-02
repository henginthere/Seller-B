import { api } from "api/api";
import axios from 'axios';

// export const LoginApi = async (userInfo, success, fail) => {
//     return await api.post("/manager/login")
// }

const statusError = {
    status: false,
    json: {
        error: ["연결이 원활하지 않습니다. 잠시 후 다시 시도해 주세요"]
    }

};

