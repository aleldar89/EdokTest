package com.example.feature_category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data_category.dto.Category
import com.example.feature_category.databinding.CardCategoryBinding
import com.example.feature_components.base_adapter.FeedItemDiffCallBack
import com.example.feature_components.extensions.loadImage
import com.example.feature_components.listener.OnInteractionListener

class CategoryAdapter(
    private val onInteractionListener: OnInteractionListener<Category>
) : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CardCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class CategoryViewHolder(
    private val binding: CardCategoryBinding,
    private val onInteractionListener: OnInteractionListener<Category>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {

        binding.apply {
            if (category.image_url.isEmpty())
                image.loadImage()
            else
                image.loadImage(category.image_url)

            image.setOnClickListener {
                onInteractionListener.onSelect(category)
            }

            name.text = category.name
        }
    }
}

class CategoryDiffCallback : FeedItemDiffCallBack<Category>()