package com.example.favourite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.Repository
import kotlinx.coroutines.launch
import domain.RecyclerItem

class FavouriteVM(
    private val repository: Repository
) : ViewModel() {

    private val _vacancies = MutableLiveData<List<RecyclerItem>>()
    val vacancies: LiveData<List<RecyclerItem>> = _vacancies

    fun getFavorites() {
        viewModelScope.launch {
            runCatching {
                repository.getFavorites()
            }.onSuccess {
                _vacancies.value = it
            }.onFailure {
                Log.d("TAG", "getFavorites: $it")
            }
        }
    }

    fun addFavorite(id: String) {
        viewModelScope.launch {
            repository.insertFavorite(id)
            getFavorites()
        }
    }
}
