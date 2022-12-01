package com.example.pillee.jetpackcompnavigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.example.pillee.themes.background_color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefillScreen(navController: NavController){
    Scaffold(topBar = { CentralAppBar(navController, "Refill", AppScreens.StartPageScreen.route) }) {
       RefillUI()
    }
}

@Composable
fun RefillUI(){
    var textValue by remember { mutableStateOf(TextFieldValue("")) }

    val list = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color),
        verticalArrangement = Arrangement.spacedBy(
            space = 55.dp,
            alignment = Alignment.CenterVertically ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column() {
            Text("Pill to refill:", fontSize = 20.sp, color = Color.Black)
            DropDownMenu(list = list)
        }
        Column() {
            Text("Number of refilled pills:", fontSize = 20.sp, color = Color.Black)
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
        }
 }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenu(list : Array<String>): String {

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
    return selectedItem;
}