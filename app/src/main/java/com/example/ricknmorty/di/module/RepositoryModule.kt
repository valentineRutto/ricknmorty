package com.example.ricknmorty.di.module

import com.example.ricknmorty.model.CharacterRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        CharacterRepository(get())
    }
}