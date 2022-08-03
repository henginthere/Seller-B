package com.ssafy.sellerb.data.repository

import com.ssafy.sellerb.data.local.prefs.UserPreferences
import com.ssafy.sellerb.data.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userPreferences: UserPreferences
){
    fun saveCurrentUser(user :User){
        userPreferences.setUserId(user.id)
        userPreferences.setUserName(user.name)
    }

    fun removeCurrentUser() {

    }

    fun getCurrentUser(): User? {
        val userId = userPreferences.getUserId()
        val userName = userPreferences.getUserName()

        return if (userId != null && userName != null)
            User(userId,userName)
        else
            null
    }
}