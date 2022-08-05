package com.ssafy.sellerb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers
import com.ssafy.sellerb.util.Event

class HomeViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    userRepository: UserRepository
) : BaseViewModel(coroutineDispatchers) {

    val count = 0;

    private val user: User? = userRepository.getCurrentUser()

    private val _isLogIn: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isLogin: LiveData<Event<Boolean>> = _isLogIn

    init {
        if(user == null){
            _isLogIn.postValue(Event(false))
        }else{
            _isLogIn.postValue(Event(true))
        }
    }
    override fun onCreate() {

    }


}