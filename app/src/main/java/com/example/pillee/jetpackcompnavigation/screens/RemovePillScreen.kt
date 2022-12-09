package com.example.pillee.jetpackcompnavigation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
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
fun RemovePillScreen(navController: NavController, pillViewModel: PillDetailViewModel = viewModel()){

    Scaffold(topBar = { CentralAppBar(navController, "Remove Pills", AppScreens.ConfigurationScreen.route) }) {
        RemoveUI(pillViewModel)
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun RemoveUI(pillViewModel: PillDetailViewModel){
    var textValue by remember { mutableStateOf(TextFieldValue("")) }
    val data = pillViewModel.data.value
    var listPills = data.data

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
            Text("Pill to remove:", fontSize = 20.sp, color = Color.Black)
            pill = DropDownMenuRemove(list = listPills)
        }

        RemoveButton(pill, pillViewModel)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenuRemove(list: List<Pills>?): Pills {
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
fun RemoveButton(pill: Pills, pillViewModel: PillDetailViewModel){


    androidx.compose.material3.Button(
        onClick = {
            if (pill.id != "") {
                pillViewModel.deletePill(pill.id)
            }

        },
        colors = ButtonDefaults.buttonColors(schedule_blue),
        modifier = Modifier
            .width(280.dp)
            .height(50.dp)
    ) { androidx.compose.material3.Text("Remove", color = Color.White) }
}