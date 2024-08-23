package com.example.search

import com.example.search.search.SearchVM
import com.example.search.work.WorkVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchVM(get()) }
    viewModel { WorkVM(get()) }
}
