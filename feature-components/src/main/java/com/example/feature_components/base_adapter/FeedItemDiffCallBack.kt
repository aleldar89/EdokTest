package com.example.feature_components.base_adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.core.FeedItem

abstract class FeedItemDiffCallBack<T: FeedItem>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}