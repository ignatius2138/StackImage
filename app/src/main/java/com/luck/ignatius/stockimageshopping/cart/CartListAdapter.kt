package com.luck.ignatius.stockimageshopping.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luck.ignatius.stockimageshopping.database.CartTable
import com.luck.ignatius.stockimageshopping.databinding.CartRecyclerViewItemBinding

class CartListAdapter(val clickListener: CartItemListener): ListAdapter<CartTable, CartListAdapter.ViewHolder>(CartListDiffCallback()) {

    class ViewHolder private constructor(val binding: CartRecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: CartTable, clickListener: CartItemListener) {
            binding.cartItem = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
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
        holder.bind(getItem(position)!!, clickListener)
    }

    class CartItemListener(val clickListener: (cartId: String) -> Unit) {
        fun onClick(cartItem: CartTable) = clickListener(cartItem.url)
    }

}