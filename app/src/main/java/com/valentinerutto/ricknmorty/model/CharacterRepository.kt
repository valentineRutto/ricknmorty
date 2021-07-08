package com.valentinerutto.ricknmorty.model

import com.valentinerutto.ricknmorty.model.api.ApiHelper
import com.valentinerutto.ricknmorty.model.data.CharacterResponse
import com.valentinerutto.ricknmorty.model.data.CharacterUiData
import com.valentinerutto.ricknmorty.utils.NetworkResult
import com.valentinerutto.ricknmorty.utils.safeApiCall
import kotlinx.coroutines.Dispatchers

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
                character?.origin?.name,
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
