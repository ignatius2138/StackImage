package com.luck.ignatius.stockimageshopping.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luck.ignatius.stockimageshopping.database.CartTable
import com.luck.ignatius.stockimageshopping.databinding.CartRecyclerViewItemBinding

class CartListAdapter: ListAdapter<CartTable, CartListAdapter.ViewHolder>(CartListDiffCallback()) {

    class ViewHolder private constructor(val binding: CartRecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root){
        val cartItemImage: ImageView = binding.cartItemImageView

        fun bind(item: CartTable) {
            binding.descriptionTextViewRecycler.text = item.description
            binding.priceTextViewRecycler.text = "Price: " + item.price + " $"
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartRecyclerViewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class CartListDiffCallback: DiffUtil.ItemCallback<CartTable>() {
        override fun areItemsTheSame(oldItem: CartTable, newItem: CartTable): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: CartTable, newItem: CartTable): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}