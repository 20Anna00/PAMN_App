package com.example.pillee.jetpackcompnavigation.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PillDetailScreen(
    addNewPill: (String, String, String, String, String, Int, Int) -> Unit
) {

    var idUser by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var days by remember { mutableStateOf("") }
    var daysRefill by remember { mutableStateOf("") }
    var hour by remember { mutableStateOf("") }
    var quantityPC by remember { mutableStateOf(0) }
    var totalAmount by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = idUser,
                onValueChange = { idUser = it},
                label = {
                    Text(text = "User")
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = name,
                onValueChange = { name = it},
                label = {
                    Text(text = "Name")
                }
            )
        }
    }
}