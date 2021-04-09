package com.example.ricknmorty.data.api

import com.example.ricknmorty.network.ApiService

class ApiHelper(private val apiService: ApiService) {

    suspend fun getCharacters() = apiService.getAllCharacters()
}