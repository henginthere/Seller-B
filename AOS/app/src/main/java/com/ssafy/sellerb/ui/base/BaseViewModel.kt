package com.ssafy.sellerb.ui.base

import androidx.lifecycle.ViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers

abstract class BaseViewModel(
    protected val coroutineDispatchers: CoroutineDispatchers
) : ViewModel(){
    abstract fun onCreate()


}