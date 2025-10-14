package com.exam.seven.mvvm.state

import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject

class RegisterState @Inject constructor() {
    val showDialog = mutableStateOf(false)
    val message = mutableStateOf("")
    val status = mutableStateOf(false)
    val userName = mutableStateOf("")
    val password = mutableStateOf("")
    val confirmPassword = mutableStateOf("")
    val registerSuccess = mutableStateOf(false)
}