package com.example.pillee.jetpackcompnavigation.model

import com.google.firebase.Timestamp
import java.sql.Time


data class Pills(
    var id:String = "",
    var userID:String = "",
    var name:String = "",
    var days:String = "",
    var hour:String,
    var daysRefill:String = "",
    /*
    var quantityPC: Int = 0,
    var totalAmount: Int = 0
     */
){
    constructor() : this(
        "",
        "" ,
        "",
        "",
        "",
        "",
        /*
        0,
        0

         */
    )

   /* fun getid(): String {
        return id
    }

    fun getname(): String {
        return name
    }


    fun getuserID(): String {
        return userID
    }

    fun getdays(): String {
        return days
    }

    fun gethour(): String {
        return hour
    }

    fun getdaysRefill(): String {
        return daysRefill
    }

    fun getquantityPC(): Int {
        return quantityPC
    }

    fun gettotalAmount(): Int {
        return totalAmount
    }

    fun settotalAmount(totalAmount1: Int){
        totalAmount = totalAmount1
    }
    fun setqauntityPC(QuantityPC: Int){
        quantityPC = QuantityPC
    }
    fun setdaysRefill(DaysRefill: String){
        daysRefill = DaysRefill
    }
    fun sethour(Hour: String){
        hour = Hour
    }
    fun setdays(Days: String){
        days = Days
    }
    fun setname(Name: String){
        name = Name
    }
    fun setid(Id: String){
        id = Id
    }
    fun setuserID(UserID: String){
        userID = UserID
    }*/
}


