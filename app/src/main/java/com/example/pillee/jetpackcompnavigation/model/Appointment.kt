package com.example.pillee.jetpackcompnavigation.model

import java.sql.Time

data class Appointment(
    val userId: String,
    val dateAndTime: String,
    val hospital: String,
    val doctorName: String,
    val concept: String
){
    constructor(): this("", "", "", "", "")
}
