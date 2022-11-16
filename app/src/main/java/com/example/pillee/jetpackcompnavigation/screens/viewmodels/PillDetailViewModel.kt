package com.example.pillee.jetpackcompnavigation.screens.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.model.repository.PillRepository
import com.example.pillee.jetpackcompnavigation.screens.PillDetailState
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class PillDetailViewModel
@Inject
constructor(
    private val pillRepository: PillRepository
): ViewModel(){
    private val _state: MutableState<PillDetailState> = mutableStateOf(PillDetailState())
    val state: State<PillDetailState>
        get() = _state

    fun addNewPill(userId : String, name : String, days : String , hour : String, daysRefill : String , quantityPC : Int, totalAmount : Int){
        val pill = Pills(
            id=UUID.randomUUID().toString(),
            userId = userId,
            name = name,
            days = days,
            hour = hour,
            daysRefill = daysRefill,
            quantityPC = quantityPC,
            totalAmount = totalAmount
        )
        pillRepository.addNewPill(pill)
    }
}