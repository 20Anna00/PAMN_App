package com.example.pillee.jetpackcompnavigation.model

import com.google.firebase.Timestamp
import java.sql.Time

data class Appointment(
    val userId: String,
    val dateTime: Timestamp,
    val hospital: String,
    val doctorName: String,
    val concept: String
){
    constructor(): this("", Timestamp.now(), "", "", "")
}
