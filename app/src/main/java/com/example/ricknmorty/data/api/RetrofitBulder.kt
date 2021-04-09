package com.example.ricknmorty.data.api

import com.example.ricknmorty.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Main entry point for network access.`
 */
object RetrofitBulder {

    private const val BASE_URL = "https://rickandmortyapi.com/api"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}