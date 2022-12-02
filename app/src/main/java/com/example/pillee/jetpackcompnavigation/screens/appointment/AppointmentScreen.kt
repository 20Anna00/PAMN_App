package com.example.pillee.jetpackcompnavigation.screens.appointment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.screens.addAppointmentButton
import com.example.pillee.jetpackcompnavigation.screens.register.RegisterUiState
import com.example.pillee.jetpackcompnavigation.screens.register.RegisterViewModel
import com.example.pillee.themes.CentralAppBar
import com.example.pillee.themes.white
import io.grpc.internal.ReadableBuffer
import java.sql.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(navController: NavController, appointmentViewModel: AppointmentViewModel = viewModel()){
    val context = LocalContext.current
    val appointmentUiState = appointmentViewModel?.appointmentUiState
    val calendar = Calendar.getInstance()
    Scaffold() {
        AppointmentBodyContent(navController, context, appointmentUiState, appointmentViewModel, calendar)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppointmentBodyContent(navController: NavController,
                           context: Context,
                           appointmentUiState: AppointmentUiState?,
                           appointmentViewModel: AppointmentViewModel, calendar: Calendar){



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(157, 193, 193))
            .padding(start = 25.dp, top = 25.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.Top,
        ),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .background(Color(157, 193, 193)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.Start
            )
            ) {
            optionsText(text = "Date: ")
            date(appointmentUiState, appointmentViewModel, calendar)
        }
        Row(
            modifier = Modifier
                .background(Color(157, 193, 193)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.Start
            )
        ) {
            optionsText(text = "Time: ")
            time(appointmentUiState, appointmentViewModel, calendar)
        }
        Row(
            modifier = Modifier
                .background(Color(157, 193, 193)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.Start
            )
        ){
            optionsText(text = "Name of the hospital: ")
            HospitalTextField(appointmentUiState, appointmentViewModel)

        }

        Row(
            modifier = Modifier
                .background(Color(157, 193, 193)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.Start
            )
        ){
            optionsText(text = "Name of the doctor: ")
            DoctorTextField(appointmentUiState, appointmentViewModel )
        }

        optionsText(text = "Concept of the appointment: ")
        ConceptTextField(appointmentUiState, appointmentViewModel )
        AddAppointmentButton(context, appointmentViewModel)
    }
}

@Composable
fun HospitalTextField(appointmentUiState: AppointmentUiState?, appointmentViewModel: AppointmentViewModel) {

    OutlinedTextField(
        value = appointmentUiState?.hospital ?: "",
        singleLine = true,
        onValueChange = { appointmentViewModel?.onHospitalChange(it) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(46, 104, 117),
            unfocusedBorderColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color(46, 104, 117)
        )

    )
}

@Composable
fun DoctorTextField(appointmentUiState: AppointmentUiState?, appointmentViewModel: AppointmentViewModel) {

    OutlinedTextField(
        value = appointmentUiState?.doctorName ?: "",
        singleLine = true,
        onValueChange = { appointmentViewModel?.onDoctorChange(it) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(46, 104, 117),
            unfocusedBorderColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color(46, 104, 117)
        )

    )
}

@Composable
fun ConceptTextField(appointmentUiState: AppointmentUiState?, appointmentViewModel: AppointmentViewModel) {

    OutlinedTextField(
        value = appointmentUiState?.concept ?: "",
        onValueChange = { appointmentViewModel?.onConceptChange(it) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(46, 104, 117),
            unfocusedBorderColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color(46, 104, 117)
        )

    )
}

@Composable
fun AddAppointmentButton(context: Context, appointmentViewModel: AppointmentViewModel){
    Button(onClick = {
        appointmentViewModel.addNewAppointment(context = context)
    }, colors = ButtonDefaults.buttonColors( Color(46, 104, 117)),
        modifier = Modifier
            .width(280.dp)
            .height(50.dp)

    ) { Text("Add appointment reminder", color = Color.White) }
}


@Composable
fun optionsText(text: String){
    Text(text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
        )
}

@Composable
fun date(appointmentUiState: AppointmentUiState?, appointmentViewModel: AppointmentViewModel, calendar: Calendar){
    val mContext = LocalContext.current
    val sdf = SimpleDateFormat("dd-MM-yyyy")

    // Fetching current year, month and day
    val mYear = calendar.get(Calendar.YEAR)
    val mMonth = calendar.get(Calendar.MONTH)
    val mDay = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    // Declaring a string value to
    // store date in string format

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            appointmentViewModel.onDateChange("$mDayOfMonth/${mMonth+1}/$mYear")
        }, mYear, mMonth, mDay
    )


    val dateButtonModifier = Modifier.padding(start = 1.dp, end = 1.dp, top = 1.dp, bottom = 1.dp)

    Button(onClick = {
        mDatePickerDialog.show()
    }, modifier = dateButtonModifier,
        contentPadding = PaddingValues(top = 1.dp, bottom = 1.dp, start = 15.dp, end = 15.dp)
        , colors = ButtonDefaults.buttonColors( Color(46, 104, 117)) ) {
        Text(text = "${ appointmentUiState?.date }", color = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        CalendarIcon()
       }
}

@Composable
fun time(appointmentUiState: AppointmentUiState?, appointmentViewModel: AppointmentViewModel, calendar: Calendar){
    val mContext = LocalContext.current
    val sdf = SimpleDateFormat("hh:mm")
    val mHour = calendar[Calendar.HOUR_OF_DAY]
    val mMinute = calendar[Calendar.MINUTE]
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            appointmentViewModel.onTimeChange("$mHour:$mMinute")

        }, mHour, mMinute, false
    )

    val timeButtonModifier = Modifier.padding(start = 1.dp, end = 1.dp, top = 1.dp, bottom = 1.dp)

    Button(onClick = {
        mTimePickerDialog.show()
    }, modifier = timeButtonModifier,
        contentPadding = PaddingValues(top = 1.dp, bottom = 1.dp, start = 15.dp, end = 15.dp)
        , colors = ButtonDefaults.buttonColors( Color(46, 104, 117)) ) {
        Text(text = "${ appointmentUiState?.time }", color = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        ClockIcon()
    }
}

@Composable
fun ClockIcon() {
    val imageModifier = Modifier.size(23.dp)
    Image(
        painter = painterResource(id = R.drawable.schedule_fill0_wght400_grad0_opsz48),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = white),
        modifier = imageModifier
    )
}

@Composable
fun CalendarIcon() {
    val imageModifier = Modifier.size(23.dp)
    Image(
        painter = painterResource(id = R.drawable.calendar_month_fill0_wght400_grad0_opsz48),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = white),
        modifier = imageModifier
    )
}




@Preview
@Composable
fun appointmentScreenPreview(){
    AppointmentBodyContent(rememberNavController(), LocalContext.current,
        AppointmentViewModel().appointmentUiState, AppointmentViewModel(), Calendar.getInstance()
    )
}