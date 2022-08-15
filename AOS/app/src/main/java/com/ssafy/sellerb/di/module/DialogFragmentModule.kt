package com.ssafy.sellerb.di.module

import androidx.lifecycle.ViewModelProvider
import com.ssafy.sellerb.ViewModelProviderFactory
import com.ssafy.sellerb.data.repository.ConsultingRepository
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseDialog
import com.ssafy.sellerb.ui.consulting.review.ConsultingReviewDialogViewModel
import com.ssafy.sellerb.util.CoroutineDispatchers
import com.ssafy.sellerb.util.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class DialogFragmentModule(private val fragment: BaseDialog<*>) {

    @Provides
    fun provideConsultingReviewDialogViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        consultingRepository: ConsultingRepository
    ): ConsultingReviewDialogViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(ConsultingReviewDialogViewModel::class) {
            ConsultingReviewDialogViewModel(
                coroutineDispatchers,
                networkHelper,
                userRepository,
                consultingRepository
            )
        })[ConsultingReviewDialogViewModel::class.java]

}