package com.example.pillee.jetpackcompnavigation.model

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class CheckedPills (
    var pill: Pills = Pills(),
    var checked: MutableState<Boolean>,
    var hour : String
    ){

}