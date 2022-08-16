package com.ssafy.sellerb.data.remote

import com.ssafy.sellerb.data.model.Product
import com.ssafy.sellerb.data.remote.request.*
import com.ssafy.sellerb.data.remote.response.*
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @POST(Endpoints.LOGIN)
    suspend fun doLoginCall(
        @Body request: LoginRequest,
    ):LoginResponse

    @POST(Endpoints.SIGNUP)
    suspend fun doSignupCall(
        @Body request: SignupRequest
    ):UserInfoResponse

    @GET(Endpoints.GET_USER_INFO)
    suspend fun getUserInfoCall(
        @Path("seq") userSeq: Long,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String
    ):UserInfoResponse

    @POST(Endpoints.GOOGLE_LOGIN)
    suspend fun doGoogleLoginCall(
        @Body request: SimpleLoginRequest
    ):LoginResponse

    @GET(Endpoints.GET_PRODUCT_INFO)
    suspend fun getProductInfo(
        @Path("seq") productSeq: Long,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String
    ):Product

    @POST(Endpoints.WAITING_CUSTOMER)
    suspend fun startWaiting(
        @Body request: WaitingRequest
    ):WaitingResponse

    @GET(Endpoints.GET_CONSULTING_INFO)
    suspend fun getConsultingInfo(
        @Path("customer-id") id: String
    ):ConsultingInfoResponse

    @PUT(Endpoints.START_CONSULTING)
    suspend fun setConsultingState(
        @Path("seq") seq: Long,
        @Body request: ConsultingStartRequest
    ):GeneralResponse

    @DELETE(Endpoints.WAITING_CANCEL)
    suspend fun cancelWaiting(
        @Path("seq") seq: Long,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String
    ):GeneralResponse

    @GET(Endpoints.GET_WAITING_INFO)
    suspend fun getWaitingInfo(
        @Path("seq") seq: Long,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String
    ):WaitingResponse

    @GET(Endpoints.GET_CONSULTING_STATE)
    suspend fun getConsultingState(
        @Path("customer-id") id: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String
    ):List<ConsultingStateResponse>
}