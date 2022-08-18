package com.ssafy.sellerb.ui.consulting.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssafy.sellerb.data.model.Consulting
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.repository.ConsultingRepository
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.network.NetworkHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ConsultingHistoryViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val consultingRepository: ConsultingRepository,
    private val allConsultingList: ArrayList<Consulting>
) : BaseViewModel(coroutineDispatchers, networkHelper){

    private val user: User? = userRepository.getCurrentUser()

    val _consultings: MutableLiveData<List<Consulting>> = MutableLiveData()
    val consultings: LiveData<List<Consulting>> = _consultings

    override fun onCreate() {}

    init {
        if(user != null){
            onFetchConsultingList()
        }
    }

    private fun onFetchConsultingList(){
        viewModelScope.launch(coroutineDispatchers.io()){
            try {
                consultingRepository.getConsultingDay(user!!.id, user.accessToken)
                    .onStart {  }
                    .collect{
                        allConsultingList.addAll(it.toMutableList())
                        _consultings.postValue(it)
                    }
            }catch (ex: Exception){
                handleNetworkError(ex)
            }
        }
    }

}