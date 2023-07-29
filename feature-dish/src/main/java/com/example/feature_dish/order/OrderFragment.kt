package com.example.feature_dish.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data_dish.dto.Dish
import com.example.feature_components.base_fragment.BaseFragment
import com.example.feature_components.listener.OnInteractionListener
import com.example.feature_components.utils.StringArg
import com.example.feature_components.utils.getFormattedDate
import com.example.feature_dish.databinding.FragmentOrderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : BaseFragment() {

    private val datePattern = "d MMMM, yyyy"

    private val viewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOrderBinding.inflate(inflater, container, false)

        val orderAdapter = OrderAdapter(
            object : OnInteractionListener<Dish> {
                override fun onAdd(item: Dish) {
                    viewModel.plusDishToOrder(item)
                }

                override fun onRemove(item: Dish) {
                    viewModel.minusDishFromOrder(item)
                }
            }
        )

        binding.apply {
//            viewModel.location.observe(viewLifecycleOwner) {
//                location.text = "${it.latitude}, ${it.longitude}"
//            }
            date.text = getFormattedDate(datePattern)
            list.adapter = orderAdapter
            list.layoutManager = LinearLayoutManager(context)
        }

        viewModel.localData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                viewModel.nullifyOrderSum()
            }
            orderAdapter.submitList(it)
        }

        viewModel.orderSum.observe(viewLifecycleOwner) { sum ->
            binding.apply {
                if (sum > 0) {
                    orderButton.text = "Оплатить $sum Р"
                    orderButton.isVisible = true
                } else
                    orderButton.isGone = true
            }
        }

        return binding.root
    }

}