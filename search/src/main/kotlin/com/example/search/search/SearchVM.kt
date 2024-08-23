package com.example.search.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.RecyclerItem
import domain.Repository
import kotlinx.coroutines.launch

class SearchVM(
    private val repository: Repository
) : ViewModel() {

    private val _vacancies = MutableLiveData<List<RecyclerItem>>()
    val vacancies: LiveData<List<RecyclerItem>> = _vacancies

    private val _recommendations = MutableLiveData<List<RecyclerItem>>()
    val recommendations: LiveData<List<RecyclerItem>> = _recommendations

    fun getVacanciesShort() {
        viewModelScope.launch {
            runCatching {
                repository.getVacanciesShort()
            }.onSuccess {
                _vacancies.value = it
            }.onFailure {
                Log.d("TAG", "$it")
            }
        }
    }

    fun getVacanciesFull() {
        viewModelScope.launch {
            runCatching {
                repository.getVacanciesFull()
            }.onSuccess {
                _vacancies.value = it
            }.onFailure {
                Log.d("TAG", "$it")
            }
        }
    }

    fun getRecommendations() {
        viewModelScope.launch {
            runCatching {
                repository.getRecommendations()
            }.onSuccess {
                _recommendations.value = it
            }.onFailure {
                Log.d("TAG", "$it")
            }
        }
    }

    fun addFavorite(id: String, isFull: Boolean) {
        viewModelScope.launch {
            repository.insertFavorite(id)
            if (isFull) getVacanciesFull() else getVacanciesShort()
        }
    }
}
