package com.example.favourite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
    viewModel { FavouriteVM(get()) }
}
