package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartPageScreen(navController: NavController){
    Scaffold() {
        StartBodyContent(navController)
    }
}

@Composable
fun StartBodyContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(157, 193, 193)),
        verticalArrangement = Arrangement.spacedBy(
            space = 75.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Start page", color = Color.White, fontSize = 40.sp)
        scheduleButton(navController)
        refillButton(navController)
        appointmentButton(navController)
        configurationButton(navController)
    }
}

@Composable
fun scheduleButton(navController: NavController) {
    Button(onClick = {
        navController.navigate(route = AppScreens.ScheduleScreen.route)
    }, colors = ButtonDefaults.buttonColors( Color(46, 104, 117)),
        modifier = Modifier
            .width(280.dp)
            .height(75.dp)
    ) { Text("Schedule", color = Color.White) }
}

@Composable
fun refillButton(navController: NavController) {
    Button(onClick = {
        navController.navigate(route = AppScreens.RefillScreen.route)
    }, colors = ButtonDefaults.buttonColors( Color(46, 104, 117)),
        modifier = Modifier
            .width(280.dp)
            .height(75.dp)
    ) { Text("Refill", color = Color.White) }
}
@Composable
fun appointmentButton(navController: NavController) {
    Button(onClick = {
        navController.navigate(route = AppScreens.AppointmentScreen.route)
    }, colors = ButtonDefaults.buttonColors( Color(46, 104, 117)),
        modifier = Modifier
            .width(280.dp)
            .height(75.dp)
    ) { Text("Appointment", color = Color.White) }
}
@Composable
fun configurationButton(navController: NavController) {
    Button(onClick = {
        navController.navigate(route = AppScreens.ConfigurationScreen.route)
    }, colors = ButtonDefaults.buttonColors( Color(46, 104, 117)),
        modifier = Modifier
            .width(280.dp)
            .height(75.dp)
    ) { Text("Configuration", color = Color.White) }
}

@Preview
@Composable
fun StartPreview(){
    StartBodyContent(rememberNavController())
}