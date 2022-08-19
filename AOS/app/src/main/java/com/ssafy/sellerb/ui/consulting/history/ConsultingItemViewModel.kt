package com.ssafy.sellerb.ui.consulting.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ssafy.sellerb.data.model.Consulting
import com.ssafy.sellerb.data.model.Product
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.repository.ConsultingRepository
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseItemViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.network.NetworkHelper
import javax.inject.Inject

class ConsultingItemViewModel @Inject constructor(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val consultingRepository: ConsultingRepository
) : BaseItemViewModel<Consulting>(coroutineDispatchers, networkHelper) {

    private val user: User = userRepository.getCurrentUser()!!

    val consultantName: LiveData<String> =
        Transformations.map(data) { it.consultant.consultantName }
    val consultantThumbnail: LiveData<String> =
        Transformations.map(data) { it.consultant.consultantThumbnail }
    val productName: LiveData<String> = Transformations.map(data) { it.product.name }
    val productSeq: LiveData<Long> = Transformations.map(data) { it.product.seq }
    val productThumbnail: LiveData<String> = Transformations.map(data) { it.product.thumbnail }
    val productPrice: LiveData<Int> = Transformations.map(data) { it.product.price }
    val consultingDate: LiveData<String> = Transformations.map(data) { it.consultingDate }

    val launchOrderFragment: MutableLiveData<Product> = MutableLiveData()

    fun onClick() {
        launchOrderFragment.postValue(data.value!!.product)
    }


    override fun onCreate() {}

}