package com.ssafy.sellerb.data.remote

import com.ssafy.sellerb.data.model.Waiting

object Endpoints {

    const val LOGIN = "auth/login"
    const val SIGNUP = "customer/register"
    const val GET_USER_INFO = "customer/{seq}"
    const val GET_PRODUCT_INFO = "product/{seq}"
    const val WAITING_CUSTOMER = "waiting"
    const val GET_CONSULTING_INFO = "consulting/customer/{customer-id}"
    const val START_CONSULTING = "consulting/state/{seq}"
    const val WAITING_CANCEL = "waiting/{seq}"
    const val GET_WAITING_INFO = "waiting/{seq}"
    const val GET_CONSULTING_STATE = "consulting/customer/day/{customer-id}"
    const val GOOGLE_LOGIN = "customer/oauth/login/google"

}