package com.example.pillee.jetpackcompnavigation.screens

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
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
import androidx.navigation.NavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.themes.CentralAppBar
import java.util.*

val list = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
val listPills = arrayOf("Hibuprofeno", "Termagin", "Paracetamol", "Dalzy", "Anfetas", "Coca", "Cristal")



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicineScreen(navController: NavController){
    Scaffold(topBar = { CentralAppBar(navController, "Add Medicine",  AppScreens.ConfigurationScreen.route) }) {
        MyUI()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyUI() {
    var name = "";
    var day = "";
    var hour = "";
    var number = "";

    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    val mTime = remember {mutableStateOf("")}
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"

        }, mHour, mMinute, false
    )

    var textValue by remember { mutableStateOf(TextFieldValue("")) }

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
            Button(onClick = { mTimePickerDialog.show() },
                colors = androidx.compose.material.ButtonDefaults.buttonColors(Color(0xFF174560)),
                shape = RoundedCornerShape(8.dp),

                modifier = Modifier
                    .width(280.dp)
                    .height(60.dp)

            ) {
                Text(text = mTime.value, fontSize = 20.sp, color = Color.White)
            }

        }


        Column()
        {

            Text("Number of Pills that were refilled", color = Color.Black, fontSize = 20.sp)
            TextField(
                modifier = Modifier
                    .height(60.dp)
                    .width(280.dp),
                shape = RoundedCornerShape(8.dp),
                value = textValue, onValueChange = { textValue = it },
                colors = textFieldColors(
                    textColor = Color.White,
                    trailingIconColor = Color.White,
                    placeholderColor = Color(0xFF174560),
                    backgroundColor = Color(0xFF174560),

                    )
            )

        }
    }
    hour = mTime.value
    number = textValue.toString()

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

@Preview
@Composable
fun addMedicinePreview(){
    MyUI()
}