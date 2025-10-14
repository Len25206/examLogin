package com.exam.seven.mvvm.state

import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject

class LoginState @Inject constructor() {
    val showDialog = mutableStateOf(false)
    val message = mutableStateOf("")
    val status = mutableStateOf(false)
    val userName = mutableStateOf("")
    val password = mutableStateOf("")
    val loginSuccess = mutableStateOf(false)
    val showPassword = mutableStateOf(false)
}