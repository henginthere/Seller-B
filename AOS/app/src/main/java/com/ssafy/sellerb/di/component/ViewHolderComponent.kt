package com.ssafy.sellerb.di.component

import com.ssafy.sellerb.ViewModelScope
import com.ssafy.sellerb.di.module.ViewHolderModule
import com.ssafy.sellerb.ui.consulting.history.ConsultingItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: ConsultingItemViewHolder)

}