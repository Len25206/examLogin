package com.exam.seven.application.page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.exam.seven.R
import com.exam.seven.application.Screen
import com.exam.seven.ui.theme.PrimaryBlueColor


@Composable
fun StartingPage(
    navController: NavController
) {
    BackHandler { }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                )
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.mipmap.coffee),
                contentDescription = "Coffee",
            )
            Spacer(
                modifier = Modifier
                    .height(70.dp)
            )
            Text(
                "Hello Again \nZip Coffee",
                style = TextStyle(
                    color = PrimaryBlueColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                "Some days make the coffee, other days the coffee makes you",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                ),
                modifier = Modifier
                    .padding(10.dp)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(
                        horizontal = 30.dp,
                        vertical = 30.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(Screen.LoginPage.route)
                    },
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    colors = ButtonColors(
                        containerColor = PrimaryBlueColor,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Black
                    )
                ) {
                    Text(
                        "Login",
                        style = TextStyle(color = Color.White)
                    )
                }


                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(Screen.RegisterPage.route)
                    }
                ) {
                    Text(
                        "Register",
                        style = TextStyle(
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}