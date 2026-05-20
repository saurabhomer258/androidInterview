package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.app.View.adapter.ProductListAdapter
import com.example.app.common.UiState
import com.example.app.data.Product
import com.example.app.data.repository.ProductRepository
import com.example.app.viewmodel.ProductListViewModelFactory
import com.example.app.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
     val repository = ProductRepository(NetworkServices().api)
    val viewModel: ProductViewModel by viewModels {
        ProductListViewModelFactory(repository)
    }
    var progessBar : ProgressBar? = null
    var recyclerView : RecyclerView? = null
    val adapter = ProductListAdapter(this) {
        product ->
        
        startActivity(Intent(this, ProductDetailActivity::class.java).apply {
            putExtra("product_id", product.id)
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    
    setContentView(R.layout.layout_product_page)
        progessBar = findViewById(R.id.progess_bar)
        recyclerView = findViewById(R.id.recycler_view)
    recyclerView?.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    state -> render(state)
                }
            }
        }
    
    }
    
    private fun render (state : UiState<List<Product>>) {
        progessBar?.visibility = if(state is UiState.Loading) View.VISIBLE else View.GONE
        recyclerView?.visibility = if(state is UiState.Success) View.VISIBLE else View.GONE
            // todo add one line for error
    }
    
    
    
    
}