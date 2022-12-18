package com.example.pillee.jetpackcompnavigation.screens

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.model.Appointment
import com.example.pillee.jetpackcompnavigation.model.CheckedApp
import com.example.pillee.jetpackcompnavigation.model.CheckedPills
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.screens.appointment.AppointmentViewModel
import com.example.pillee.jetpackcompnavigation.screens.viewmodels.PillDetailViewModel
import com.example.pillee.themes.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


var pillList = mutableListOf<CheckedPills>()
var appList = mutableListOf<CheckedApp>()


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(navController: NavController, pillViewModel: PillDetailViewModel = viewModel(), appointmentViewModel: AppointmentViewModel = viewModel()){
    Scaffold(topBar = { CentralAppBar(navController, "My Schedule", AppScreens.StartPageScreen.route) }) {
        MyUi(navController, pillViewModel, appointmentViewModel)
    }
}

@SuppressLint("SuspiciousIndentation")
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
    var list = arrayOf("nothing","sunday","monday","tuesday","wednesday","thursday","friday","saturday")
    var morningPills = mutableListOf<CheckedPills>()
    var morningString = mutableListOf<String>()
    var afternoonPills = mutableListOf<CheckedPills>()
    var afternoonString = mutableListOf<String>()
    var eveningPills = mutableListOf<CheckedPills>()
    var eveningString = mutableListOf<String>()



    var currentsplitHour = currentTime.split(":")
    var currenttime = currentsplitHour[0] + currentsplitHour[1]
    var currenttimeReal = currenttime.toInt()


    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var todaysDate = LocalDate.now().format(formatter).toString()

    var todaysMorningAppointments = mutableListOf<CheckedApp>()

    var todaysAfternoonAppointments = mutableListOf<CheckedApp>()

    var todaysEveningAppointments = mutableListOf<CheckedApp>()


    if (appointmentList != null){
        for (app in appointmentList){
            var checkedapp = CheckedApp(app, remember{ mutableStateOf(false)})
            var bool = false
            for (pill in pillList) {
                if (appsEqual(app, checkedapp)) {
                    bool = true
                    break
                }
            }
            if(!bool){
                appList.add(checkedapp)
            }
        }
    }

    for (appcheck in appList){
        var app = appcheck.app
        val splitHour = app.dateAndTime.split(" ")

        var needed = splitHour[0]

        if (needed == todaysDate){

            val time = splitHour[1]
            val timeSplit = time.split(":")
            val timeSplita = timeSplit[0] + timeSplit[1]
            var timeReal = timeSplita.toInt()
            if (timeReal < 1100){
                todaysMorningAppointments.add(appcheck)
            }
            else if (timeReal > 1100 && timeReal < 1600){
                todaysAfternoonAppointments.add(appcheck)
            } else {
                todaysEveningAppointments.add(appcheck)
            }
        }
    }


    var counter = -1
    var splitHour = currentTime.split(":")
    var time = splitHour[0] + splitHour[1]
    var neededHour = "0:0"
    if (listPills != null) {
        for (pill in listPills) {
            var i = 0
            var checkedpill = CheckedPills(pill, remember{ mutableStateOf(false)})
            var bool = false
            for (pill in pillList) {
                if (pillsEqual(pill, checkedpill)) {
                    bool = true
                    break
                }
            }
            if(!bool){
                pillList.add(checkedpill)
            }
        }
    }
    for (checkpill in pillList){
        var pill = checkpill.pill
        counter = -1
        var splitDays = pill.days.split(", ")
        var neededHourSplit = pill.hour.split(",")
        for(day in splitDays){
            if (day == list[currentDay]){
                for (i in 0..neededHourSplit.size-1) {

                        neededHour = neededHourSplit[i]

                        var splitHourPill = neededHour.split(":")
                        var timePill = splitHourPill[0] + splitHourPill[1]
                        var timeRealPill = timePill.toInt()


                        if(timeRealPill <= 1100){
                            morningPills.add(checkpill)
                            morningString.add(neededHour)
                        }
                        if(timeRealPill > 1100 && timeRealPill <= 1600) {
                            afternoonPills.add(checkpill)
                            afternoonString.add(neededHour)
                        }
                        if (timeRealPill > 1600) {
                            eveningPills.add(checkpill)
                            eveningString.add(neededHour)
                        }
                    }
                }
            }
        }



    sort(morningPills, morningString)
    sort(afternoonPills, afternoonString)
    sort(eveningPills, eveningString)


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


        if (appointmentList != null) {
            for (app in appointmentList) {
                //showAppointment(datetime = app.dateTime, hospital = app.hospital)
            }
        }


        for (app in todaysMorningAppointments){
            showAppointment(app)
        }

        var i = 0
        while (i <= morningPills.size-1){
            showPill(morningPills[i], morningString[i], pillViewModel)
            i++
        }


        Text(text = "Afternoon", color = Color.Black, fontSize = 30.sp)
        for (app in todaysAfternoonAppointments){
            showAppointment(app)
        }
        var j = 0

        while (j < afternoonPills.size-1){
            showPill(afternoonPills[j], afternoonString[j], pillViewModel)
        }

        Text(text = "Evening", color = Color.Black, fontSize = 30.sp)
        for (app in todaysEveningAppointments){
            showAppointment(app)
        }

        var l = 0
        while (l <= eveningPills.size-1){
            showPill(eveningPills[l], eveningString[l], pillViewModel)

            l++
        }


    }
}


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showPill(checkedpill : CheckedPills, hour : String, pillViewModel: PillDetailViewModel) {
    var name = checkedpill.pill.name
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


    var color = if(!checkedpill.checked.value) schedule_blue else schedule_green
    var colorUn = if(currenttimeReal < timeReal && !checkedpill.checked.value) schedule_blue else if (checkedpill.checked.value) schedule_green else schedule_red
    var hourId = if(timeReal < 1200) "a.m." else "p.m."

    var refillPillsInt = checkedpill.pill.daysRefill.toInt()
    var newNumberInt = refillPillsInt - 1
    var newNumber = newNumberInt.toString()

    Row(modifier = Modifier
        .clip(shape = RoundedCornerShape(20.dp))
        .height(75.dp)
        .width(350.dp)
        .background(colorUn),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

        ) {
        Checkbox(checked = checkedpill.checked.value, onCheckedChange = { checkedpill.checked.value = it
            pillViewModel.updatePill(checkedpill.pill.id, newNumber)},
            colors = CheckboxDefaults.colors(checkedColor = if(!checkedpill.checked.value) schedule_blue else schedule_green, checkmarkColor = schedule_lightgreen)
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
fun showAppointment(checkedapp: CheckedApp) {
    var datetime = checkedapp.app.dateAndTime
    var hospital = checkedapp.app.hospital

    var splitHour = datetime.split(" ")
    var time = splitHour[1]
    var timeSplit = time.split(":")
    var timeSplita = timeSplit[0] + timeSplit[1]
    var timeReal = timeSplita.toInt()

    val sdf2 = SimpleDateFormat("HH:mm")
    val currentTime = sdf2.format(Date())


    var currentsplitHour = currentTime.split(":")
    var currenttime = currentsplitHour[0] + currentsplitHour[1]
    var currenttimeReal = currenttime.toInt()

    var color = if(!checkedapp.checked.value) schedule_blue else schedule_green
    var colorUn = if(currenttimeReal < timeReal && !checkedapp.checked.value) schedule_blue else if (checkedapp.checked.value) schedule_green else schedule_red
    var hourId = if(timeReal < 1200) "a.m." else "p.m."


    Row(modifier = Modifier
        .clip(shape = RoundedCornerShape(20.dp))
        .height(75.dp)
        .width(350.dp)
        .background(colorUn),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,

        ) {
        Checkbox(checked = checkedapp.checked.value, onCheckedChange = {checkedapp.checked.value = it},
            colors = CheckboxDefaults.colors(checkedColor = color, checkmarkColor = schedule_lightgreen)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 0.dp,
                alignment = Alignment.CenterVertically ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(time, fontSize = 25.sp, color = Color.White)
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


fun sort(pills: MutableList<CheckedPills>, hours: MutableList<String>){
    var n = pills.size
    if ( n >= 2){
        for (j in 0..n){
            var swapped = false
            for (i in 0..n-2){
                var leftP = pills[i]
                var leftHwhole = hours[i]
                var leftHSplit = leftHwhole.split(":")
                var leftHTog = leftHSplit[0] + leftHSplit[1]
                var leftH = 0
                if (leftHTog.toIntOrNull() != null) {
                    leftH = leftHTog.toInt()
                }
                var rightP = pills[i+1]
                var rightHwhole = hours[i+1]
                var rightHSplit = rightHwhole.split(":")
                var rightHTog = rightHSplit[0] + rightHSplit[1]
                var rightH = 0
                if (rightHTog.toIntOrNull() != null) {
                    rightH = rightHTog.toInt()
                }
                if (leftH > rightH) {
                    hours.set(i+1,leftHwhole)
                    pills.set(i+1, leftP)
                    hours.set(i, rightHwhole)
                    pills.set(i, rightP)
                    swapped = true
                }
            }

        }
    }
}

fun pillsEqual(pill1 : CheckedPills, pill2: CheckedPills): Boolean {
    if(pill1.pill.id == pill2.pill.id){
        return true
    }
    return false
}

fun appsEqual(app1: Appointment, appcheck: CheckedApp): Boolean{
    var app2 = appcheck.app
    if(app1.dateAndTime == app2.dateAndTime && app1.hospital == app2.hospital
        && app1.concept == app2.concept && app1.userId == app2.userId && app1.doctorName == app2.doctorName){
        return true
    }
    return false
}


