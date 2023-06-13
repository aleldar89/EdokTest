package com.example.feature_category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.dto.Category
import com.example.core.listener.OnInteractionListener
import com.example.feature_category.databinding.CardCategoryBinding
import glide.loadImage

class CategoryAdapter(
    private val onInteractionListener: OnInteractionListener<Category>
) : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CardCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }
}

class CategoryViewHolder(
    private val binding: CardCategoryBinding,
    private val onInteractionListener: OnInteractionListener<Category>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {
        binding.apply {
            image.loadImage(category.image_url)

            image.setOnClickListener {
                onInteractionListener.onSelect(category)
            }

            name.text = category.name
        }
    }

}

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}