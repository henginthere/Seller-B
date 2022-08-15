package com.ssafy.sellerb.data.repository

import com.ssafy.sellerb.data.model.Waiting
import com.ssafy.sellerb.data.remote.NetworkService
import com.ssafy.sellerb.data.remote.request.ConsultingStartRequest
import com.ssafy.sellerb.data.remote.request.WaitingRequest
import com.ssafy.sellerb.data.remote.response.ConsultingInfoResponse
import com.ssafy.sellerb.data.remote.response.ConsultingStateResponse
import com.ssafy.sellerb.data.remote.response.WaitingResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConsultingRepository @Inject constructor(
    private val networkService: NetworkService
){

    fun startWaiting(waitingRequest: WaitingRequest): Flow<Waiting> {
        return flow{
            val response = networkService.startWaiting(waitingRequest)
            emit(Waiting(response.waitingCustomerSeq,
                response.productSeq,
                response.productThumbnail,
                response.productName,
                response.productId,
                response.waitingCustomerState
            ))
        }
    }

    fun getWaitingInfo(waitingSeq: Long, accessToken: String): Flow<Waiting>{
        return flow {
            val response = networkService.getWaitingInfo(waitingSeq,accessToken)
            emit(Waiting(response.waitingCustomerSeq,
                response.productSeq,
                response.productThumbnail,
                response.productName,
                response.productId,
                response.waitingCustomerState
            ))
        }
    }

    fun cancelWaiting(waitingSeq: Long, accessToken: String): Flow<Boolean>{
        return flow {
            val response = networkService.cancelWaiting(waitingSeq,accessToken)
            if (response.statusCode == "200"){
                emit(true)
            }else{
                emit(false)
            }
        }
    }

    fun getConsultingState(id: String, accessToken: String): Flow<ConsultingStateResponse>{
        return flow {
            val response = networkService.getConsultingState(id,accessToken)
            emit(response.last())
        }
    }

    fun startConsulting(id:String, accessToken: String, consultantSeq: Long, waitingSeq: Long): Flow<Boolean>{
        return flow {
            val request = ConsultingStartRequest("start")
            val response = networkService.setConsultingState(consultantSeq, request)
            val cancelResponse = networkService.cancelWaiting(waitingSeq, accessToken)
            emit(true)
        }
    }
}