package com.valentinerutto.ricknmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.valentinerutto.ricknmorty.model.CharacterRepository
import com.valentinerutto.ricknmorty.model.data.CharacterResponse
import com.valentinerutto.ricknmorty.model.data.CharacterUiData
import com.valentinerutto.ricknmorty.utils.NetworkResult
import com.valentinerutto.ricknmorty.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    val characterLiveData= MutableLiveData<List<CharacterUiData>>()

    suspend fun rickMortyCharactersList(): List<CharacterUiData>? {
        return characterRepository.getCharacters()
    }

    suspend fun getCharacterSafeApiCall():NetworkResult<CharacterResponse?>{
        return characterRepository.getCharacterSafeApiCall()
    }

    fun getCharacterBasicMethods() = viewModelScope.launch {
        characterRepository.getCharactersBasic()
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