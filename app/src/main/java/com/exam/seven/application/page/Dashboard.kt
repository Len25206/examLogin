package com.exam.seven.application.page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.exam.seven.application.Screen
import com.exam.seven.mvvm.model.User
import com.exam.seven.ui.theme.PrimaryBlueColor
import com.exam.seven.ui.theme.WhiteBackground

@Preview
@Composable
fun Dashboard(
    navController: NavController? = null,
    user: User? = null
) {
    BackHandler { }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .shadow(10.dp)
                .size(
                    width = 300.dp,
                    height = 400.dp
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(
                    horizontal = 10.dp,
                    15.dp
                )
        ) {
            Text(
                "Hi Welcome, \n${user?.userName}",
                style = TextStyle(
                    color = PrimaryBlueColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
            Text(
                "Have a sip",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                ),
                modifier = Modifier
                    .padding(10.dp)

            )

            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .size(
                        height = 50.dp,
                        width = 350.dp
                    ),
                onClick = {
                    navController?.navigate(Screen.StartPage.route)
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
                    "Back to start",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    }
}