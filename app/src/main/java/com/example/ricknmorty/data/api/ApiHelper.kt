package com.example.ricknmorty.data.api

import com.example.ricknmorty.data.model.CharacterResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCharacters(): Response<CharacterResponse>
}