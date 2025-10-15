package com.exam.seven.mvvm.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exam.seven.mvvm.model.User
import com.exam.seven.mvvm.repository.UserRepository
import com.exam.seven.mvvm.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val state: RegisterState
) : ViewModel() {
    val showDialog = state.showDialog
    val message = state.message
    val status = state.status
    val userName = state.userName
    val password = state.password
    val confirmPassword = state.confirmPassword
    val userList = repository.getUser()
    val registerSuccess = state.registerSuccess
    fun showDialog(showDialog: Boolean, message: String? = null, status: Boolean = false) {
        state.showDialog.value = showDialog
        state.message.value = message ?: ""
        state.status.value = status

        if (showDialog) {
            viewModelScope.launch {
                delay(2000)
                state.showDialog.value = false
            }
        }
    }

    fun userNameChanged(userName: String) {
        state.userName.value = userName
    }

    fun passwordChanged(password: String) {
        state.password.value = password
    }

    fun confirmPasswordChanged(confirmPassword: String) {
        state.confirmPassword.value = confirmPassword
    }

    fun registerUser() {
        val userName = state.userName.value
        val password = state.password.value
        val confirmPassword = state.confirmPassword.value

        val user = userList.find { it.userName == userName }
        if (user != null) {
            Log.e("RegisterUserViewModel", "registerUser: $user")
            showDialog(
                true,
                "Username already exist",
                false
            )
            return
        }
        if (userName.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
            showDialog(
                true,
                "Username and password is empty",
                false
            )
            return
        }
        if (userName.isEmpty()) {
            showDialog(
                true,
                "Username is empty",
                false
            )
            return
        }
        if (password.isEmpty()) {
            showDialog(
                true,
                "Password is empty",
                false
            )
            return
        }
        if (confirmPassword.isEmpty()) {
            showDialog(
                true,
                "Confirm password is empty",
                false
            )
            return
        }
        if (password != confirmPassword) {
            showDialog(
                true,
                "Password and confirm password is not match",
                false
            )
            return
        }
        repository.addUser(User(userName, password))
        showDialog(
            true,
            "Register success",
            true
        )
        state.userName.value = ""
        state.password.value = ""
        state.confirmPassword.value = ""
        onRegisterSuccess(true)
        viewModelScope.launch {
            delay(2000)
            state.showDialog.value = false
         onRegisterSuccess(false)
        }

    }

    fun onRegisterSuccess(registerSuccess: Boolean) {
        state.registerSuccess.value = registerSuccess
    }
}