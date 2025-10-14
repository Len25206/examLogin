package com.exam.seven.mvvm.viewModel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exam.seven.mvvm.model.User
import com.exam.seven.mvvm.repository.UserRepository
import com.exam.seven.mvvm.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository,
    private val loginState: LoginState
) : ViewModel() {
    val showDialog: State<Boolean> = loginState.showDialog
    val message: State<String> = loginState.message
    val status: State<Boolean> = loginState.status
    val userName: State<String> = loginState.userName
    val password: State<String> = loginState.password
    val userList = repository.getUser()
    val loginSuccess: State<Boolean> = loginState.loginSuccess
    var user: User? = null
    var showPassword: State<Boolean> = loginState.showPassword



    fun onShowDialog(showDialog: Boolean, message: String? = null, status: Boolean = false) {
        loginState.showDialog.value = showDialog
        loginState.message.value = message ?: ""
        loginState.status.value = status

        if (showDialog) {
            viewModelScope.launch {
                delay(2000)
                loginState.showDialog.value = false
                loginState.message.value = ""
                loginState.status.value = false
            }
        }
    }

    fun userNameChanged(userName: String) {
        loginState.userName.value = userName
    }

    fun passwordChanged(password: String) {
        loginState.password.value = password
    }

    fun login() {
        val userName = loginState.userName.value
        val password = loginState.password.value
        if (userName.isEmpty() && password.isEmpty()) {
            onShowDialog(
                true,
                "Username and password is empty",
                false
            )
            return
        }
        if (userName.isEmpty()) {
            onShowDialog(
                true,
                "Username is empty",
                false
            )

            return
        }
        if (password.isEmpty()) {
            onShowDialog(
                true,
                "Password is empty",
                false
            )
            return
        }
        user = userList.find { it.userName == userName && it.password == password }
        if (user != null) {
            onShowDialog(
                true,
                "Login success",
                true
            )
            loginState.userName.value = ""
            loginState.password.value = ""
            loginState.loginSuccess.value = true

            viewModelScope.launch {
                delay(1000)
                loginState.userName.value = ""
                loginState.password.value = ""
                loginState.loginSuccess.value = false
            }
        } else {
            onShowDialog(
                true,
                "username or password is invalid",
                false
            )

        }

    }
    fun showPassword() {
        loginState.showPassword.value = !loginState.showPassword.value
    }


}