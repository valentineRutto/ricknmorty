package com.example.ricknmorty.data.di.module

import com.example.ricknmorty.data.CharacterRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        CharacterRepository(get())
    }
}