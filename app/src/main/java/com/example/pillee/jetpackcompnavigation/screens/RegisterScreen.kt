package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController){
    Scaffold() {
        RegisterBodyContent(navController = navController)
    }
}

@Composable
fun RegisterBodyContent(navController: NavController){
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
        RegisterTextField(name = "Email")
        RegisterPasswordField(name = "Password")
        registerButton(navController)

    }
}

@Composable
fun registerButton(navController: NavController) {
    Button(onClick = {
        navController.navigate(route = AppScreens.StartPageScreen.route)
    }, colors = ButtonDefaults.buttonColors( Color(46, 104, 117)),
        modifier = Modifier
            .width(280.dp)
            .height(50.dp)

    ) { Text("Register", color = Color.White) }
}

@Composable
fun RegisterTextField(name: String) {
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
fun RegisterPasswordField(name : String){
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
fun RegisterPreview(){
    RegisterBodyContent(rememberNavController())
}