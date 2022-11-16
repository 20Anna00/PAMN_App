package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
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
import androidx.navigation.NavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens
import com.example.pillee.themes.CentralAppBar

val list = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")


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
    var textValue by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFE5E5E5)),
        verticalArrangement = Arrangement.spacedBy(
            space = 65.dp,
            alignment = Alignment.CenterVertically ),
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        Column ()
        {
            Text("Name", color = Color.Black, fontSize = 20.sp)
            MyDropDownMenu(list)
        }

        Column()
        {

            Text("Day", color = Color.Black, fontSize = 20.sp)
            MyDropDownMenu(list = list)
        }

        Column()
        {

            Text("Hour", color = Color.Black, fontSize = 20.sp)
            MyDropDownMenu(list = list)
        }

        Column()
        {

            Text("Number of Pills that were refilled", color = Color.Black, fontSize = 20.sp)
            TextField(
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
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyDropDownMenu(list : Array<String>) {

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
}