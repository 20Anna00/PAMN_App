package com.example.pillee.jetpackcompnavigation.model

import androidx.compose.runtime.MutableState

data class CheckedApp (
    var app: Appointment,
    var checked: MutableState<Boolean>
){

}