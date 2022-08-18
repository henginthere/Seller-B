package com.ssafy.sellerb.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.sellerb.SellerBApplication
import com.ssafy.sellerb.di.component.DaggerViewHolderComponent
import com.ssafy.sellerb.di.component.ViewHolderComponent
import com.ssafy.sellerb.di.module.ViewHolderModule
import com.ssafy.sellerb.util.display.Toaster
import javax.inject.Inject

abstract class BaseItemViewHolder<T : Any, VM : BaseItemViewModel<T>>(
    @LayoutRes layoutId: Int, parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)),
    LifecycleOwner {

    init {
        onCreate()
    }

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var lifecycleRegistry: LifecycleRegistry

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    open fun bind(data: T) {
        viewModel.updateData(data)
    }

    protected fun onCreate() {
        injectDependencies(buildViewHolderComponent())
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        setupObservers()
        setupView(itemView)
    }

    fun onStart() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED

    }

    fun onStop() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    fun onDestroy() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    private fun buildViewHolderComponent() =
        DaggerViewHolderComponent
            .builder()
            .applicationComponent((itemView.context.applicationContext as SellerBApplication).applicationComponent)
            .viewHolderModule(ViewHolderModule(this))
            .build()

    open fun showMessage(message: String) = Toaster.show(itemView.context, message)

    open fun showMessage(@StringRes resId: Int) = showMessage(itemView.context.getString(resId))

    protected open fun setupObservers() {
        viewModel.messageString.observe(this) {
            it.data?.run { showMessage(this) }
        }

        viewModel.messageStringId.observe(this) {
            it.data?.run { showMessage(this) }
        }
    }

    protected abstract fun injectDependencies(viewHolderComponent: ViewHolderComponent)

    abstract fun setupView(view: View)

}