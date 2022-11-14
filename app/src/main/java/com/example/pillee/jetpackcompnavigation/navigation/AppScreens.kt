package com.example.pillee.jetpackcompnavigation.navigation

sealed class AppScreens (val route: String){
    object HomeScreen: AppScreens("home_screen")
    object LoginScreen: AppScreens("login_screen")
    object RegisterScreen: AppScreens("register_screen")
    object StartPageScreen: AppScreens("start_page_screen")
    object ScheduleScreen: AppScreens("schedule_screen")
    object RefillScreen: AppScreens("schedule_screen")
    object ConfigurationScreen: AppScreens("configuration_screen")
    object AppointmentScreen: AppScreens("appointment_screen")


}