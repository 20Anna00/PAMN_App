package com.example.pillee.jetpackcompnavigation.model

import com.google.firebase.Timestamp

data class Appointment(
    val userId: String,
    val dateTime: Timestamp,
    val hospital: String,
    val doctorName: String,
    val description: String
){
    constructor(): this("", Timestamp.now(), "", "", "")
}
