package com.example.search.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.Repository
import kotlinx.coroutines.launch

class WorkVM(
    private val repository: Repository
) : ViewModel() {

    fun addFavorite(id: String) {
        viewModelScope.launch {
            repository.insertFavorite(id)
        }
    }
}
