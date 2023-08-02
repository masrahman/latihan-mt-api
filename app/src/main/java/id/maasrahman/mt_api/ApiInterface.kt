package id.maasrahman.mt_api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products?limit=10")
    fun getProduct(): Call<ProductResponse>
}