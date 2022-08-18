package com.ssafy.sellerb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.Event
import com.ssafy.sellerb.util.network.NetworkHelper

class HomeViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper,
    userRepository: UserRepository
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    private val user: User? = userRepository.getCurrentUser()

    private val _isLogIn: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isLogin: LiveData<Event<Boolean>> = _isLogIn

    val isWaiting: MutableLiveData<Boolean> = MutableLiveData()

    init {
        if(user == null){
            _isLogIn.postValue(Event(false))
        }else{
            _isLogIn.postValue(Event(true))
        }
        //userRepository.removeWaitingSeq()
        if(userRepository.getWaitingSeq() != 0L){
            isWaiting.postValue(false)
        }else{
            isWaiting.postValue(true)
        }
    }

    fun updateToken(){

    }

    override fun onCreate() {
    }


}