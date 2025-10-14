package com.exam.seven.application

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exam.seven.application.page.Dashboard
import com.exam.seven.application.page.LoginPage
import com.exam.seven.application.page.RegisterPage
import com.exam.seven.application.page.StartingPage
import com.exam.seven.mvvm.model.User
import com.exam.seven.mvvm.viewModel.LoginViewModel
import com.exam.seven.mvvm.viewModel.RegisterUserViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val registerUserViewModel: RegisterUserViewModel = hiltViewModel()
    var user: User? = null
    MainNav.setController(navController)
    BackHandler(enabled =true   ) {
        Log.e("TAG", "MainScreen:")
    }
    NavHost(
        navController = navController,
        startDestination = Screen.StartPage.route
    ) {
        composable(Screen.StartPage.route) {
            StartingPage(
                navController
            )
        }
        composable(Screen.LoginPage.route) {
            LoginPage(
                navController,
                loginViewModel,
                user = {
                    user = it
                }
            )
        }
        composable(Screen.RegisterPage.route)
        {
            RegisterPage(
                navController,
                registerUserViewModel
            )
        }
        composable(Screen.DashboardPage.route) {
            Dashboard(
                navController,
                user
            )
        }

    }
}