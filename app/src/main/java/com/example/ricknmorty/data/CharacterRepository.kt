package com.example.ricknmorty.data

import com.example.ricknmorty.data.api.ApiHelper
import com.example.ricknmorty.data.model.CharacterUiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val apiHelper: ApiHelper) {

    suspend fun getCharacters(): List<CharacterUiData>? = withContext(Dispatchers.IO) {


        apiHelper.getCharacters().body()?.results?.map { character ->
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
