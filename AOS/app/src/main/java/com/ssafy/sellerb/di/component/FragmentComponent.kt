package com.ssafy.sellerb.di.component

import com.ssafy.sellerb.FragmentScope
import com.ssafy.sellerb.di.module.FragmentModule
import com.ssafy.sellerb.ui.home.HomeFragment
import com.ssafy.sellerb.ui.mypage.MyPageFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: MyPageFragment)

}