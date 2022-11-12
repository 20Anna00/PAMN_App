package com.example.pillee.jetpackcompnavigation.screens

<<<<<<< HEAD:app/src/main/java/com/example/pillee/HomeScreen.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.MaterialTheme
=======
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
>>>>>>> 59e429a9c7c0bfb3bf50b9611599716f9f78dadf:app/src/main/java/com/example/pillee/jetpackcompnavigation/screens/HomeScreen.kt
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< HEAD:app/src/main/java/com/example/pillee/HomeScreen.kt
import androidx.compose.ui.graphics.Color
=======
>>>>>>> 59e429a9c7c0bfb3bf50b9611599716f9f78dadf:app/src/main/java/com/example/pillee/jetpackcompnavigation/screens/HomeScreen.kt
import androidx.compose.ui.tooling.preview.Preview
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hola que tal")
        Button(
            onClick = {
            navController.navigate(route = AppScreens.LoginScreen.route)
        }, colors = ButtonDefaults.buttonColors( Color.White)
        )

        {
            Text(
                "Log In",
                color = Color.Black


            )
        }
    }
}

@Preview
@Composable
fun PreviewHome(){
    BodyContent(rememberNavController())
}



