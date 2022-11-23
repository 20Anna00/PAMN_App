package com.example.pillee.jetpackcompnavigation.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.themes.CentralAppBar
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(navController: NavController){
    Scaffold(topBar = { CentralAppBar(navController, "My Schedule", AppScreens.StartPageScreen.route) }) {
        MyUi(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyUi(navController: NavController){
    val sdf = SimpleDateFormat("'Today is ' dd-MM-yyyy")
    val sdf2 = SimpleDateFormat("HH:mm")
    val currentDate = sdf.format(Date())
    val currentTime = sdf2.format(Date())



    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFCFDBE1)),
        verticalArrangement = Arrangement.spacedBy(
            space = 25.dp,
            alignment = Alignment.CenterVertically ),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = currentDate, color = Color.Black, fontSize = 20.sp)
        Text(text = currentTime, color = Color.Black, fontSize = 40.sp)
        Text(text = "Morning", color = Color.Black, fontSize = 30.sp)
        showPill("Ibuprofen", "Monday", "08:30")
        Text(text = "Afternoon", color = Color.Black, fontSize = 30.sp)
        Text(text = "Evening", color = Color.Black, fontSize = 30.sp)


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showPill(name : String, day : String, hour : String) {
    var color = Color(0xFF174560)
    val checked = remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .height(75.dp)
        .width(350.dp)
        .background(color),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

    ) {
        Checkbox(checked = checked.value, onCheckedChange = {checked.value = it},
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF149274))
        )
        Text(hour, fontSize = 25.sp, color = Color.White)
        Text(name, fontSize = 30.sp, color = Color.White)
        Image(
            painter = painterResource(id = R.drawable.pills_1),
            contentDescription = "Pill",
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        )

    }
}