package com.example.ricknmorty.data

import com.example.ricknmorty.data.api.ApiHelper
import com.example.ricknmorty.data.model.CharacterUiData

class CharacterRepository(private val apiHelper: ApiHelper) {
//    = withContext(Dispatchers.IO)

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
}
