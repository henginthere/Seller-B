package com.ssafy.sellerb.ui.main

import androidx.lifecycle.MutableLiveData
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers
import com.ssafy.sellerb.util.Event
import com.ssafy.sellerb.util.network.NetworkHelper

class MainSharedViewModel(
    coroutineDispatchers: CoroutineDispatchers,
) : BaseViewModel(coroutineDispatchers){

    override fun onCreate() {}

    val qrCodeUrl = MutableLiveData<Event<String>>()

    fun onQrCodeResult(url : String){
        qrCodeUrl.postValue(Event(url))
    }
}