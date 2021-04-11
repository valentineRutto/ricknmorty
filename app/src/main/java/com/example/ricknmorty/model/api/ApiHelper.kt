package com.example.ricknmorty.model.api

import com.example.ricknmorty.model.data.CharacterResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCharacters(): Response<CharacterResponse>
}