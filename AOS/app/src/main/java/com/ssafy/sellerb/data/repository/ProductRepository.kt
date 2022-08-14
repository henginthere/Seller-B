package com.ssafy.sellerb.data.repository

import com.ssafy.sellerb.data.model.Product
import com.ssafy.sellerb.data.model.User
import com.ssafy.sellerb.data.remote.NetworkService
import com.ssafy.sellerb.data.remote.request.WaitingRequest
import com.ssafy.sellerb.data.remote.response.WaitingResponse
import com.ssafy.sellerb.util.network.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val networkService: NetworkService,
    private val networkHelper: NetworkHelper
){
    companion object{
        const val TAG = "ProductRepository"
    }

    fun getProductInfo(productSeq: Long, user: User): Flow<Product> {
        return flow {
            val response = networkService.getProductInfo(
                productSeq,
                user.accessToken
            )
            emit(response)
        }
    }

}