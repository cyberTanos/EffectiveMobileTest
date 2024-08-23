package com.example.auth.login

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.R
import utlil.ResourceProvider

class LoginVM(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> = _isButtonEnabled

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val email = MutableLiveData<String>()

    fun checkEmail(email: String) {
        _isButtonEnabled.value = email.isNotBlank()
        this.email.value = email
    }

    fun canNavigate(): Boolean {
        _error.value = if (email.value?.matches(EMAIL_ADDRESS.toRegex()) != true) resourceProvider.getString(R.string.wrong_email)
        else ""
        return error.value?.isEmpty() == true
    }

    fun clearError() {
        _error.value = ""
    }
}
