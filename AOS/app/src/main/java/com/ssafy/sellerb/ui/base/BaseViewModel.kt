package com.ssafy.sellerb.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.sellerb.R
import com.ssafy.sellerb.util.CoroutineDispatchers
import com.ssafy.sellerb.util.Resource
import com.ssafy.sellerb.util.network.NetworkHelper
import javax.net.ssl.HttpsURLConnection

abstract class BaseViewModel(
    protected val coroutineDispatchers: CoroutineDispatchers,
    protected val networkHelper: NetworkHelper
) : ViewModel(){

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()

    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    protected fun handleNetworkError(err: Throwable?) : Resource<Int> {
        if (checkInternetConnection()) {
            return err?.let {
                networkHelper.castToNetworkError(it).run {
                    when (status) {
                        -1 -> Resource.error(R.string.network_default_error)
                        0 -> Resource.error(R.string.server_connection_error)
                        HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                            forcedLogoutUser()
                            Resource.error(R.string.server_connection_error)
                        }
                        HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                            Resource.error(R.string.network_internal_error)
                        HttpsURLConnection.HTTP_UNAVAILABLE ->
                            Resource.error(R.string.network_server_not_available)

                        else -> {
                            Resource.error(R.string.try_again)
                        }
                    }
                }
            } ?: Resource.error(R.string.try_again)
        }else {
            return Resource.error(R.string.network_connection_error)
        }
    }


    abstract fun onCreate()


}