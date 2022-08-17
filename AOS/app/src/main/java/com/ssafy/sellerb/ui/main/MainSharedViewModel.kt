package com.ssafy.sellerb.ui.main

import androidx.lifecycle.MutableLiveData
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.Event
import com.ssafy.sellerb.util.network.NetworkHelper

class MainSharedViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers,networkHelper){

    override fun onCreate() {}

    val qrCodeResult = MutableLiveData<Event<Long>>()

    val consultingSeq = MutableLiveData<Long>()

    val token: MutableLiveData<String> = MutableLiveData()

    init {
        consultingSeq.postValue(0L)
    }

    fun onQrCodeResult(productSeq : Long){
        qrCodeResult.postValue(Event(productSeq))
    }
}