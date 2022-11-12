package com.example.pillee.jetpackcompnavigation.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens

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
            .background(Color(157, 193, 193)),
        verticalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText()
        RegisterButton(navController)
        LoginButton(navController)
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

    ) {
        Text(
            "Log In",
            color = Color.White
        )
    }
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

   ) { Text(
            "Register",
            color = Color.Black
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



