package com.example.pillee.jetpackcompnavigation.screens.appointment

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.themes.CentralAppBar
import com.example.pillee.themes.white
import io.grpc.internal.ReadableBuffer
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(navController: NavController){
    val context = LocalContext.current
    Scaffold() {
        AppointmentBodyContent(navController)
    }
}

@Composable
fun AppointmentBodyContent(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(157, 193, 193)),
        verticalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        optionsText(text = "Date and time of the appointment")
        date()
        optionsText(text = "Name of the hospital")
        optionsText(text = "Concept of the appointment")
    }
}

@Composable
fun optionsText(text: String){
    Text(text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
        )
}

@Composable
fun date(){
    val mContext = LocalContext.current
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    var currentDateAndTime = sdf.format(Date())


    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }
    mDate.value = sdf.format(Date())
    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    val dateButtonModifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)

    Button(onClick = {
        mDatePickerDialog.show()
    }, modifier = dateButtonModifier
        , colors = ButtonDefaults.buttonColors( Color(46, 104, 117)) ) {
        Text(text = "${ mDate.value }", color = Color.White)
        angleDownIcon()
       }
}

@Composable
fun angleDownIcon() {
    val imageModifier = Modifier.size(30.dp)

    Image(
        painter = painterResource(id = R.drawable.keyboard_arrow_down_fill0_wght400_grad0_opsz48),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = white),
        modifier = imageModifier
    )
}


@Preview
@Composable
fun appointmentScreenPreview(){
    AppointmentBodyContent(rememberNavController())
}