package com.example.feature_dish

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.dto.Category
import com.example.core.dto.Dish
import com.example.core.listener.OnInteractionListener
import com.example.core.utils.StringArg
import com.example.feature_dish.adapter.MenuAdapter
import com.example.feature_dish.adapter.TegAdapter
import com.example.feature_dish.databinding.FragmentMenuBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)
        val gson = Gson()
        val category = arguments?.textArg.let { gson.fromJson(it, Category::class.java) }

        val menuAdapter = MenuAdapter(
            object : OnInteractionListener<Dish> {
                override fun onSelect(item: Dish) {
                    findNavController().navigate(
                        R.id.action_menuFragment_to_dishFragment,
                        Bundle().apply {
                            textArg = gson.toJson(item)
                        }
                    )
                }
            }
        )

        val tegAdapter = TegAdapter(
            object : OnInteractionListener<String> {
                override fun onSelect(item: String) {
                    viewModel.filterMenu(item)
                }
            }
        )

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.category.text = category.name

        binding.list.apply {
            adapter = menuAdapter
            layoutManager = GridLayoutManager(context,3)
        }

        binding.tagList.apply {
            adapter = tegAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.menuData.observe(viewLifecycleOwner) {
            menuAdapter.submitList(it)
        }

        viewModel.tegs.observe(viewLifecycleOwner) {
            tegAdapter.submitList(it)
        }

        return binding.root
    }

}