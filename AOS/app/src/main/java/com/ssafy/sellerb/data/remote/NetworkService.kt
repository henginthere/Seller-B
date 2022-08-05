package com.ssafy.sellerb.data.remote

import com.ssafy.sellerb.data.remote.request.LoginRequest
import com.ssafy.sellerb.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
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

}