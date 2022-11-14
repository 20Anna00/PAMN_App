package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        modifier = Modifier
            .fillMaxSize()
            .background(Color(157, 193, 193)),
        verticalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTextField(name = "Email")
        LoginPasswordField(name = "Password")
        loginButton(navController)

    }
}

@Composable
fun loginButton(navController: NavController) {
    Button(onClick = {
        navController.navigate(route = AppScreens.StartPageScreen.route)
    }, colors = ButtonDefaults.buttonColors( Color(46, 104, 117)),
        modifier = Modifier
            .width(280.dp)
            .height(50.dp)
    ) { Text("Log In", color = Color.White) }
}

@Composable
fun LoginTextField(name: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        singleLine = true,
        onValueChange = { text = it },
        label = { Text(text = name) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(46, 104, 117),
            unfocusedBorderColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color(46, 104, 117)
        )
    )
}
@Composable
fun LoginPasswordField(name : String){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        singleLine = true,
        onValueChange = { text = it },
        label = { Text(text = name) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(46, 104, 117),
            unfocusedBorderColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color(46, 104, 117)
        )
    )
}


@Preview
@Composable
fun loginPreview(){
    LoginBodyContent(rememberNavController())
}
