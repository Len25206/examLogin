package com.exam.seven.application

sealed class Screen(val route: String) {
    object StartPage : Screen("start")
    object LoginPage : Screen("login")
    object RegisterPage : Screen("register")
    object DashboardPage: Screen("dashboard")
}
