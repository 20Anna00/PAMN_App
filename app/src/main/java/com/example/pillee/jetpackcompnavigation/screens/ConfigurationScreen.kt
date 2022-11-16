package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.themes.CentralAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen(navController: NavController){
    Scaffold(topBar = { CentralAppBar(navController, "Change Schedule",  AppScreens.StartPageScreen.route) })
         {
        ConfigureBodyContent(navController = navController)
    }
}

@Composable
fun ConfigureBodyContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE5E5E5)),
        verticalArrangement = Arrangement.spacedBy(
            space = 75.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         addMedicineButton(navController)
        addAppointmentButton(navController = navController)
    }
}

@Composable
fun addMedicineButton(navController: NavController) {

    Button(onClick = {
        navController.navigate(route = AppScreens.AddMedicineScreen.route)
    }, colors = ButtonDefaults.buttonColors( Color(0xFF174560)),
        modifier = Modifier
            .width(280.dp)
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF174560)),
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {
            Image(
                painter = painterResource(id = R.drawable.pills_1),
                contentDescription = "Add Medicine",
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp)
            )
            Text("Add Medicine", color = Color.White)
        }
    }
}

@Composable
fun addAppointmentButton(navController: NavController) {
    Button(onClick = {
        navController.navigate(route = AppScreens.AppointmentScreen.route)
    }, colors = ButtonDefaults.buttonColors( Color(0xFF174560)),
        modifier = Modifier
            .width(280.dp)
            .height(150.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF174560)),
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterVertically ),
            horizontalAlignment = Alignment.CenterHorizontally

        )
            {
        Image(
            painter = painterResource(id = R.drawable.stethoscope_1),
            contentDescription = "Add Appointment",
            modifier = Modifier
                .height(45.dp)
                .width(45.dp)
        )
        Text("Add a Doctors Appointment", color = Color.White)
    }
    }
}