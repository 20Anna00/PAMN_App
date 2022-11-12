package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    Scaffold {
        LoginBodyContent(navController)
    }
}

@Composable
fun LoginBodyContent(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTextField(name = "Email")
        LoginTextField(name = "Password")
        loginButton(navController)

    }
}

@Composable
fun loginButton(navController: NavController) {
    Button(onClick = {
        navController.navigate(route = AppScreens.StartPageScreen.route)
    }) {
        Text("Log In")
    }
}

@Composable
fun LoginTextField(name: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = name) }
    )
}



@Preview
@Composable
fun loginPreview(){
    LoginBodyContent(rememberNavController())
}
