package com.valentinerutto.ricknmorty.model.api

import com.valentinerutto.ricknmorty.model.data.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterResponse>

    @GET("character")
    suspend fun getAllCharactersUsingLibrary(): Response<CharacterResponse>

}

