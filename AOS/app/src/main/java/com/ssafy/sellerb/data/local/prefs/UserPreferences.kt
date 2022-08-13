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
        const val KEY_REFRESH_TOKEN = "PREF_KEY_REFRESH_TOKEN"
        const val KEY_AUTHORITY = "PREF_KEY_AUTHORITY"
        const val KEY_USER_SEQ = "PREF_USER_SEQ"
        const val KEY_USER_EMAIL = "PREF_USER_EMAIL"
        const val KEY_USER_BIRTH = "PREF_USER_BIRTH"
        const val KEY_USER_TOKEN = "PREF_USER_TOKEN"
        const val KEY_WAITING_SEQ = "PREF_WAITING_SEQ"
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

    fun getRefreshToken() =
        prefs.getString(KEY_REFRESH_TOKEN, null)

    fun setRefreshToken(refreshToken : String) =
        prefs.edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply()

    fun removeRefreshToken() =
        prefs.edit().remove(KEY_REFRESH_TOKEN).apply()

    fun getAuthority() =
        prefs.getString(KEY_AUTHORITY, null)

    fun setAuthority(authority: String) =
        prefs.edit().putString(KEY_AUTHORITY, authority).apply()

    fun removeAuthority() =
        prefs.edit().remove(KEY_AUTHORITY).apply()

    fun getUserSeq() :Long =
        prefs.getLong(KEY_USER_SEQ, 0L)

    fun setUserSeq(userSeq : Long) =
        prefs.edit().putLong(KEY_USER_SEQ,userSeq).apply()

    fun removeUserSeq() =
        prefs.edit().remove(KEY_USER_SEQ).apply()

    fun getUserEmail() =
        prefs.getString(KEY_USER_EMAIL, null)

    fun setUserEmail(userEmail: String) =
        prefs.edit().putString(KEY_USER_EMAIL, userEmail).apply()

    fun removeUserEmail() =
        prefs.edit().remove(KEY_USER_EMAIL).apply()

    fun getUserBirth() =
        prefs.getString(KEY_USER_BIRTH,null)

    fun setUserBirth(userBirth: String) =
        prefs.edit().putString(KEY_USER_BIRTH, userBirth).apply()

    fun removeUserBirth() =
        prefs.edit().remove(KEY_USER_BIRTH).apply()

    fun getUserToken() =
        prefs.getString(KEY_USER_TOKEN, null)

    fun setUserToken(token : String) =
        prefs.edit().putString(KEY_USER_TOKEN, token).apply()

    fun removeUserToken() =
        prefs.edit().remove(KEY_USER_TOKEN).apply()

    fun getWaitingSeq() =
        prefs.getLong(KEY_WAITING_SEQ, 0L)

    fun setWaitingSeq(waitingSeq: Long) =
        prefs.edit().putLong(KEY_WAITING_SEQ, waitingSeq).apply()

    fun removeWaitingSeq() =
        prefs.edit().remove(KEY_WAITING_SEQ).apply()
}