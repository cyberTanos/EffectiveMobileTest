package com.example.effectivemobiletest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.Repository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainVM(
    private val repository: Repository
) : ViewModel() {

    private val _favouriteCount = MutableLiveData<Int>()
    val favouriteCount: LiveData<Int> = _favouriteCount

    init {
        viewModelScope.launch {
            repository.getFavoritesFlow().onEach {
                _favouriteCount.value = it.size
            }.launchIn(viewModelScope)
        }
    }
}
