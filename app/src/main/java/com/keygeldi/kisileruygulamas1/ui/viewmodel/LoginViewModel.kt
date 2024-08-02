package com.keygeldi.kisileruygulamas1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygeldi.kisileruygulamas1.room.UserDao
import kotlinx.coroutines.launch

class LoginViewModel(private val userDao: UserDao) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Giriş işlemi yapan fonksiyon
    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val user = userDao.getUserByCredentials(username, password)
                if (user != null) {
                    _loginResult.value = true
                } else {
                    _loginResult.value = false
                    _errorMessage.value = "Kullanıcı adı veya şifre yanlış"
                }
            } catch (e: Exception) {
                _loginResult.value = false
                _errorMessage.value = "Bir hata oluştu: ${e.message}"
            }
        }
    }
}
