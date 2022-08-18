package com.ssafy.sellerb.di.module

import androidx.lifecycle.LifecycleRegistry
import com.ssafy.sellerb.ViewModelScope
import com.ssafy.sellerb.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}