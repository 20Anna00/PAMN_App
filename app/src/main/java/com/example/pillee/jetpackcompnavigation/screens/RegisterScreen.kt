package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController){
    Scaffold() {
        RegisterBodyContent(navController = navController)
    }
}

@Composable
fun RegisterBodyContent(navController: NavController){

}