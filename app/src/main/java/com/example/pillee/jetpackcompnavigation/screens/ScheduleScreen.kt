package com.example.pillee.jetpackcompnavigation.screens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.icu.text.SimpleDateFormat
import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.model.Appointment
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.screens.appointment.AppointmentViewModel
import com.example.pillee.jetpackcompnavigation.screens.viewmodels.PillDetailViewModel
import com.example.pillee.themes.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(navController: NavController, pillViewModel: PillDetailViewModel = viewModel(), appointmentViewModel: AppointmentViewModel = viewModel()){
    Scaffold(topBar = { CentralAppBar(navController, "My Schedule", AppScreens.StartPageScreen.route) }) {
        MyUi(navController, pillViewModel, appointmentViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyUi(navController: NavController, pillViewModel: PillDetailViewModel, appointmentViewModel: AppointmentViewModel){
    val data = pillViewModel.data.value
    val dataAppointment = appointmentViewModel.data.value
    var appointmentList = dataAppointment.data
    val mCalendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("'Today is ' dd-MM-yyyy")
    val sdf2 = SimpleDateFormat("HH:mm")
    val currentDate = sdf.format(Date())
    val currentTime = sdf2.format(Date())
    val currentDay = mCalendar.get(Calendar.DAY_OF_WEEK)
    var listPills = data.data
    var pills = mutableListOf<Pills>()
    var list = arrayOf( "nothing", "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday")
    var morningPills = mutableListOf<Pills>()
    var morningString = mutableListOf<String>()
    var afternoonPills = mutableListOf<Pills>()
    var afternoonString = mutableListOf<String>()
    var eveningPills = mutableListOf<Pills>()
    var eveningString = mutableListOf<String>()



    var currentsplitHour = currentTime.split(":")
    var currenttime = currentsplitHour[0] + currentsplitHour[1]
    var currenttimeReal = currenttime.toInt()


    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val current = LocalDateTime.now().format(formatter)
    val calendar = Calendar.getInstance()

    val mYear = calendar.get(Calendar.YEAR)
    val mMonth = calendar.get(Calendar.MONTH)
    val mDay = calendar.get(Calendar.DAY_OF_MONTH)

    var todaysDate = LocalDate.now()

    var todaysAppointments = mutableListOf<Appointment>()
    var todaysMorningAppointments = mutableListOf<Appointment>()

    var todaysAfternoonAppointments = mutableListOf<Appointment>()

    var todaysEveningAppointments = mutableListOf<Appointment>()


    if (appointmentList != null){
        for (app in appointmentList){
            var timeA = Date(app.dateTime.seconds*1000)
            var timeApp =  timeA.toString()
            var splitHour = timeApp.split(" ")
            var needed = splitHour[0] + " " + splitHour[1] + " " + splitHour[2]

            if (timeA.equals(todaysDate)){
                todaysAppointments.add(app)
            }

        }
    }


    for (app in todaysAppointments){
        val timeA = Date(app.dateTime.seconds*1000)
        val timeApp =  timeA.toString()
        val splitHour = timeApp.split(" ")
        val time = splitHour[3]
        val timeSplit = time.split(":")
        val timeSplita = timeSplit[0] + timeSplit[1]
        var timeReal = timeSplita.toInt()
        if (timeReal < 1100){
            todaysMorningAppointments.add(app)
        }
         else if (timeReal > 1100 && timeReal < 1600){
            todaysAfternoonAppointments.add(app)
        } else {
            todaysEveningAppointments.add(app)
        }

    }
    var counter = -1


   /*if (listPills != null) {
        for (pill in listPills){
            var splitDays = pill.days.split(", ")
            for (day in splitDays){
                if(day == list[currentDay]){
                    pills.add(pill)
                    counter++
                    break
                }
            }

        }
    }*/
    var splitHour = currentTime.split(":")
    var time = splitHour[0] + splitHour[1]
    var neededHour = ""
        if (listPills != null) {
            for (pill in listPills){
                var splitDays = pill.days.split(", ")
                for (day in splitDays) {
                    if (day == list[currentDay]) {
                        pills.add(pill)
                        counter++
                        break
                    }
                }


            var neededHourSplit = pill.hour.split(",")

        if (counter == 0){
            neededHour = neededHourSplit[0]
        }
        if (counter == 1){
            neededHour = neededHourSplit[1]
        }
        if (counter == 2){
            neededHour = neededHourSplit[2]
        }
        var splitHourPill = neededHour.split(":")
        var timePill = splitHourPill[0] + splitHourPill[1]
        var timeRealPill = timePill.toInt()
        if(timeRealPill <= 1100){
            morningPills.add(pill)
            morningString.add(neededHour)
        }
        if(timeRealPill > 1100 && timeRealPill <= 1600) {
            afternoonPills.add(pill)
            afternoonString.add(neededHour)
        }
        if (timeRealPill > 1600) {
            eveningPills.add(pill)
            eveningString.add(neededHour)
        }
    }
        }




    Column(modifier = Modifier
        .fillMaxSize()
        .background(background_color)
        .verticalScroll(rememberScrollState())
        .padding(top = 25.dp, bottom = 25.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 25.dp,
            alignment = Alignment.CenterVertically ),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = currentDate, color = Color.Black, fontSize = 30.sp)
        Text(text = currentTime, color = Color.Black, fontSize = 50.sp)
        Text(text = "Morning", color = Color.Black, fontSize = 30.sp)

<<<<<<< HEAD
        if (appointmentList != null) {
            for (app in appointmentList){
                //showAppointment(datetime = app.dateTime, hospital = app.hospital)
=======
            for (app in todaysMorningAppointments){
                showAppointment(datetime = app.dateTime, hospital = app.hospital)
>>>>>>> 03ed28f (idk)
            }
        var i = 0
        while (i <= morningPills.size){

            showPill(morningPills[i].name, morningPills[i].days, morningString[i])
            i++
        }


        Text(text = "Afternoon", color = Color.Black, fontSize = 30.sp)
        for (app in todaysAfternoonAppointments){
            showAppointment(datetime = app.dateTime, hospital = app.hospital)
        }
        var j = 0
        while (j <= afternoonPills.size){
            showPill(afternoonPills[i].name, afternoonPills[i].days, afternoonString[i])
            j++
        }

        Text(text = "Evening", color = Color.Black, fontSize = 30.sp)
        for (app in todaysEveningAppointments){
            showAppointment(datetime = app.dateTime, hospital = app.hospital)
        }
        var l = 0
        while (l <= eveningPills.size){
            showPill(eveningPills[i].name, eveningPills[i].days, eveningString[i])
            l++
        }


    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showPill(name : String, day : String, hour : String) {
    var splitHour = mutableListOf<String>()
    var time = ""
    if( hour != ""){
        splitHour = hour.split(":") as MutableList<String>
        time = splitHour[0] + splitHour[1]
    }

    var timeReal = 0
    if(time.toIntOrNull() != null){
        timeReal = time.toInt()
    }

    val sdf2 = SimpleDateFormat("HH:mm")
    val currentTime = sdf2.format(Date())


    var currentsplitHour = currentTime.split(":")
    var currenttime = currentsplitHour[0] + currentsplitHour[1]
    var currenttimeReal = currenttime.toInt()

    val checked = remember { mutableStateOf(false) }
    var color = if(!checked.value) schedule_blue else schedule_green
    var colorUn = if(currenttimeReal < timeReal && !checked.value) schedule_blue else if (checked.value) schedule_green else schedule_red
    //color = if(currenttimeReal < timeReal) schedule_red else color
    var hourId = if(timeReal < 1200) "a.m." else "p.m."

        Row(modifier = Modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .height(75.dp)
            .width(350.dp)
            .background(colorUn),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

    ) {
        Checkbox(checked = checked.value, onCheckedChange = {checked.value = it},
            colors = CheckboxDefaults.colors(checkedColor = color, checkmarkColor = schedule_lightgreen)
        )
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 0.dp,
                    alignment = Alignment.CenterVertically ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(hour, fontSize = 25.sp, color = Color.White)
                Text(hourId, fontSize = 25.sp, color = Color.White)
            }
        Text(name, fontSize = 30.sp, color = Color.White)
        Image(
            painter = painterResource(id = R.drawable.pills_1),
            contentDescription = "Pill",
            modifier = Modifier
                .height(45.dp)
                .width(45.dp)
        )

    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showAppointment(datetime: com.google.firebase.Timestamp, hospital: String, ) {
    var timeA = Date(datetime.seconds*1000)
    var timeApp =  timeA.toString()
    var splitHour = timeApp.split(" ")
    var time = splitHour[3]
    var timeSplit = time.split(":")
    var timeSplita = timeSplit[0] + timeSplit[1]
    var timeReal = timeSplita.toInt()
    var hour = timeSplit[0] + ":" + timeSplit[1]

    val sdf2 = SimpleDateFormat("HH:mm")
    val currentTime = sdf2.format(Date())


    var currentsplitHour = currentTime.split(":")
    var currenttime = currentsplitHour[0] + currentsplitHour[1]
    var currenttimeReal = currenttime.toInt()

    val checked = remember { mutableStateOf(false) }
    var color = if(!checked.value) schedule_blue else schedule_green
    var colorUn = if(currenttimeReal < timeReal && !checked.value) schedule_blue else if (checked.value) schedule_green else schedule_red
    //color = if(currenttimeReal < timeReal) schedule_red else color
    var hourId = if(timeReal < 1200) "a.m." else "p.m."

    Row(modifier = Modifier
        .clip(shape = RoundedCornerShape(20.dp))
        .height(75.dp)
        .width(350.dp)
        .background(colorUn),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

        ) {
        Checkbox(checked = checked.value, onCheckedChange = {checked.value = it},
            colors = CheckboxDefaults.colors(checkedColor = color, checkmarkColor = schedule_lightgreen)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 0.dp,
                alignment = Alignment.CenterVertically ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(hour, fontSize = 25.sp, color = Color.White)
            Text(hourId, fontSize = 25.sp, color = Color.White)
        }
        Text(hospital, fontSize = 30.sp, color = Color.White)
        Image(
            painter = painterResource(id = R.drawable.stethoscope_1),
            contentDescription = "Pill",
            modifier = Modifier
                .height(45.dp)
                .width(45.dp)
        )

    }
}
    


