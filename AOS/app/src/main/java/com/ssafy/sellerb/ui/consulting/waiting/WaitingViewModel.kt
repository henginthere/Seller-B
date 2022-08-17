package com.ssafy.sellerb.ui.consulting.waiting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.model.Waiting
import com.ssafy.sellerb.data.remote.request.WaitingRequest
import com.ssafy.sellerb.data.remote.response.ConsultingStateResponse
import com.ssafy.sellerb.data.repository.ConsultingRepository
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.network.NetworkHelper
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.lang.Exception

class WaitingViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val consultingRepository: ConsultingRepository
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    val user: User? = userRepository.getCurrentUser()
    val waiting: MutableLiveData<Waiting> = MutableLiveData()
    val waitingCancel: MutableLiveData<Boolean> = MutableLiveData()
    private var waitingSeq: Long = userRepository.getWaitingSeq()
    val isWaiting: MutableLiveData<Boolean> = MutableLiveData()
    val startConsulting: MutableLiveData<Boolean> = MutableLiveData()
    val consultingInfo: MutableLiveData<ConsultingStateResponse> = MutableLiveData()


    init {
        if (waitingSeq != 0L) {
            isWaiting.postValue(false)
        } else {
            isWaiting.postValue(true)
        }
    }

    fun loadWaiting() {
        viewModelScope.launch(coroutineDispatchers.io()) {
            try {
                consultingRepository.getWaitingInfo(waitingSeq, user!!.accessToken)
                    .onStart { }
                    .collect {
                        waiting.postValue(it)
                        getConsultingState()
                    }
            } catch (ex: Exception) {
                handleNetworkError(ex)
            }
        }
    }

    fun startWaiting(productSeq: Long) {
        val request = WaitingRequest(user!!.seq, productSeq.toLong(), false)
        viewModelScope.launch(coroutineDispatchers.io()) {
            try {
                consultingRepository.startWaiting(request)
                    .onStart { }
                    .collect {
                        waiting.postValue(it)
                        userRepository.setWaitingSeq(it.waitingCustomerSeq)
                        waitingSeq = it.waitingCustomerSeq
                    }
            } catch (ex: Exception) {
                handleNetworkError(ex)
            }
        }
    }

    fun doCancel() {
        viewModelScope.launch(coroutineDispatchers.io()) {
            try {
                if (waitingSeq != 0L && user!!.accessToken != null) {
                    consultingRepository.cancelWaiting(
                        waiting.value!!.waitingCustomerSeq,
                        user!!.accessToken
                    )
                        .onStart { }
                        .collect {
                            waitingCancel.postValue(true)
                            userRepository.removeWaitingSeq()
                        }
                }
            } catch (ex: Exception) {
                handleNetworkError(ex)
            }
        }
    }

    fun getConsultingState() {
        viewModelScope.launch(coroutineDispatchers.io()) {
            consultingRepository.getConsultingState(user!!.id, user.accessToken)
                .onStart { }
                .collect {
                    if (it.consultingState == "waiting") {
                        consultingInfo.postValue(it)
                    }
                }
        }
    }

    fun setConsulting() {
        viewModelScope.launch(coroutineDispatchers.io()) {
            consultingRepository.startConsulting(
                user!!.id, user.accessToken,
                consultingInfo.value!!.consultingSeq, waitingSeq
            )
                .onStart { }
                .collect {
                    if (it) {
                        startConsulting.postValue(true)
                        userRepository.removeWaitingSeq()
                    }
                }
        }
    }

    override fun onCreate() {
    }

}