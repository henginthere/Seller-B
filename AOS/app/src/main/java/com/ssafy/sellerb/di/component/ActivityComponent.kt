package com.ssafy.sellerb.di.component

import com.ssafy.sellerb.ActivityScope
import com.ssafy.sellerb.di.module.ActivityModule
import com.ssafy.sellerb.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)
}