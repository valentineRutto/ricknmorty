package com.example.ricknmorty.model.api

import com.example.ricknmorty.model.data.CharacterResponse
import com.example.ricknmorty.utils.NetworkResult
import com.example.ricknmorty.utils.Resource
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterResponse>

    @GET("character")
    suspend fun getAllCharactersUsingLibrary(): Response<CharacterResponse>

}

