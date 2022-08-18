package com.ssafy.sellerb.ui.consulting.history

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.ssafy.sellerb.data.model.Consulting
import com.ssafy.sellerb.ui.base.BaseAdapter

class ConsultingAdapter(
    parentLifecycle: Lifecycle,
    consultings: ArrayList<Consulting>
) : BaseAdapter<Consulting,ConsultingItemViewHolder>(parentLifecycle, consultings){

    lateinit var OnClickListener : OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultingItemViewHolder {
        return ConsultingItemViewHolder(parent,OnClickListener)
    }

    fun setupOnClickListener(OnClickListener: OnClickListener){
        this.OnClickListener = OnClickListener
    }

}