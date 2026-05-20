package com.example.app.data.repository

import com.example.app.data.Product
import com.example.app.data.remote.ProductApi
import retrofit2.http.GET
import retrofit2.http.Path

class ProductRepository (private val api : ProductApi) {
    
    

    suspend fun getProducts() : Result<List<Product>>  = api.getProducts()
    
    suspend fun  getProductDetail(id : Int) : Result<Product>  = api.getProductDetail(id)
}