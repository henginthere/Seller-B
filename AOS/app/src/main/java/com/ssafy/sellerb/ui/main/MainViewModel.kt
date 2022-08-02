package com.ssafy.sellerb.ui.main

import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers

class MainViewModel(
    coroutineDispatchers: CoroutineDispatchers,
    private val userRepository: UserRepository
) :BaseViewModel(coroutineDispatchers){
    override fun onCreate() {

    }

}