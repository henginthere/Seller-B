package com.ssafy.sellerb.data.repository

import com.ssafy.sellerb.data.remote.NetworkService
import com.ssafy.sellerb.data.remote.request.WaitingRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConsultingRepository @Inject constructor(
    private val networkService: NetworkService
){

    fun doWaitingCustomer(waitingRequest: WaitingRequest): Flow<Long> {
        return flow{
            val response = networkService.getWaitingCustomer(
                waitingRequest
            )
            emit(response.waitingCustomerSeq)
        }
    }
}