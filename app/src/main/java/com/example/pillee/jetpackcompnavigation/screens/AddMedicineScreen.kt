package com.example.pillee.jetpackcompnavigation.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.screens.appointment.AppointmentUiState
import com.example.pillee.jetpackcompnavigation.screens.appointment.AppointmentViewModel
import com.example.pillee.jetpackcompnavigation.screens.appointment.CalendarIcon
import com.example.pillee.jetpackcompnavigation.screens.appointment.ClockIcon
import com.example.pillee.jetpackcompnavigation.screens.viewmodels.PillDetailViewModel
import com.example.pillee.jetpackcompnavigation.screens.viewmodels.PillUiState
import com.example.pillee.themes.CentralAppBar
import java.text.SimpleDateFormat
import java.util.*

val list = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
val listPills = arrayOf("Hibuprofeno", "Termagin", "Paracetamol", "Dalzy", "Anfetas", "Coca", "Cristal")



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicineScreen(navController: NavController, pillDetailViewModel: PillDetailViewModel = viewModel()){
    Scaffold(topBar = { CentralAppBar(navController, "Add Medicine",  AppScreens.ConfigurationScreen.route) }) {
        val pillUiState = pillDetailViewModel.pillUiState
        AddMedicineBodyContent(navController, LocalContext.current, pillUiState, pillDetailViewModel)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddMedicineBodyContent(
    navController: NavController,
    context: Context,
    pillUiState: PillUiState,
    pillDetailViewModel: PillDetailViewModel
) {
    var name = "";
    var day = "";
    var hour = "";
    var number = "";



    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFCFDBE1)),
        verticalArrangement = Arrangement.spacedBy(
            space = 65.dp,
            alignment = Alignment.CenterVertically ),
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        Column ()
        {
            Text("Name", color = Color.Black, fontSize = 20.sp)
            name = MyDropDownMenu(listPills)
        }

        Column()
        {

            Text("Day", color = Color.Black, fontSize = 20.sp)
            day = MyDropDownMenu(list = list)
        }

        Column()
        {

            Text("Hour", color = Color.Black, fontSize = 20.sp)
            time(pillUiState, pillDetailViewModel)

        }


        Column()
        {

            Text("Number of Pills that were refilled", color = Color.Black, fontSize = 20.sp)

            /*TextField(
                modifier = Modifier
                    .height(60.dp)
                    .width(280.dp),
                value = pillUiState.totalAmount, onValueChange = {  },
                colors = textFieldColors(
                    textColor = Color.White,
                    trailingIconColor = Color.White,
                    placeholderColor = Color(0xFF174560),
                    backgroundColor = Color(0xFF174560),

                    )
            )*/

        }
    }

}


@Composable
fun time(pillUiState: PillUiState?, pillDetailViewModel: PillDetailViewModel){

    val mContext = LocalContext.current
    val calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("hh:mm")
    val mHour = calendar[Calendar.HOUR_OF_DAY]
    val mMinute = calendar[Calendar.MINUTE]
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            pillDetailViewModel.onHourChange("$mHour:$mMinute")

        }, mHour, mMinute, false
    )

    val timeButtonModifier = Modifier.padding(start = 1.dp, end = 1.dp, top = 1.dp, bottom = 1.dp)

    androidx.compose.material3.Button(
        onClick = {
            mTimePickerDialog.show()
        },
        modifier = timeButtonModifier,
        contentPadding = PaddingValues(top = 1.dp, bottom = 1.dp, start = 15.dp, end = 15.dp),
        colors = ButtonDefaults.buttonColors(Color(46, 104, 117))
    ) {
        androidx.compose.material3.Text(text = "${pillUiState?.hour}", color = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        ClockIcon()
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyDropDownMenu(list : Array<String>): String {

    val listItems = list

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
                textStyle = TextStyle(fontSize = 20.sp)
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
