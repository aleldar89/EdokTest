package com.example.feature_dish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.core.dto.Dish
import com.example.core.listener.OnInteractionListener
import com.example.core.utils.StringArg
import com.example.feature_dish.databinding.FragmentDishBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishFragment : DialogFragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: MenuViewModel by activityViewModels()
    private val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDishBinding.inflate(inflater, container, false)
        val dish: Dish = arguments?.textArg.let { gson.fromJson(it, Dish::class.java) }

        val dishViewHolder = DishViewHolder(
            binding,
            object : OnInteractionListener<Dish> {
                //todo первое добавление не меняет сумму
                override fun onAdd(item: Dish) {
                    viewModel.orderPlus(item)
                }
            }
        )

        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }

        dishViewHolder.bind(dish)

        return binding.root
    }

}