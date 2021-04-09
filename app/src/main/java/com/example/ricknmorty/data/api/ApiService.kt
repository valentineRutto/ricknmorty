package com.example.ricknmorty.data.api

import com.example.ricknmorty.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterResponse>

}

