package com.ssafy.sellerb.util.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
interface CoroutineDispatchers {

    fun default(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun main(): CoroutineDispatcher
}