package com.example.feature_order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.dto.Dish
import com.example.core.listener.OnInteractionListener
import com.example.feature_order.databinding.CardOrderBinding
import glide.loadImage

class OrderAdapter(
    private val onInteractionListener: OnInteractionListener<Dish>
) : ListAdapter<Dish, OrderViewHolder>(DishDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = CardOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }
}

class OrderViewHolder(
    private val binding: CardOrderBinding,
    private val onInteractionListener: OnInteractionListener<Dish>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dish: Dish) {
        binding.apply {
            name.text = dish.name

            if (dish.image_url == null)
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