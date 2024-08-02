package com.keygeldi.kisileruygulamas1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygeldi.kisileruygulamas1.data.entity.User
import com.keygeldi.kisileruygulamas1.room.UserDao
import kotlinx.coroutines.launch

class RegisterViewModel(private val userDao: UserDao) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    // Kayıt işlemi sonucu
    private val _registrationResult = MutableLiveData<Boolean>()
    val registrationResult: LiveData<Boolean> get() = _registrationResult

    // Hata mesajı
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Kullanıcı adı, şifre güncelleyen fonksiyonlar
    fun setUsername(name: String) {
        _username.value = name
    }

    fun setPassword(pass: String) {
        _password.value = pass
    }

    // Kayıt işlemi yapan fonksiyon
    fun register() {
        val name = _username.value ?: return
        val pass = _password.value ?: return
        val user = User(username = name, password = pass)
        viewModelScope.launch {
            try {
                userDao.insertUser(user)
                _registrationResult.value = true
            } catch (e: Exception) {
                _registrationResult.value = false
                _errorMessage.value = "Kayıt sırasında bir hata oluştu: ${e.message}"
            }
        }
    }
}
