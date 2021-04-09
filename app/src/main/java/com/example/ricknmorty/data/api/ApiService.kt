package com.example.ricknmorty.network

import com.example.ricknmorty.data.model.CharacterResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/character")
    suspend fun getAllCharacters(): CharacterResponse

}

