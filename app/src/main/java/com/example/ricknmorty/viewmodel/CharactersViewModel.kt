package com.example.ricknmorty.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ricknmorty.data.CharacterRepository
import com.example.ricknmorty.data.model.CharacterUiData

class CharactersViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    suspend fun rickMortyCharactersList(): List<CharacterUiData>? {
        return characterRepository.getCharacters()
    }


//
//    fun getCharacters() = liveData(Dispatchers.IO) {
//
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = characterRepository.getCharacters()))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

}