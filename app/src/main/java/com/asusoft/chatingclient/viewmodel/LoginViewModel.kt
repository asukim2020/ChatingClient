package com.asusoft.chatingclient.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(

): ViewModel() {

    val id = MutableLiveData<String>("")
    val pw = MutableLiveData<String>("")
    val autoLogin = MutableLiveData<Boolean>(false)

    fun login() {
        // TODO: - 개발
    }

    fun signUp() {
        // TODO: - 개발
    }
}