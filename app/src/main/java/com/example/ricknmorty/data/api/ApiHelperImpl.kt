package com.example.ricknmorty.data.api


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getCharacters() = apiService.getAllCharacters()

}
