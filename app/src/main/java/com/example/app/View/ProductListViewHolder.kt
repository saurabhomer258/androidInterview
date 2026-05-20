package com.example.app.View

import androidx.recyclerview.widget.RecyclerView
import com.example.app.data.Product
import com.example.app.databinding.ItemProductPageBinding

class ProductListViewHolder(
    private val binding: ItemProductPageBinding,
    private val onClick: (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    
    fun bind(product: Product) {
        binding.tvTitle.text = product.title
        binding.tvPrice.text = "₹${product.price}"
        
        binding.root.setOnClickListener {
            onClick(product)
        }
    }
}
