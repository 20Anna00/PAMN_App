package com.example.pillee.jetpackcompnavigation.model

import com.google.firebase.Timestamp

data class Notification (
    val userId: String,
    val dateTime: Long,
    val pill: String,

){
    constructor(): this("", 0, "")
}