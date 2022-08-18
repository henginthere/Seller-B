package com.ssafy.sellerb.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssafy.sellerb.data.model.Product
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.repository.ProductRepository
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.network.NetworkHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper,
    userRepository: UserRepository,
    private val productRepository: ProductRepository
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    override fun onCreate() {}

    val user: User? = userRepository.getCurrentUser()
    val productInfo: MutableLiveData<Product> = MutableLiveData()

    init {
        if(user != null){
            getProductInfo()
        }
    }

    fun getProductInfo(){
        viewModelScope.launch(coroutineDispatchers.io()){
            try {
                productRepository.getProductInfo(10L,user!!)
                    .onStart {  }
                    .collect{
                        productInfo.postValue(it)
                    }
            }catch (ex: Exception){
                handleNetworkError(ex)
            }
        }
    }

}