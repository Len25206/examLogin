package com.exam.seven.application.page

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.trace
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.exam.seven.application.Screen
import com.exam.seven.application.dialog.CustomMessageDialog
import com.exam.seven.mvvm.model.User
import com.exam.seven.mvvm.viewModel.LoginViewModel
import com.exam.seven.ui.theme.PrimaryBlueColor

@Composable
fun LoginPage(
    navController: NavController,
    loginViewModel: LoginViewModel,
    user: (User) -> Unit
) {
    BackHandler { }
    if (loginViewModel.loginSuccess.value) {
        navController.navigate(Screen.DashboardPage.route)
        loginViewModel.user?.let {
            user(
                it
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White
                )
                .padding(
                    vertical = 20.dp,
                    horizontal = 20.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Login here",
                style = TextStyle(
                    color = PrimaryBlueColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(
                "Welcome back please enter your credentials",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            )
            OutlinedTextField(
                value = loginViewModel.userName.value,
                onValueChange = {
                    loginViewModel.userNameChanged(it)
                },
                label = {
                    Text("Username")
                },
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )
            OutlinedTextField(
                value = loginViewModel.password.value,
                onValueChange = {
                    loginViewModel.passwordChanged(it)
                },
                label = {
                    Text("Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )
            Button(
                modifier = Modifier
                    .size(
                        height = 50.dp,
                        width = 350.dp
                    ),
                onClick = {
                    loginViewModel.login()
                },
                elevation = ButtonDefaults.buttonElevation(8.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonColors(
                    containerColor = PrimaryBlueColor,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Black
                )
            ) {
                Text(
                    "Login",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Screen.RegisterPage.route)
                }
            ) {
                Text(
                    "Create new account",
                    style = TextStyle(
                        color = Color.Black
                    )
                )
            }
        }
        AnimatedVisibility(
            visible = loginViewModel.showDialog.value,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(500)
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(500)
            )
        ) {
            CustomMessageDialog(
                message = loginViewModel.message.value,
                isSuccess = loginViewModel.status.value,
            )
        }
    }
}