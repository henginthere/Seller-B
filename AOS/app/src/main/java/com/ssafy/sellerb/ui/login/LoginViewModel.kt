package com.ssafy.sellerb.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInCredential
import com.ssafy.sellerb.data.remote.request.SimpleLoginRequest
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.Event
import com.ssafy.sellerb.util.network.NetworkHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(
    coroutineDispatchers,
    networkHelper
){
    private val ioContext = coroutineDispatchers.io()
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val idField : MutableLiveData<String> = MutableLiveData()
    val pwField : MutableLiveData<String> = MutableLiveData()
    val loginFail: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val googleCredential: MutableLiveData<SignInCredential> = MutableLiveData()

    override fun onCreate() {}

    fun onIdChange(id : String) = idField.postValue(id)

    fun onPasswordChange(pw : String) = pwField.postValue(pw)

    fun doLogin() {
        val id = idField.value
        val pw = pwField.value
        if(id != null && pw != null){
            viewModelScope.launch(ioContext){
                try {
                    userRepository.doUserLogin(id,pw)
                        .onStart {  }
                        .collect{
                            userRepository.saveCurrentUser(user = it)
                            launchMain.postValue(Event(emptyMap()))
                        }
                }catch (ex: Exception){
                    handleNetworkError(ex)
                    loginFail.postValue(Event(true))
                }
            }
        }
    }

    fun doGoogleLogin(id: String, pw: String, name: String){
        viewModelScope.launch(ioContext){
            try {
                userRepository.doGoogleLogin(SimpleLoginRequest(id,pw,name))
                    .onStart {  }
                    .collect{
                        userRepository.saveCurrentUser(user = it)
                        launchMain.postValue(Event(emptyMap()))
                    }
            }catch (ex: Exception){
                handleNetworkError(ex)
                loginFail.postValue(Event(true))
            }
        }

    }

}