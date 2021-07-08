package com.valentinerutto.ricknmorty.di.module

import com.valentinerutto.ricknmorty.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CharactersViewModel(get())
    }
}
