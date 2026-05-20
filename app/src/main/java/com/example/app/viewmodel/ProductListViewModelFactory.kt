package com.example.app.viewmodel

import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.ViewModel
import com.example.app.data.repository.ProductRepository

class ProductListViewModelFactory(
    private val repository: ProductRepository
) : ViewModelProvider.Factory {
    
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            ProductViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
