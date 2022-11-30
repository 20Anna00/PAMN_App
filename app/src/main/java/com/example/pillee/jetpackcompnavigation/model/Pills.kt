package com.example.pillee.jetpackcompnavigation.model

import com.google.firebase.Timestamp


data class Pills(
    val id:String = "",
    val userID:String = "",
    val name:String = "",
    val days:String = "",
    val hour:String = Timestamp.now().toString(),
    val daysRefill:String = "",
    val quantityPC: Int = 0,
    val totalAmount: Int = 0
){
    constructor() : this(
        "",
        "" ,
        "",
        "",
        "",
        "",
        0,
        0
    )
}


