package com.ssafy.sellerb.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.sellerb.ViewModelProviderFactory
import com.ssafy.sellerb.data.model.Product
import com.ssafy.sellerb.data.repository.ConsultingRepository
import com.ssafy.sellerb.data.repository.ProductRepository
import com.ssafy.sellerb.data.repository.UserRepository
import com.ssafy.sellerb.ui.base.BaseFragment
import com.ssafy.sellerb.ui.consulting.history.ConsultingAdapter
import com.ssafy.sellerb.ui.consulting.history.ConsultingHistoryViewModel
import com.ssafy.sellerb.ui.consulting.waiting.WaitingViewModel
import com.ssafy.sellerb.ui.home.HomeViewModel
import com.ssafy.sellerb.ui.login.LoginViewModel
import com.ssafy.sellerb.ui.main.MainSharedViewModel
import com.ssafy.sellerb.ui.mypage.MyPageViewModel
import com.ssafy.sellerb.ui.product.ProductViewModel
import com.ssafy.sellerb.ui.signup.SignupViewModel
import com.ssafy.sellerb.util.coroutine.CoroutineDispatchers
import com.ssafy.sellerb.util.network.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideConsultingAdapter() = ConsultingAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideHomeViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): HomeViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(HomeViewModel::class){
            HomeViewModel(coroutineDispatchers, networkHelper, userRepository)
        })[HomeViewModel::class.java]

    @Provides
    fun provideMyPageViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): MyPageViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(MyPageViewModel::class){
            MyPageViewModel(coroutineDispatchers, networkHelper, userRepository)
        })[MyPageViewModel::class.java]

    @Provides
    fun provideLoginViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): LoginViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(LoginViewModel::class){
            LoginViewModel(coroutineDispatchers, networkHelper, userRepository)
        })[LoginViewModel::class.java]

    @Provides
    fun provideWaitingViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        consultingRepository: ConsultingRepository
    ): WaitingViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(WaitingViewModel::class){
            WaitingViewModel(coroutineDispatchers, networkHelper, userRepository, consultingRepository)
        })[WaitingViewModel::class.java]

    @Provides
    fun provideSignupViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): SignupViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(SignupViewModel::class){
            SignupViewModel(coroutineDispatchers, networkHelper, userRepository)
        })[SignupViewModel::class.java]

    @Provides
    fun provideMainSharedViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper
    ) : MainSharedViewModel = ViewModelProvider(
        fragment.activity!!, ViewModelProviderFactory(MainSharedViewModel::class){
            MainSharedViewModel(coroutineDispatchers, networkHelper)
        })[MainSharedViewModel::class.java]

    @Provides
    fun provideConsultingHistoryViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        consultingRepository: ConsultingRepository
    ) : ConsultingHistoryViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(ConsultingHistoryViewModel::class){
            ConsultingHistoryViewModel(coroutineDispatchers, networkHelper, userRepository,
            consultingRepository, ArrayList())
        })[ConsultingHistoryViewModel::class.java]

    @Provides
    fun provideProductViewModel(
        coroutineDispatchers: CoroutineDispatchers,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        productRepository: ProductRepository
    ) : ProductViewModel = ViewModelProvider(
        fragment, ViewModelProviderFactory(ProductViewModel::class){
            ProductViewModel(coroutineDispatchers, networkHelper, userRepository,
            productRepository)
        })[ProductViewModel::class.java]
}