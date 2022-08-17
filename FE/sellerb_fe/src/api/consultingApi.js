import api from "./api";

// Seq로 조회한 대기목록
export const waitingCostomerApi = async (waitingSeq, success, fail) => {
  return await api.get(`/waiting/${waitingSeq}`).then(success).catch(fail);
};
// 상담 대기 삭제
export const delWaitingCostomerApi = async (waitingSeq, success, fail) => {
  return await api
    .delete(`/waiting/list/${waitingSeq}`)
    .then(success)
    .catch(fail);
};
// 제품군으로 조회한 상담대기목록
export const waitingCostomerListApi = async (
  productGroupSeq,
  success,
  fail,
) => {
  return await api
    .get(`/waiting/list/${productGroupSeq}`)
    .then(success)
    .catch(fail);
};

// 상담 시작시 상담 정보 올림
export const startConsultingApi = async (consultingData, success, fail) => {
  return await api
    .post(`/consulting`, consultingData)
    .then(success)
    .catch(fail);
};

// 상담 종료시 state -> end로 변경
export const endConsultingApi = async (consultingSeq, success, fail) => {
  return await api
    .put(
      `/consulting/state/${consultingSeq}`,
      {
        consultingState: "end",
      },
      { header: { "Content-Type": "application/json" } },
    )
    .then(success)
    .catch(fail);
};
