package com.ssafy.sellerb.ui.consulting

import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers

class WaitingViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    userRepository: UserRepository
) : BaseViewModel(coroutineDispatchers){


    override fun onCreate() {

    }

}