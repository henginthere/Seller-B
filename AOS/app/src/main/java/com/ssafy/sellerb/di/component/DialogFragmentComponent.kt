package com.ssafy.sellerb.di.component

import com.ssafy.sellerb.FragmentScope
import com.ssafy.sellerb.di.module.DialogFragmentModule
import com.ssafy.sellerb.ui.consulting.review.ConsultingReviewDialog
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [DialogFragmentModule::class]

)
interface DialogFragmentComponent {

    fun inject(dialogFragment : ConsultingReviewDialog)

}