package com.example.pillee.jetpackcompnavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pillee.jetpackcompnavigation.screens.HomeScreen
import com.example.pillee.jetpackcompnavigation.screens.LoginScreen
import com.example.pillee.jetpackcompnavigation.screens.RegisterScreen
import com.example.pillee.jetpackcompnavigation.screens.StartPageScreen

@Composable
fun AppNavigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = AppScreens.StartPageScreen.route){
            StartPageScreen(navController)
        }
    }
}