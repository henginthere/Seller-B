package com.ssafy.sellerb.ui.mypage

import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers

class MyPageViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    userRepository: UserRepository
) :BaseViewModel(coroutineDispatchers) {

    val count = 0;

    override fun onCreate() {
    }

}