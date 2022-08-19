package com.ssafy.sellerb.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssafy.sellerb.data.remote.request.SignupRequest
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.Event
import com.ssafy.sellerb.util.network.NetworkHelper
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SignupViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(coroutineDispatchers, networkHelper){

    override fun onCreate() {}

    val launchLogin : MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val idField: MutableLiveData<String> = MutableLiveData()
    val pwField: MutableLiveData<String> = MutableLiveData()
    val nameField: MutableLiveData<String> = MutableLiveData()

    fun onIdChange(id : String) = idField.postValue(id)

    fun onPasswordChange(pw : String) = pwField.postValue(pw)

    fun onNameChange(name: String) = nameField.postValue(name)

    fun doSignup() {
        val id = idField.value
        val pw = pwField.value
        val name = nameField.value
        if(id != null && pw != null && name != null){
            viewModelScope.launch(coroutineDispatchers.io()){
                userRepository.doUserSignup(SignupRequest(id,pw,name))
                    .onStart {  }
                    .collect{
                        if(it.statusCode == "200"){
                            launchLogin.postValue(Event(emptyMap()))
                        }
                    }
            }
        }
    }
}