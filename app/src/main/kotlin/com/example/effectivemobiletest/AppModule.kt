package com.example.effectivemobiletest

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainVM(get()) }
}
