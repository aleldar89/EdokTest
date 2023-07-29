package com.example.feature_dish.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data_dish.dto.Dish
import com.example.feature_components.base_fragment.BaseFragment
import com.example.feature_components.listener.OnInteractionListener
import com.example.feature_dish.R
import com.example.feature_dish.databinding.FragmentMenuBinding
import com.example.navigation.navigate
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : BaseFragment() {

    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)
        val gson = Gson()
        val categoryName = arguments?.textArg.let { gson.fromJson(it, String::class.java) }

        val menuAdapter = MenuAdapter(
            object : OnInteractionListener<Dish> {
                override fun onSelect(item: Dish) {
                    val args = Bundle().apply {
                        textArg = gson.toJson(item)
                    }
                    navigate(R.id.action_menuFragment_to_dishFragment, args = args)
                }
            }
        )

        val tegAdapter = TegAdapter(
            object : OnInteractionListener<String> {
                override fun onSelect(item: String) {
                    viewModel.filterMenuByTeg(item)
                }
            }
        )

        binding.apply {
            back.setOnClickListener {
                findNavController().navigateUp()
            }

            category.text = categoryName

            list.apply {
                adapter = menuAdapter
                layoutManager = GridLayoutManager(context, 3)
            }

            tagList.apply {
                adapter = tegAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }

        viewModel.apply {
            menuLocalData.observe(viewLifecycleOwner) {
                menuAdapter.submitList(it)
            }
            tegs.observe(viewLifecycleOwner) {
                tegAdapter.submitList(it)
            }
        }

        return binding.root
    }

}