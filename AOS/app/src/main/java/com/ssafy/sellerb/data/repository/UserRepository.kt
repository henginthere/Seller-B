package com.ssafy.sellerb.data.repository

import com.ssafy.sellerb.data.local.prefs.UserPreferences
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.remote.NetworkService
import com.ssafy.sellerb.data.remote.request.LoginRequest
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
        //userPreferences.setUserName(user.name)
        userPreferences.setAccessesToken(user.accessToken)
    }

    fun removeCurrentUser() {
        userPreferences.removeUserId()
        //userPreferences.removeUserName()
        userPreferences.removeAccessesToken()
    }

    fun getCurrentUser(): User? {
        val userId = userPreferences.getUserId()
        val userName = userPreferences.getUserName()
        val accessToken = userPreferences.getAccessesToken()

        return if (userId != null && accessToken != null)
            User(userId,accessToken,"","",0)
        else
            null
    }

    fun doUserLogin(id : String, pass : String) : Flow<User> {

        return flow {
            val response = networkService.doLoginCall(LoginRequest(id,pass))
            val userInfo = networkService.doSignupCall(LoginRequest(id,pass))

            emit(User(id,response.token.accessToken,response.token.refreshToken,response.authority,response.seq))
        }.flowOn(Dispatchers.IO)
    }
}