package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.themes.CentralAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefillScreen(navController: NavController){
    Scaffold(topBar = { CentralAppBar(navController, "Refill", AppScreens.StartPageScreen.route) }) {

    }
}