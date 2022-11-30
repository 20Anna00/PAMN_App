package com.example.pillee.jetpackcompnavigation.screens.viewmodels
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.model.repository.PillRepository
import com.example.pillee.jetpackcompnavigation.screens.PillDetailState
import com.google.firebase.firestore.CollectionReference
import java.util.UUID



class PillDetailViewModel
constructor(
    private val pillRepository: PillRepository = PillRepository()
): ViewModel(){


    fun addNewPill(userId : String, name : String, days : String , hour : String, daysRefill : String , quantityPC : Int, totalAmount : Int){
        val pill = Pills(
            id=UUID.randomUUID().toString(),
            userID = userId,
            name = name,
            days = days,
            hour = hour,
            daysRefill = daysRefill,
            quantityPC = quantityPC,
            totalAmount = totalAmount
        )
        pillRepository.addNewPill(pill)
    }

    fun getPills(): List<Pills> {
        return pillRepository.getPillList();
    }
}