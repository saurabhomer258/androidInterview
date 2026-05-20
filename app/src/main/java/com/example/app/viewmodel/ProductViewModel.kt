package com.example.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.NetworkServices
import com.example.app.common.UiState
import com.example.app.data.Product
import com.example.app.data.remote.ProductApi
import com.example.app.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository = ProductRepository(NetworkServices().api)
)  : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val state : StateFlow<UiState<List<Product>>> = _state
    
    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            repository.getProducts().onSuccess {
                _state.value = UiState.Success(it)
            }
                .onFailure {
                    _state.value = UiState.Error(it.message ?:  "some thing went wrong")
                }
            
        }
    }
}