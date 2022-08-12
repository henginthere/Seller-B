package com.ssafy.sellerb.data.remote

object Endpoints {

    const val LOGIN = "auth/login"
    const val SIGNUP = "customer/register"
    const val GET_USER_INFO = "customer/{seq}"
    const val GOOGLE_LOGIN = "auth/login/google"
    const val GET_PRODUCT_INFO = "product/{seq}"
    const val WAITING_CUSTOMER = "waiting"
    const val GET_CONSULTING_INFO = "consulting/customer/{customer-id}"
    const val START_CONSULTING = "consulting/state/{seq}"
}