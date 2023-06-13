package com.example.feature_dish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.dto.Dish
import com.example.feature_dish.databinding.CardMenuBinding
import com.example.core.listener.OnInteractionListener
import glide.loadImage

class MenuAdapter(
    private val onInteractionListener: OnInteractionListener<Dish>
) : ListAdapter<Dish, MenuViewHolder>(DishDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = CardMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }
}

class MenuViewHolder(
    private val binding: CardMenuBinding,
    private val onInteractionListener: OnInteractionListener<Dish>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dish: Dish) {
        binding.apply {
            if (dish.image_url == null)
                image.loadImage()
            else
                image.loadImage(dish.image_url!!)

            image.setOnClickListener {
                onInteractionListener.onSelect(dish)
            }

            name.text = dish.name
        }
    }

}

class DishDiffCallback : DiffUtil.ItemCallback<Dish>() {
    override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem == newItem
    }
}