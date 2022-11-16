package com.example.pillee.jetpackcompnavigation.model

data class Users(
    val id: String,
    val name: String,
    val email: String
){
    constructor() : this("", "", "")
}


