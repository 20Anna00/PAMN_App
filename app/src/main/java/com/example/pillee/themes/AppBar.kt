package com.example.pillee.themes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pillee.jetpackcompnavigation.navigation.AppScreens

    @Composable
    public fun CentralAppBar (navController: NavController, text : String, route : String) {
        return TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.navigate(route = route)}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Ir hacia arriba")
                }
            },
            contentColor = Color.White,
            title = {
                Text(
                    text = text,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 70.dp)
                )
            },
            backgroundColor = Color(0xFF174560),

            elevation = 24.dp
        )
    }

