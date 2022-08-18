package com.ssafy.sellerb.ui.consulting.history

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.ssafy.sellerb.R
import com.ssafy.sellerb.data.model.Consulting
import com.ssafy.sellerb.databinding.ItemViewConsultingBinding
import com.ssafy.sellerb.di.component.ViewHolderComponent
import com.ssafy.sellerb.ui.base.BaseItemViewHolder

class ConsultingItemViewHolder(
    parent: ViewGroup,
    private val onClickListener: OnClickListener
) : BaseItemViewHolder<Consulting, ConsultingItemViewModel>(R.layout.item_view_consulting, parent) {

    lateinit var binding: ItemViewConsultingBinding

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {

        binding = ItemViewConsultingBinding.bind(view)

        with(binding) {
            binding.llConsultingInfo.setOnClickListener { viewModel.onClick() }
            binding.ivConsultantThumbnail.setOnClickListener { viewModel.onClick() }
            binding.ivProductThumbnail.setOnClickListener { viewModel.onClick() }
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.productThumbnail.observe(this) {
            Glide.with(binding.ivProductThumbnail.context)
                .load(it)
                .into(binding.ivProductThumbnail)
        }

        viewModel.productName.observe(this) {
            binding.tvProductName.text = it
        }

        viewModel.consultantName.observe(this) {
            binding.tvConsultantName.text = it
        }

        viewModel.consultantThumbnail.observe(this) {
            Glide
                .with(binding.ivConsultantThumbnail.context)
                .load(it)
                .into(binding.ivConsultantThumbnail)
        }

        viewModel.consultingDate.observe(this) {
            binding.tvConsultingDate.text = it.substring(0, 10)
        }

        viewModel.launchOrderFragment.observe(this) {
            if (it != null) {
                val directions = ConsultingHistoryFragmentDirections.actionItemConsultingHistoryToHomeFragment()
                itemView.findNavController().navigate(directions)
            }
        }
    }
}
