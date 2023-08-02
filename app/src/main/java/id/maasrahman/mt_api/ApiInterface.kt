package id.maasrahman.mt_api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {
    @GET("products?limit=10")
    fun getProduct(): Call<ProductResponse>
    @POST("products/add")
    fun addProduct(@Body product: Product): Call<Product>
    @PUT("products/{id}")
    fun updateProduct(@Path("id") id: String,
                      @Body product: Product): Call<Product>
    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: String): Call<Product>

}