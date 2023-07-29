package com.example.feature_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data_category.dto.Category
import com.example.feature_category.databinding.FragmentCategoryBinding
import com.example.feature_components.base_fragment.BaseFragment
import com.example.feature_components.listener.OnInteractionListener
import com.example.feature_components.utils.getFormattedDate
import com.example.navigation.navigate
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment() {

    private val datePattern = "d MMMM, yyyy"

    private val viewModel: CategoriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val adapter = CategoryAdapter(
            object : OnInteractionListener<Category> {
                override fun onSelect(item: Category) {
                    val args = Bundle().apply {
                        textArg = Gson().toJson(item.name)
                    }
                    navigate(R.id.action_categoryFragment_to_menuFragment, args = args)
                }
            }
        )

        binding.apply {
//            viewModel.location.observe(viewLifecycleOwner) {
////                location.text = "${it.latitude}, ${it.longitude}"
//                location.text = it?.let {
//                    "${it.latitude}, ${it.longitude}"
//                }
//            }
            date.text = getFormattedDate(datePattern)
            list.adapter = adapter
            list.layoutManager = LinearLayoutManager(context)
        }

        viewModel.localData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }
}