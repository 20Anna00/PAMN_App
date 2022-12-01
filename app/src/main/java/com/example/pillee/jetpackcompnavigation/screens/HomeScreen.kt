package com.example.pillee.jetpackcompnavigation.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.themes.background_color
import com.example.pillee.themes.home_grey
import com.example.pillee.themes.home_red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold() {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color),
        verticalArrangement = Arrangement.spacedBy(
            space = 40.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterVertically,
        ),
                horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(id = R.drawable.pillelogo),
                contentDescription = "Pill",
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
            )
            Text(
                "Pillee",
                color = Color.Black,
                fontSize = 50.sp,

                )
        }
        RegisterButtonNew(navController)
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 5.dp,
                alignment = Alignment.CenterVertically,
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Already have an account?",
                color = home_grey,
                fontSize = 15.sp,
            )
            LoginButtonNew(navController)
        }
    }
}
@Composable
fun LoginButton(navController: NavController){
    Button(
        onClick = {
            navController.navigate(route = AppScreens.LoginScreen.route)
        },
        colors = ButtonDefaults.buttonColors( Color.Transparent),
        modifier = Modifier
            .width(250.dp)
            .height(50.dp)
            .border(1.dp, shape = RoundedCornerShape(20.dp), color = Color(46, 104, 117))

    ) { Text("Log In", color = Color.White) }
}

@Composable
fun LoginButtonNew(navController: NavController){
    Button(
        onClick = {
            navController.navigate(route = AppScreens.LoginScreen.route)
        },
        colors = ButtonDefaults.buttonColors( Color.Transparent),
        border = BorderStroke(0.dp, Color.Transparent),

        modifier = Modifier
            .width(250.dp)
            .height(50.dp)



    ) { Text("Log In", fontSize = 20.sp, color = home_grey) }
}

@Composable
fun RegisterButton(navController: NavController){
    Button(
        onClick = {
            navController.navigate(route = AppScreens.RegisterScreen.route)
        }, colors = ButtonDefaults.buttonColors( Color.White),
        modifier = Modifier
            .width(250.dp)
            .height(50.dp)

   ) { Text("Register", color = Color.Black) }
}

@Composable
fun RegisterButtonNew(navController: NavController){
    Button(
        onClick = {
            navController.navigate(route = AppScreens.RegisterScreen.route)
        }, colors = ButtonDefaults.buttonColors(home_red),
        modifier = Modifier
            .width(280.dp)
            .height(100.dp)

    ) {
        Text(
            "Get Started",
            color = Color.White,
            fontSize = 30.sp,

        )
    }
}

@Composable
fun WelcomeText (){
    Text(
        "The app that helps you to not forget your pills",
        color = Color.White,
        fontSize = 30.sp,
        modifier = Modifier
            .width(250.dp)
    )
}
@Preview
@Composable
fun PreviewHome(){
    BodyContent(rememberNavController())
}



