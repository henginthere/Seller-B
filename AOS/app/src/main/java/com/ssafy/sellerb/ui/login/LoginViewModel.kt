package com.ssafy.sellerb.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers
import com.ssafy.sellerb.util.Event
import kotlinx.coroutines.launch

class LoginViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    private val userRepository: UserRepository
) : BaseViewModel(
    coroutineDispatchers
){
    private val ioContext = coroutineDispatchers.io()
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val idField : MutableLiveData<String> = MutableLiveData()
    val pwField : MutableLiveData<String> = MutableLiveData()

    override fun onCreate() {}

    fun onIdChange(id : String) = idField.postValue(id)

    fun onPasswordChange(pw : String) = pwField.postValue(pw)

    fun doLogin() {
        val id = idField.value
        val pw = pwField.value
        if(id != null && pw != null){
            viewModelScope.launch(ioContext){
                val currUser = User(id,pw)
                userRepository.saveCurrentUser(currUser)
                launchMain.postValue(Event(emptyMap()))
            }
        }

    }
}