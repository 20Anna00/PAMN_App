package com.example.pillee.jetpackcompnavigation.model


import java.time.Instant.now
import com.google.firebase.Timestamp


data class Pills(
    val userId:String = "",
    val name:String = "",
    val days:String = "",
    val hour:Timestamp = Timestamp.now(),
    val daysRefill:String = "",
    val quantityPC: Int = 0,
    val totalAmount: Int = 0
)
