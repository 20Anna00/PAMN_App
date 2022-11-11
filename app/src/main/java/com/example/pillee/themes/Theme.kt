package com.example.pillee.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Shapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun PilleeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    MaterialTheme (
        content = content
            )
}