package com.example.auth.pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val PIN_LENGTH = 4

class PinVM : ViewModel() {

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    fun changePin(pin: String) {
        _isButtonEnabled.value = pin.length == PIN_LENGTH
    }
}
