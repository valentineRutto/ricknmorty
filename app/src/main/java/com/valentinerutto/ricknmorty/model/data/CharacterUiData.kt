package com.valentinerutto.ricknmorty.model.data

data class CharacterUiData(
    val id: String?,
    val name: String?,
    val status: String?,
    val species: String?,
    val imageUrl: String?,
    val episodeCount: String?,
    val origin: String?,
    val color: Int? = null
)
