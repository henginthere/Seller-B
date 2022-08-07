package com.ssafy.sellerb.data.remote

import com.ssafy.sellerb.data.remote.request.LoginRequest
import com.ssafy.sellerb.data.remote.response.LoginResponse
import com.ssafy.sellerb.data.remote.response.UserInfoResponse
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
        @Body request: LoginRequest
    )

    @GET(Endpoints.GET_USER_INFO)
    suspend fun getUserInfoCall(
        @Path("seq") userSeq: Long,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String
    ):UserInfoResponse

}