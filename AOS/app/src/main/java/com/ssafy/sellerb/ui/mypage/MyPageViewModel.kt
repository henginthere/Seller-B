package com.ssafy.sellerb.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers
import com.ssafy.sellerb.util.Event
import kotlinx.coroutines.launch

class MyPageViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    userRepository: UserRepository
) :BaseViewModel(coroutineDispatchers) {

    val launchLogin : MutableLiveData<Event<Map<String,String>>> = MutableLiveData()


    private val userRepository = userRepository

    val user: User? = userRepository.getCurrentUser()

    init {

    }

    override fun onCreate() {

    }

    fun doLogout(){
        viewModelScope.launch(coroutineDispatchers.io()) {
            userRepository.removeCurrentUser()
            launchLogin.postValue(Event(mapOf()))
        }
    }
}