package com.example.pillee.jetpackcompnavigation.model

import com.google.firebase.Timestamp


data class Pills(
    val userId:String,
    val name:String,
    val days:String,
    val hour:Timestamp,
    val daysRefill:String,
    val quantityPC: Int,
    val totalAmount: Int
){
    constructor() : this(
        "",
        "" ,
        "",
        Timestamp.now(),
        "",
        0,
        0
    )
}


