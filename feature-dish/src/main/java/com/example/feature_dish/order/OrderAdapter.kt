package com.example.feature_dish.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data_dish.dto.Dish
import com.example.feature_components.extensions.loadImage
import com.example.feature_components.listener.OnInteractionListener
import com.example.feature_dish.DishDiffCallback
import com.example.feature_dish.databinding.CardOrderBinding

class OrderAdapter(
    private val onInteractionListener: OnInteractionListener<Dish>
) : ListAdapter<Dish, OrderViewHolder>(DishDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = CardOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class OrderViewHolder(
    private val binding: CardOrderBinding,
    private val onInteractionListener: OnInteractionListener<Dish>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dish: Dish) {
        binding.apply {
            name.text = dish.name

            if (dish.image_url.isNullOrEmpty())
                image.loadImage()
            else
                image.loadImage(dish.image_url!!)

            price.text = dish.price.toString() + " Р"
            weight.text = dish.weight.toString() + "г"
            amount.text = dish.amount.toString()

            add.setOnClickListener {
                onInteractionListener.onAdd(dish)
            }

            remove.setOnClickListener {
                onInteractionListener.onRemove(dish)
            }
        }
    }

}