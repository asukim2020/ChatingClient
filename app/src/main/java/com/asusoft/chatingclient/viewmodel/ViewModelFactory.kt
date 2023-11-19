package com.asusoft.chatingclient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

@Suppress("UNCHECKED_CAST")
val ViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        with(modelClass) {
            when {
                isAssignableFrom(LoginViewModel::class.java)
                    -> LoginViewModel()

                else -> IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}