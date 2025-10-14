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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.exam.seven.application.Screen
import com.exam.seven.application.dialog.CustomMessageDialog
import com.exam.seven.mvvm.viewModel.RegisterUserViewModel
import com.exam.seven.ui.theme.PrimaryBlueColor

@Composable
fun RegisterPage(
    navController: NavController,
    registerUserViewModel: RegisterUserViewModel
) {
    BackHandler { }
    if (registerUserViewModel.registerSuccess.value) {
        navController.navigate(Screen.LoginPage.route)
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
                "Create Account",
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
                "Create account for a zip of coffee",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            )
            OutlinedTextField(
                value = registerUserViewModel.userName.value,
                onValueChange = {
                    registerUserViewModel.userNameChanged(it)
                },
                label = {
                    Text("Username")
                },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )
            OutlinedTextField(
                value = registerUserViewModel.password.value,
                onValueChange = {
                    registerUserViewModel.passwordChanged(it)
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
                    .height(5.dp)
            )
            OutlinedTextField(
                value = registerUserViewModel.confirmPassword.value,
                onValueChange = {
                    registerUserViewModel.confirmPasswordChanged(it)
                },
                label = {
                    Text("Confirm Password")
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
                    )
                    .shadow(
                        8.dp
                    ),
                onClick = {
                    registerUserViewModel.registerUser()
//                    navController.navigate(Screen.LoginPage.route)
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonColors(
                    containerColor = PrimaryBlueColor,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Black
                )
            ) {
                Text(
                    "Sign up",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Screen.LoginPage.route)
                }
            ) {
                Text(
                    "Already have account",
                    style = TextStyle(
                        color = Color.Black
                    )
                )
            }
        }
        AnimatedVisibility(
            visible = registerUserViewModel.showDialog.value,
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
                message = registerUserViewModel.message.value,
                isSuccess = registerUserViewModel.status.value,
            )
        }
    }
}