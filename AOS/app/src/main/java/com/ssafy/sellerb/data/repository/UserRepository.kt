package com.ssafy.sellerb.data.repository

import android.util.Log
import com.ssafy.sellerb.data.local.prefs.UserPreferences
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.remote.NetworkService
import com.ssafy.sellerb.data.remote.request.LoginRequest
import com.ssafy.sellerb.data.remote.request.SignupRequest
import com.ssafy.sellerb.data.remote.response.GeneralResponse
import com.ssafy.sellerb.data.remote.response.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userPreferences: UserPreferences,
    private val networkService: NetworkService
){
    fun saveCurrentUser(user :User){
        userPreferences.setUserId(user.id)
        userPreferences.setAccessesToken(user.accessToken)
        userPreferences.setRefreshToken(user.refreshToken)
        userPreferences.setAuthority(user.authority)
        userPreferences.setUserSeq(user.seq)
        userPreferences.setUserName(user.name ?: "")
        userPreferences.setUserEmail(user.email ?: "")
        userPreferences.setUserBirth(user.birth ?: "")
        userPreferences.setUserToken(user.token ?: "")
    }

    fun removeCurrentUser() {
        userPreferences.removeUserId()
        userPreferences.removeUserName()
        userPreferences.removeAccessesToken()
        userPreferences.removeRefreshToken()
        userPreferences.removeAuthority()
        userPreferences.removeUserSeq()
        userPreferences.removeUserEmail()
        userPreferences.removeUserBirth()
        userPreferences.removeUserToken()
    }

    fun getCurrentUser(): User? {
        val userId = userPreferences.getUserId()
        val userName = userPreferences.getUserName()
        val accessToken = userPreferences.getAccessesToken()
        val refreshToken = userPreferences.getRefreshToken()
        val authority = userPreferences.getAuthority()
        val userSeq = userPreferences.getUserSeq()
        val userEmail = userPreferences.getUserEmail()
        val userBirth = userPreferences.getUserBirth()
        val userToken = userPreferences.getUserToken()

        return if (userId != null && accessToken != null && refreshToken != null
            && authority != null && userSeq != null)
            User(userId,accessToken,refreshToken,authority, userSeq, userName,
                userEmail, userBirth, userToken)
        else
            null
    }

    fun doUserLogin(id : String, pass : String) : Flow<User> {
        return flow {
            val response = networkService.doLoginCall(LoginRequest(id,pass))
            val userInfoResponse = networkService.getUserInfoCall(
                response.seq, response.token.accessToken )

            emit(User(id, response.token.accessToken, response.token.refreshToken,
                response.authority, response.seq, userInfoResponse.name ?: "",
                userInfoResponse.email ?: "", userInfoResponse.birth ?: ""
                , userInfoResponse.token ?: ""))
        }.flowOn(Dispatchers.IO)
    }

    fun doUserSignup(request : SignupRequest) : Flow<GeneralResponse>{
        return flow {
            val response = networkService.doSignupCall(request)
            if(response.name != null){
                emit(GeneralResponse("200","OK"))
            }else{
                emit(GeneralResponse("500","Fail"))
            }

        }.flowOn(Dispatchers.IO)
    }

    fun doGoogleLogin(idToken: String) : Flow<String>{
        return flow {
            val response = networkService.doGoogleLoginCall(idToken)
            if( response != null){
                emit(response)
            }
        }.flowOn(Dispatchers.IO)
    }
}