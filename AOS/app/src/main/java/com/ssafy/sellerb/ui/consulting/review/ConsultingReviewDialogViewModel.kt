package com.ssafy.sellerb.ui.consulting.review

import com.ssafy.sellerb.data.repository.ConsultingRepository
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.network.NetworkHelper

class ConsultingReviewDialogViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val consultingRepository: ConsultingRepository
) : BaseViewModel(coroutineDispatchers, networkHelper){

    override fun onCreate() { }

}