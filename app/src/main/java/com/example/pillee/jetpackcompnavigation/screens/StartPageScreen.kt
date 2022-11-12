package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartPageScreen(navController: NavController){
    Scaffold() {
        StartBodyContent(navController)
    }
}

@Composable
fun StartBodyContent(navController: NavController) {
    Text("Start page")
}
