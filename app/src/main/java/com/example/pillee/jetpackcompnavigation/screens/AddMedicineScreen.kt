package com.example.pillee.jetpackcompnavigation.screens

import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.model.repository.AuthRepository
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.screens.appointment.AppointmentViewModel
import com.example.pillee.jetpackcompnavigation.screens.viewmodels.PillDetailViewModel
import com.example.pillee.themes.CentralAppBar
import com.example.pillee.themes.schedule_blue
import com.example.pillee.themes.schedule_lightgreen
import com.example.pillee.themes.white
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

val list = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
val listPills = arrayOf("Hibuprofeno", "Termagin", "Paracetamol", "Dalzy", "Anfetas", "Coca", "Cristal")



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicineScreen(navController: NavController,
                      pillDetailViewModel: PillDetailViewModel = viewModel(),
                      authRepository: AuthRepository = AuthRepository()
){
    val context = LocalContext.current
    Scaffold(topBar = { CentralAppBar(navController, "Add Medicine",  AppScreens.ConfigurationScreen.route) }) {
        MyUI(pillDetailViewModel, authRepository, context)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyUI(pillDetailViewModel: PillDetailViewModel, authRepository: AuthRepository, context: Context) {
    var name = "";


    val monday = remember { mutableStateOf(false) }
    val tuesday = remember { mutableStateOf(false) }
    val wednesday = remember { mutableStateOf(false) }
    val thursday = remember { mutableStateOf(false) }
    val friday = remember { mutableStateOf(false) }
    val saturday = remember { mutableStateOf(false) }
    val sunday = remember { mutableStateOf(false) }
    val mTime1 = remember {mutableStateOf("")}
    val mTime2 = remember {mutableStateOf("")}
    val mTime3 = remember {mutableStateOf("")}

    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    val mHour2 = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute2 = mCalendar[Calendar.MINUTE]
    val mHour3 = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute3 = mCalendar[Calendar.MINUTE]
    val format = SimpleDateFormat("HH:mm")

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            val hourString = "$mHour:$mMinute"
            val sdf = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            val hourDate = sdf.parse(hourString)
            mTime1.value = format.format(hourDate)
        }, mHour, mMinute, false
    )

    val mTimePickerDialog2 = TimePickerDialog(
        mContext,
        {_, mHour2 : Int, mMinute2: Int ->
            val hourString2 = "$mHour2:$mMinute2"
            val sdf2 = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            val hourDate2 = sdf2.parse(hourString2)
            mTime2.value = format.format(hourDate2)
        }, mHour2, mMinute2, false
    )

    val mTimePickerDialog3 = TimePickerDialog(
        mContext,
        {_, mHour3 : Int, mMinute3: Int ->
            val hourString3 = "$mHour3:$mMinute3"
            val sdf3 = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            val hourDate3 = sdf3.parse(hourString3)
            mTime3.value = format.format(hourDate3)
        }, mHour3, mMinute3, false
    )

    var textValue by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFCFDBE1)),

        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        Column ()
        {
            Text("Name:", color = Color.Black, fontSize = 20.sp,
                modifier = Modifier
                    .padding(1.dp, 16.dp, 1.dp, 1.dp))
            name = MyDropDownMenu(listPills, false)

            Text("Day:", color = Color.Black, fontSize = 20.sp,
                modifier = Modifier
                .padding(1.dp, 16.dp, 1.dp, 1.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = monday.value, onCheckedChange = {monday.value = it},
                    colors = CheckboxDefaults.colors(checkedColor = Color.Blue, checkmarkColor = schedule_lightgreen)
                )
                Text(text =  "Mo", color = Color.Black, fontSize = 12.sp)
                Checkbox(checked = tuesday.value, onCheckedChange = {tuesday.value = it},
                    colors = CheckboxDefaults.colors(checkedColor = Color.Blue, checkmarkColor = schedule_lightgreen)
                )
                Text(text =  "Tu", color = Color.Black, fontSize = 12.sp)

                Checkbox(checked = wednesday.value, onCheckedChange = {wednesday.value = it},
                    colors = CheckboxDefaults.colors(checkedColor = Color.Blue, checkmarkColor = schedule_lightgreen)
                )
                Text(text =  "We", color = Color.Black, fontSize = 12.sp)

                Checkbox(checked = thursday.value, onCheckedChange = {thursday.value = it},
                    colors = CheckboxDefaults.colors(checkedColor = Color.Blue, checkmarkColor = schedule_lightgreen)
                )
                Text(text =  "Th", color = Color.Black, fontSize = 12.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically){

                Checkbox(checked = friday.value, onCheckedChange = {friday.value = it},
                    colors = CheckboxDefaults.colors(checkedColor = Color.Blue, checkmarkColor = schedule_lightgreen)
                )
                Text(text =  "Fr", color = Color.Black, fontSize = 12.sp)

                Checkbox(checked = saturday.value, onCheckedChange = {saturday.value = it},
                    colors = CheckboxDefaults.colors(checkedColor = Color.Blue, checkmarkColor = schedule_lightgreen)
                )
                Text(text =  "Sa", color = Color.Black, fontSize = 12.sp)

                Checkbox(checked = sunday.value, onCheckedChange = {sunday.value = it},
                    colors = CheckboxDefaults.colors(checkedColor = Color.Blue, checkmarkColor = schedule_lightgreen)
                )
                Text(text =  "Su", color = Color.Black, fontSize = 12.sp)
            }

            Text("Hour:", color = Color.Black, fontSize = 20.sp,
                modifier = Modifier
                    .padding(1.dp, 16.dp, 1.dp, 1.dp))
            Column(modifier = Modifier
                .padding(1.dp, 1.dp, 1.dp, 1.dp)
                .padding(1.dp, 1.dp, 1.dp, 1.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(40.dp),
                modifier = Modifier.width(300.dp)){
                    clockButton(mTimePickerDialog, mTime1)
                    clearHourButton(mTime = mTime1)
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(40.dp)){
                    clockButton(mTimePickerDialog2, mTime2)
                    clearHourButton(mTime = mTime2)
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(40.dp)){
                    clockButton(mTimePickerDialog3, mTime3)
                    clearHourButton(mTime = mTime3)
                }
            }

            Text("Amount of pills in container:", color = Color.Black, fontSize = 20.sp,
            modifier = Modifier.padding(1.dp, 16.dp, 1.dp, 1.dp))

        }
        OutlinedTextField(
            modifier = Modifier
                .padding(1.dp, 1.dp, 1.dp, 16.dp)
                .height(60.dp)
                .width(280.dp)
                .padding(1.dp, 1.dp, 1.dp, 1.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(8.dp),
            value = textValue, onValueChange = { textValue = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(46, 104, 117),
                unfocusedBorderColor = Color.White,
                unfocusedLabelColor = Color.White,
                focusedLabelColor = Color(46, 104, 117)

            )
        )
        AddMedicineButton(pillDetailViewModel, authRepository, name, mTime1, mTime2, mTime3, textValue.text,
            monday,tuesday, wednesday, thursday, friday, saturday, sunday, context)

    }

}



@Composable
fun clearHourButton(mTime: MutableState<String>){
    Button(onClick = { mTime.value = "" },
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        modifier= Modifier.size(50.dp),
        colors = androidx.compose.material.ButtonDefaults.buttonColors(Color(0xFF7A7A7A))
        ){
        ClearHourIcon()
    }
}

@Composable
fun clockButton(mTimePickerDialog: TimePickerDialog, mTime1: MutableState<String>){
    Button(onClick = { mTimePickerDialog.show() },
        colors = androidx.compose.material.ButtonDefaults.buttonColors(Color(0xFF174560)),
        modifier = Modifier
            .padding(10.dp, 1.dp, 1.dp, 1.dp)
            .height(50.dp)
            .width(145.dp),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 15.dp),
        ) {
        Box(contentAlignment = Alignment.CenterEnd) {
            Text(text = mTime1.value, fontSize = 20.sp, color = Color.White,
                modifier = Modifier.fillMaxWidth())
            ClockIcon()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyDropDownMenu(list : Array<String>, readOnly: Boolean): String {

    val listItems = list
    val focusManager = LocalFocusManager.current
    var selectedItem by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {


            TextField(
                readOnly = readOnly,
                shape = RoundedCornerShape(8.dp),
                value = selectedItem,
                onValueChange = { selectedItem = it },
                //label = { Text(text = "Name") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                //colors = ExposedDropdownMenuDefaults.textFieldColors()
                colors = textFieldColors(
                    textColor = Color.White,
                    trailingIconColor = Color.White,
                    placeholderColor = Color(0xFF174560),
                    backgroundColor = Color(0xFF174560),

                ),
                textStyle = TextStyle(fontSize = 20.sp),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),

                )


        // filter options based on text field value
        val filteringOptions =
            listItems.filter { it.contains(selectedItem, ignoreCase = true) }

        if (filteringOptions.isNotEmpty()) {

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                filteringOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption, fontSize = 20.sp)
                    }
                }
            }
        }
    }
    return selectedItem;
}

@Composable
fun ClockIcon() {
    val imageModifier = Modifier.size(43.dp)
    Image(
        painter = painterResource(id = R.drawable.schedule_fill0_wght400_grad0_opsz48),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = white),
        modifier = imageModifier
    )
}

@Composable
fun ClearHourIcon(){
    val imageModifier = Modifier.size(23.dp)
    Image(
        painter = painterResource(id = R.drawable.delete_fill0_wght400_grad0_opsz48),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = Color.White),
        modifier = imageModifier
    )
}

@Composable
fun AddMedicineButton(pillDetailViewModel: PillDetailViewModel,
                      authRepository: AuthRepository,
                      name: String,
                      hour1: MutableState<String>,
                      hour2: MutableState<String>,
                      hour3: MutableState<String>,
                      numberString: String,
                      monday: MutableState<Boolean>,
                      tuesday: MutableState<Boolean>,
                      wednesday: MutableState<Boolean>,
                      thursday: MutableState<Boolean>,
                      friday: MutableState<Boolean>,
                      saturday: MutableState<Boolean>,
                      sunday: MutableState<Boolean>,
                      context: Context
){
    androidx.compose.material3.Button(
        onClick = {

                  Log.d("TAG", "Horaaa1: ${hour1.value}")
                  pillDetailViewModel.addNewPill(authRepository.currentUser.toString(),
                  name,
                  takeDays(monday,tuesday, wednesday, thursday, friday, saturday, sunday),
                  takeHours(hour1.value, hour2.value, hour3.value), numberString, context)
        }, colors = ButtonDefaults.buttonColors(schedule_blue),
        modifier = Modifier
            .width(280.dp)
            .height(50.dp)

    ) { androidx.compose.material3.Text("Add Pill", color = Color.White) }
}

fun takeHours(hour1: String, hour2: String, hour3: String):String{
    var res = ""

    if(!hour1.equals("")) res += "$hour1,"
    if(!hour2.equals("")) res += "$hour2,"
    if(!hour3.equals("")) res += "$hour3,"

    val regex = ",$".toRegex()
    res = res.replace(regex, "")

    return res
}

fun takeDays(monday: MutableState<Boolean>,
             tuesday: MutableState<Boolean>,
             wednesday: MutableState<Boolean>,
             thursday: MutableState<Boolean>,
             friday: MutableState<Boolean>,
             saturday: MutableState<Boolean>,
             sunday: MutableState<Boolean>): String{
    var res = ""
    if(monday.value) res += "monday,"
    if(tuesday.value) res += "tuesday,"
    if(wednesday.value) res += "wednesday,"
    if(thursday.value) res += "thursday,"
    if(friday.value) res += "friday,"
    if(saturday.value) res += "saturday,"
    if(sunday.value) res += "sunday,"

    val regex = ",$".toRegex()
    res = res.replace(regex, "")
    return res
}


