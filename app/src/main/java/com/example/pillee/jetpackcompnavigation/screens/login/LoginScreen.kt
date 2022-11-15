package com.example.pillee.jetpackcompnavigation.screens.login

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.screens.login.LoginUiState
import com.example.pillee.jetpackcompnavigation.screens.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    auth: FirebaseAuth,
    loginViewModel: LoginViewModel? = viewModel()
) {
    val loginUiState = loginViewModel?.loginUiState
    val context = LocalContext.current
    Scaffold {
        LoginBodyContent(navController, loginUiState, loginViewModel, context)
    }
}

@Composable
fun LoginBodyContent(
    navController: NavController,
    loginUiState: LoginUiState?,
    loginViewModel: LoginViewModel?,
    context: Context){

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

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
        EmailTextField(loginUiState, loginViewModel )
        PasswordTextField(loginUiState, loginViewModel)
        loginButton(navController, loginViewModel, loginUiState, context)

    }
}

@Composable
fun loginButton(navController: NavController, loginViewModel: LoginViewModel?, loginUiState: LoginUiState?, context: Context) {
    Button(onClick = {
        loginViewModel?.loginUser(context, navController)
    }, colors = ButtonDefaults.buttonColors( Color(46, 104, 117)),
        modifier = Modifier
            .width(280.dp)
            .height(50.dp)
    ) { Text("Log In", color = Color.White) }
}

@Composable
fun EmailTextField(loginUiState: LoginUiState?, loginViewModel: LoginViewModel?) {

    OutlinedTextField(
        value = loginUiState?.email ?: "",
        singleLine = true,
        onValueChange = { loginViewModel?.onEmailChange(it) },
        label = { Text(text = "Email") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(46, 104, 117),
            unfocusedBorderColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color(46, 104, 117)
        )

    )
}
@Composable
fun PasswordTextField(loginUiState: LoginUiState?, loginViewModel: LoginViewModel?){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = loginUiState?.password?:"",
        singleLine = true,
        onValueChange = { loginViewModel?.onPasswordChange(it) },
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

fun logService(email: String, password: String){

}


@Preview
@Composable
fun loginPreview(){

}
