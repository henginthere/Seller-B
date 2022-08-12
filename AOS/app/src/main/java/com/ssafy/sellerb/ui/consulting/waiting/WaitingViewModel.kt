package com.ssafy.sellerb.ui.consulting.waiting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.remote.request.WaitingRequest
import com.ssafy.sellerb.data.repository.ProductRepository
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers
import com.ssafy.sellerb.util.Event
import com.ssafy.sellerb.util.network.NetworkHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.lang.Exception

class WaitingViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) : BaseViewModel(coroutineDispatchers, networkHelper){

    private val user: User? = userRepository.getCurrentUser()
    val waitingSeq: MutableLiveData<Long> = MutableLiveData()

    init{
//        val request = WaitingRequest(user!!.seq, 1L, false )
//        viewModelScope.launch(coroutineDispatchers.io()){
//            try {
//                productRepository.doWaitingCustomer(request)
//                    .onStart {  }
//                    .collect{
//                        waitingSeq.postValue(it)
//                    }
//            } catch (ex: Exception){
//                handleNetworkError(ex)
//            }
//        }
    }

    override fun onCreate() {
    }

}