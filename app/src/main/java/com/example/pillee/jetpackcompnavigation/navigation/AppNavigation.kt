package com.example.pillee.jetpackcompnavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pillee.jetpackcompnavigation.screens.*
import com.example.pillee.jetpackcompnavigation.screens.appointment.AppointmentScreen
import com.example.pillee.jetpackcompnavigation.screens.login.LoginScreen
import com.google.firebase.auth.FirebaseAuth

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
        composable(route = AppScreens.ScheduleScreen.route){
            ScheduleScreen(navController)
        }
        composable(route = AppScreens.ConfigurationScreen.route){
            ConfigurationScreen(navController)
        }
        composable(route = AppScreens.AppointmentScreen.route){
            AppointmentScreen(navController)
        }
        composable(route = AppScreens.RefillScreen.route){
            RefillScreen(navController)
        }
        composable(route = AppScreens.AddMedicineScreen.route){
            AddMedicineScreen(navController)
        }
        composable(route = AppScreens.PillDetailScreen.route){

        }
        composable(AppScreens.RemovePillScreen.route){
            RemovePillScreen(navController = navController)
        }
    }
}