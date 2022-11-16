package com.example.pillee.jetpackcompnavigation.screens

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.screens.register.RegisterUiState
import com.example.pillee.jetpackcompnavigation.screens.register.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, registerViewModel: RegisterViewModel? = viewModel()){
    val registerUiState = registerViewModel?.registerUiState
    val context = LocalContext.current
    Scaffold() {
        RegisterBodyContent(navController = navController, registerUiState, registerViewModel, context)
    }
}

@Composable
fun RegisterBodyContent(navController: NavController,
                        registerUiState: RegisterUiState?,
                        registerViewModel: RegisterViewModel?,
                        context: Context
){
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
        EmailTextField(registerUiState, registerViewModel)
        PasswordTextField(registerUiState, registerViewModel)
        registerButton(navController, registerUiState, registerViewModel, context)

    }
}

@Composable
fun registerButton(navController: NavController, registerUiState: RegisterUiState?, registerViewModel: RegisterViewModel?, context: Context) {
    Button(onClick = {
        registerViewModel?.RegisterUser(context, navController)
    }, colors = ButtonDefaults.buttonColors( Color(46, 104, 117)),
        modifier = Modifier
            .width(280.dp)
            .height(50.dp)

    ) { Text("Register", color = Color.White) }
}

@Composable
fun NameTextField(registerUiState: RegisterUiState?, registerViewModel: RegisterViewModel?) {

    OutlinedTextField(
        value = registerUiState?.name ?: "",
        singleLine = true,
        onValueChange = { registerViewModel?.onNameChange(it) },
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
fun EmailTextField(registerUiState: RegisterUiState?, registerViewModel: RegisterViewModel?) {

    OutlinedTextField(
        value = registerUiState?.email ?: "",
        singleLine = true,
        onValueChange = { registerViewModel?.onEmailChange(it) },
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
fun PasswordTextField(registerUiState: RegisterUiState?, registerViewModel: RegisterViewModel?){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = registerUiState?.password?:"",
        singleLine = true,
        onValueChange = { registerViewModel?.onPasswordChange(it) },
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
}