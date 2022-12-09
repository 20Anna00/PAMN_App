package com.example.pillee.jetpackcompnavigation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.jetpackcompnavigation.screens.viewmodels.PillDetailViewModel
import com.example.pillee.themes.CentralAppBar
import com.example.pillee.themes.background_color
import com.example.pillee.themes.schedule_blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefillScreen(navController: NavController, pillViewModel: PillDetailViewModel = viewModel()){
    val drawerState = remember { mutableStateOf(DrawerValue.Closed) }

    Scaffold(topBar = { CentralAppBar(navController, "Refill", AppScreens.StartPageScreen.route, drawerState) }) {
       RefillUI(pillViewModel)
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun RefillUI(pillViewModel: PillDetailViewModel){
    var textValue by remember { mutableStateOf(TextFieldValue("")) }
    val data = pillViewModel.data.value
    var listPills = data.data


    var value = ""
    var pill = Pills()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background_color),
            verticalArrangement = Arrangement.spacedBy(
                space = 55.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column() {
                Text("Pill to refill:", fontSize = 20.sp, color = Color.Black)
                pill = DropDownMenu(list = listPills)
            }
            Column {
                Text("Number of refilled pills:", fontSize = 20.sp, color = Color.Black)
                    value = refillNumber().text

            }
            RefillButton(value = value, pill, pillViewModel)
        }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenu(list: List<Pills>?): Pills {
    var pill = Pills()

    var myPillsList = arrayOf<String>()
    if (listPills != null) {
        if (list != null) {
            for (pill in list){
                myPillsList +=pill.name
            }
        }
    }
    val listItems = myPillsList


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
            colors = ExposedDropdownMenuDefaults.textFieldColors(
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
    if (list != null) {
        for (pill in list){
            if(pill.name == selectedItem){
                return pill
            }
        }
    }
    return pill
}

@Composable
fun refillNumber(): TextFieldValue {
    var textValue by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        modifier = Modifier
            .height(60.dp)
            .width(280.dp),
        shape = RoundedCornerShape(8.dp),
        value = textValue, onValueChange = { textValue = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(46, 104, 117),
            unfocusedBorderColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color(46, 104, 117)
        )
    )
    return textValue
}

@Composable
fun RefillButton(value : String, pill: Pills, pillViewModel: PillDetailViewModel){
    var newValue = 0

        if(value.toIntOrNull() != null){
        if(pill.daysRefill.toIntOrNull() != null){
            newValue = pill.daysRefill.toInt() + value.toInt()
        }
    }
    var realValue = newValue.toString()
    androidx.compose.material3.Button(
        onClick = {
            if (pill.id != "") {
                pillViewModel.updatePill(pill.id, realValue)
            }

        },
        colors = ButtonDefaults.buttonColors(schedule_blue),
        modifier = Modifier
            .width(280.dp)
            .height(50.dp)
    ) { androidx.compose.material3.Text("Refill", color = Color.White) }
}