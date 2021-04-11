package com.example.ricknmorty.model

import com.example.ricknmorty.model.api.ApiHelper
import com.example.ricknmorty.model.data.CharacterResponse
import com.example.ricknmorty.model.data.CharacterUiData
import com.example.ricknmorty.utils.NetworkResult
import com.example.ricknmorty.utils.Resource
import com.example.ricknmorty.utils.safeApiCall
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class CharacterRepository(private val apiHelper: ApiHelper) {

    suspend fun getCharacters(): List<CharacterUiData>? {
        return apiHelper.getCharacters().body()?.results?.map { character ->
            CharacterUiData(
                character?.id.toString(),
                character?.name,
                character?.status,
                character?.species,
                character?.image,
                character?.episode?.size.toString(),
                character?.origin?.name
            )
        }
    }
    suspend fun getCharactersResource(): CharacterResponse? {
        return apiHelper.getCharacters().body()
    }



        suspend fun getCharactersBasic(): CharacterResponse? {
        return try {
            val response = apiHelper.getCharacters()
            if (response.isSuccessful) {
                response.body()
            } else {
              //  "Failed to get workorder sla time to completion"
                null
            }
        } catch (t: Throwable) {
       //throw error
        null
        }
    }


    suspend fun getCharacterSafeApiCall(): NetworkResult<CharacterResponse?>{
        return safeApiCall(Dispatchers.IO){
            apiHelper.getCharacters().body()
        }
    }

}
