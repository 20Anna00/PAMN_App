package com.example.pillee

import androidx.annotation.StringRes
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.pillee.themes.AquaBlue

@Composable
fun HomeScreen() {

}

@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier
    ) {
        Text(text = stringResource(text), fontSize = 16.sp)
    }
}

@Composable
fun BasicTextButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    TextButton(onClick = action, modifier = modifier) { Text(text = stringResource(text)) }
}
