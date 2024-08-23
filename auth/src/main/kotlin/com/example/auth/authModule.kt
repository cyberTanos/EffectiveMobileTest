package com.example.auth

import com.example.auth.login.LoginVM
import com.example.auth.pin.PinVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { LoginVM(get()) }
    viewModel { PinVM() }
}
