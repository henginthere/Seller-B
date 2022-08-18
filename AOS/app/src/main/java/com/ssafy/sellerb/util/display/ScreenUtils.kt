package com.ssafy.sellerb.util.display

import android.content.res.Resources

object ScreenUtils {

    fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

    fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels
}