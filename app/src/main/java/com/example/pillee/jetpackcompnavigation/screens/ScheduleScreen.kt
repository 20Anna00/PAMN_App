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
import java.util.*

/*@AndroidEntryPoint
@ExperimentalCoroutinesApi
class ScheduleScreenActivity: AppCompatActivity(){
    private val viewModel: PillDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dataOrException = viewModel.data.value
        }
    }
}*/

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
    var list = arrayOf( "nothing", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    var morningPills = mutableListOf<Pills>()
    var afternoonPills = mutableListOf<Pills>()
    var eveningPills = mutableListOf<Pills>()


   if (listPills != null) {
        for (pill in listPills){
            if(pill.days == list[currentDay]){
                pills.add(pill)
            }
        }
    }
    var splitHour = currentTime.split(":")
    var time = splitHour[0] + splitHour[1]


    for (pill in pills){
        var splitHourPill = pill.hour.split(":")
        var timePill = splitHourPill[0] + splitHourPill[1]
        var timeRealPill = timePill.toInt()
        if(timeRealPill <= 1100){
            morningPills.add(pill)
        }
        if(timeRealPill > 1100 && timeRealPill <= 1600) {
            afternoonPills.add(pill)
        }
        if (timeRealPill > 1600) {
            eveningPills.add(pill)
        }
        Log.d(TAG, "Name of pill: ${ pill.name }")
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

        if (appointmentList != null) {
            for (app in appointmentList){
                //showAppointment(datetime = app.dateTime, hospital = app.hospital)
            }
        }
        for (pill in morningPills){
            Log.d(TAG, pill.name)
            showPill(pill.name, pill.days, pill.hour)
        }
        //showPill("namejfij", "days", "08:30")
        //showPill("namejfij", "days", "23:50")


        Text(text = "Afternoon", color = Color.Black, fontSize = 30.sp)
        for (pill in afternoonPills){
            showPill(pill.name, pill.days, pill.hour)
        }
        Text(text = "Evening", color = Color.Black, fontSize = 30.sp)
        for (pill in eveningPills){
            showPill(pill.name, pill.days, pill.hour)
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
    


