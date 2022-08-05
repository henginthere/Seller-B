package com.ssafy.sellerb.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences){

    companion object {
        const val KEY_USER_ID = "PREF_KEY_USER_ID"
        const val KEY_USER_NAME = "PREF_KEY_USER_NAME"
        const val KEY_ACCESSES_TOKEN = "PREF_KEY_ACCESSES_TOKEN"

    }

    fun getUserId(): String? =
        prefs.getString(KEY_USER_ID, null)

    fun setUserId(userId: String) =
        prefs.edit().putString(KEY_USER_ID,userId).apply()

    fun removeUserId() =
        prefs.edit().remove(KEY_USER_ID).apply()

    fun getUserName(): String? =
        prefs.getString(KEY_USER_NAME, null)

    fun setUserName(userName: String) =
        prefs.edit().putString(KEY_USER_NAME,userName).apply()

    fun removeUserName() =
        prefs.edit().remove(KEY_USER_NAME).apply()

    fun getAccessesToken() =
        prefs.getString(KEY_ACCESSES_TOKEN, null)

    fun setAccessesToken(accessToken : String) =
        prefs.edit().putString(KEY_ACCESSES_TOKEN,accessToken).apply()

    fun removeAccessesToken() =
        prefs.edit().remove(KEY_ACCESSES_TOKEN).apply()

}