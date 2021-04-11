package com.example.ricknmorty.model.api

import com.example.ricknmorty.model.data.CharacterResponse
import com.example.ricknmorty.utils.Resource
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getCharacters() = apiService.getAllCharacters()

     suspend fun getAllCharactersUsingLibrary() : Response<CharacterResponse> {
         return  apiService.getAllCharactersUsingLibrary()

     }



}
