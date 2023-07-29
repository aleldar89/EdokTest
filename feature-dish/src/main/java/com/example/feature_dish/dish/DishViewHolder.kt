package com.example.feature_dish.dish

import androidx.recyclerview.widget.RecyclerView
import com.example.data_dish.dto.Dish
import com.example.feature_components.extensions.loadImage
import com.example.feature_components.listener.OnInteractionListener
import com.example.feature_dish.databinding.FragmentDishBinding

class DishViewHolder(
    private val binding: FragmentDishBinding,
    private val onInteractionListener: OnInteractionListener<Dish>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dish: Dish) {
        binding.apply {
            name.text = dish.name

            if (dish.image_url.isNullOrEmpty())
                image.loadImage()
            else
                image.loadImage(dish.image_url!!)

            description.text = dish.description ?: ""
            //FIXME stringresourse
            price.text = dish.price.toString() + " Р"
            weight.text = dish.weight.toString() + "г"
            add.setOnClickListener {
                onInteractionListener.onAdd(dish)
            }
        }
    }
}