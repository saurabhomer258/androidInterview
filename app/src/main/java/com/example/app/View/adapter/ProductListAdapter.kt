package com.example.app.View.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.app.View.ProductListViewHolder
import com.example.app.data.Product
import com.example.app.databinding.ItemProductPageBinding

class ProductListAdapter(
    private val onClick: (Product) -> Unit
) : ListAdapter<Product, ProductListViewHolder>(DiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ItemProductPageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductListViewHolder(binding, onClick)
    }
    
    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}



