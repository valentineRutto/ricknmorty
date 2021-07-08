package com.valentinerutto.ricknmorty.model.api

import com.valentinerutto.ricknmorty.model.data.CharacterResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCharacters(): Response<CharacterResponse>
}