package com.example.pillee.jetpackcompnavigation.navigation

sealed class AppScreens (val route: String){
    object HomeScreen: AppScreens("home_screen")
    object LoginScreen: AppScreens("login_screen")
    object RegisterScreen: AppScreens("register_screen")
    object StartPageScreen: AppScreens(route = "start_page_screen")


}