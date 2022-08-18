package com.ssafy.sellerb.ui.product

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.navercorp.nid.NaverIdLoginSDK.applicationContext
import com.ssafy.sellerb.R
import com.ssafy.sellerb.databinding.FragmentMyPageBinding
import com.ssafy.sellerb.databinding.FragmentProductBinding
import com.ssafy.sellerb.di.component.FragmentComponent
import com.ssafy.sellerb.ui.base.BaseFragment
import com.ssafy.sellerb.util.Constants.PAYMENT_APPLICATION_ID
import kr.co.bootpay.android.Bootpay
import kr.co.bootpay.android.events.BootpayEventListener
import kr.co.bootpay.android.models.BootExtra
import kr.co.bootpay.android.models.BootItem
import kr.co.bootpay.android.models.BootUser
import kr.co.bootpay.android.models.Payload
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

class ProductFragment : BaseFragment<ProductViewModel>() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun provideLayoutId(): Int = R.layout.fragment_product

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {
        _binding = FragmentProductBinding.bind(view)

        binding.btnPayment.setOnClickListener {
            goRequest()
        }

        binding.ibNumAdd.setOnClickListener {
            val num = binding.tvProductNum.text.toString().toInt() + 1
            binding.tvProductNum.text = num.toString()
            setPrice(num)
        }

        binding.ibNumMinus.setOnClickListener {
            val num = binding.tvProductNum.text.toString().toInt()
            if (num > 1) {
                binding.tvProductNum.text = (num - 1).toString()
                setPrice(num - 1)
            }
        }
    }

    fun setPrice(num: Int) {
        val dec = DecimalFormat("#,###")
        binding.tvProductPrice.text = dec.format(viewModel.productInfo.value!!.price * num) + "원"

    }

    override fun setUpObserver() {
        super.setUpObserver()

        viewModel.productInfo.observe(this) {
            if (it != null) {
                binding.tvProductName.text = it.name
                Glide
                    .with(binding.ivProductThumbnail.context)
                    .load(it.thumbnail)
                    .into(binding.ivProductThumbnail)
                val dec = DecimalFormat("#,###")
                binding.tvProductPrice.text = dec.format(it.price) + "원"
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun goRequest() {

        if (viewModel.productInfo.value == null) {
            return
        }

        val product = viewModel.productInfo.value!!
        val number = binding.tvProductNum.text.toString().toInt()
        val extra = BootExtra()
            .setCardQuota("0,2,3") // 일시불, 2개월, 3개월 할부 허용, 할부는 최대 12개월까지 사용됨 (5만원 이상 구매시 할부허용 범위)
        val items: MutableList<BootItem> = ArrayList()
        val item1 = BootItem().setName(product.name).setId("ITEM_CODE_MOUSE")
            .setQty(number).setPrice(product.price.toDouble())
        items.add(item1)
        val payload = Payload()

        payload.setApplicationId(PAYMENT_APPLICATION_ID)
            .setOrderName(product.name)
            .setOrderId("1234")
            .setPrice(product.price.toDouble() * number)
            .setUser(getBootUser())
            .setExtra(extra).items = items

        val map: MutableMap<String, Any> = HashMap()
        map["1"] = "abcdef"
        map["2"] = "abcdef55"
        map["3"] = 1234
        payload.metadata = map
        Bootpay.init(parentFragmentManager, context)
            .setPayload(payload)
            .setEventListener(object : BootpayEventListener {
                override fun onCancel(data: String) {
                    Log.d("bootpay", "cancel: $data")

                }

                override fun onError(data: String) {
                    Log.d("bootpay", "error: $data")
                }

                override fun onClose(data: String) {
                    Log.d("bootpay", "close: $data")
                    Bootpay.removePaymentWindow()
                }

                override fun onIssued(data: String) {
                    Log.d("bootpay", "issued: $data")
                }

                override fun onConfirm(data: String): Boolean {
                    Log.d("bootpay", "confirm: $data")
                    //                        Bootpay.transactionConfirm(data); //재고가 있어서 결제를 진행하려 할때 true (방법 1)
                    return true //재고가 있어서 결제를 진행하려 할때 true (방법 2)
                    //                        return false; //결제를 진행하지 않을때 false
                }

                override fun onDone(data: String) {
                    Log.d("done", data)
                }
            }).requestPayment()
    }

    fun getBootUser(): BootUser? {
        val userId = "123411bd4ss121"
        val user = BootUser()
        user.id = userId
        user.area = "서울"
        user.gender = 1 //1: 남자, 0: 여자
        user.email = "test1234@gmail.com"
        user.phone = "010-1234-4567"
        user.birth = "1988-06-10"
        user.username = "홍길동"
        return user
    }

}