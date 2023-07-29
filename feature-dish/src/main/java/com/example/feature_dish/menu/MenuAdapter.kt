package com.example.feature_dish.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data_dish.dto.Dish
import com.example.feature_dish.DishDiffCallback
import com.example.feature_dish.databinding.CardMenuBinding
import com.example.feature_components.extensions.loadImage
import com.example.feature_components.listener.OnInteractionListener

class MenuAdapter(
    private val onInteractionListener: OnInteractionListener<Dish>
) : ListAdapter<Dish, MenuViewHolder>(DishDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = CardMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class MenuViewHolder(
    private val binding: CardMenuBinding,
    private val onInteractionListener: OnInteractionListener<Dish>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dish: Dish) {
        binding.apply {
            if (dish.image_url.isNullOrEmpty())
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