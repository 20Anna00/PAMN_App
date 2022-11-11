package com.example.pillee.themes

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pillee.R


val nunito = FontFamily(
    listOf(
        Font(R.font.nunito_regular, FontWeight.Normal),
        Font(R.font.nunito_medium, FontWeight.Medium),
        Font(R.font.nunito_semibold, FontWeight.SemiBold),
        Font(R.font.nunito_bold, FontWeight.Bold),
        Font(R.font.nunito_black, FontWeight.Black),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        color = AquaBlue,
        fontFamily = nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        color = TextWhite,
        fontFamily = nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h2 = TextStyle(
        color = TextWhite,
        fontFamily = nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)