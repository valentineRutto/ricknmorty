package com.example.ricknmorty.data

import com.example.ricknmorty.data.model.CharacterUiData
import com.example.ricknmorty.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val apiService: ApiService) {

    suspend fun getCharacters(): List<CharacterUiData>? = withContext(Dispatchers.IO) {

        apiService.getAllCharacters().results?.map { character ->
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
