package com.example.feature_dish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_dish.databinding.CardTegBinding
import com.example.core.listener.OnInteractionListener

class TegAdapter(
    private val onInteractionListener: OnInteractionListener<String>
) : ListAdapter<String, TegViewHolder>(TegDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TegViewHolder {
        val binding = CardTegBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TegViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: TegViewHolder, position: Int) {
        val teg = getItem(position)
        holder.bind(teg)
    }
}

class TegViewHolder(
    private val binding: CardTegBinding,
    private val onInteractionListener: OnInteractionListener<String>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String) {
        binding.apply {
            textButton.text = text
            textButton.setOnClickListener {
                onInteractionListener.onSelect(text)
            }
        }
    }

}

class TegDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}