package com.valentinerutto.ricknmorty.model.api

import com.valentinerutto.ricknmorty.model.data.CharacterResponse
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getCharacters() = apiService.getAllCharacters()

     suspend fun getAllCharactersUsingLibrary() : Response<CharacterResponse> {
         return  apiService.getAllCharactersUsingLibrary()

     }



}
