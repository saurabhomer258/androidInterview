package com.example.app.data.remote

import com.example.app.data.Product
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductApi {
    
    @GET("products")
    suspend fun getProducts() : Result<List<Product>>
    
    @GET("products/{id}")
    suspend fun  getProductDetail(@Path("id") id : Int ) : Result<Product>
    
    
}