package com.example.feature_dish

import androidx.recyclerview.widget.RecyclerView
import com.example.core.dto.Dish
import com.example.core.listener.OnInteractionListener
import com.example.feature_dish.databinding.FragmentDishBinding
import glide.loadImage

class DishViewHolder(
    private val binding: FragmentDishBinding,
    private val onInteractionListener: OnInteractionListener<Dish>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dish: Dish) {
        binding.apply {
            name.text = dish.name

            if (dish.image_url == null)
                image.loadImage()
            else
                image.loadImage(dish.image_url!!)

            description.text = dish.description ?: ""
            //todo stringresourse
            price.text = dish.price.toString() + " Р"
            weight.text = dish.weight.toString() + "г"
            add.setOnClickListener {
                onInteractionListener.onAdd(dish)
            }
        }
    }

}