package com.example.ricknmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ricknmorty.data.CharacterRepository
import com.example.ricknmorty.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    fun rickMortyCharactersList() = viewModelScope.launch {
        characterRepository.getCharacters()
    }

    fun getCharacters() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = characterRepository.getCharacters()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))
        }
    }

}