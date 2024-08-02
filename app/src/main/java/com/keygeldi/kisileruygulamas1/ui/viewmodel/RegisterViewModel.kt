package com.keygeldi.kisileruygulamas1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    // Kullanıcı adı, şifre  güncelleyen fonksiyonlar
    fun setUsername(name: String) {
        _username.value = name
    }

    fun setPassword(pass: String) {
        _password.value = pass
    }

    // Kayıt işlemi yapan fonksiyon
    fun register() {
        // Kayıt işlemini burada yapabilirsiniz.
        // Örneğin, kullanıcı adı ve şifre doğrulama ve kaydetme
    }
}
