package com.example.feature_dish.dish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.data_dish.dto.Dish
import com.example.feature_components.listener.OnInteractionListener
import com.example.feature_components.utils.StringArg
import com.example.feature_dish.databinding.FragmentDishBinding
import com.example.feature_dish.menu.MenuViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishFragment : DialogFragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDishBinding.inflate(inflater, container, false)
        val dish = arguments?.textArg.let { Gson().fromJson(it, Dish::class.java) }

        val viewHolder = DishViewHolder(
            binding,
            object : OnInteractionListener<Dish> {
                override fun onAdd(item: Dish) {
                    viewModel.addDishToOrder(item)
                }
            }
        )

        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }

        viewHolder.bind(dish)

        return binding.root
    }
}