package com.valentinerutto.ricknmorty.di.module

import com.valentinerutto.ricknmorty.model.CharacterRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        CharacterRepository(get())
    }
}