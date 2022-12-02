package com.example.pillee.jetpackcompnavigation.screens.viewmodels
import android.content.Context
import android.renderscript.ScriptIntrinsicYuvToRGB
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.model.repository.AuthRepository
import com.example.pillee.jetpackcompnavigation.model.repository.PillRepository
import com.example.pillee.jetpackcompnavigation.screens.PillDetailState
import com.example.pillee.jetpackcompnavigation.screens.appointment.AppointmentUiState
import com.google.firebase.Timestamp
import com.google.rpc.context.AttributeContext.Auth
import java.text.SimpleDateFormat
import java.util.*


class PillDetailViewModel
constructor(
    private val authRepository: AuthRepository = AuthRepository(),
    private val pillRepository: PillRepository = PillRepository()
): ViewModel(){

    val currentUser = authRepository.currentUser
    val hasUser: Boolean
        get() =  authRepository.hasUser()

    var pillUiState by mutableStateOf(PillUiState())
        private set

    fun onNameChange(name:String) {
        pillUiState = pillUiState.copy(name = name)
    }

    fun onDaysChange(days:String) {
        pillUiState = pillUiState.copy(days = days)
    }

    fun onHourChange(hour: String) {
        pillUiState = pillUiState.copy(hour = hour)
    }

    fun onDaysRefillChange(daysRefill: String) {
        pillUiState = pillUiState.copy(daysRefill = daysRefill)
    }

    fun onQuantityPCChange(quantityPC: Int) {
        pillUiState = pillUiState.copy(quantityPC = quantityPC)
    }

    fun onTotalAmountChange(totalAmount: Int) {
        pillUiState = pillUiState.copy(totalAmount = totalAmount)
    }

    private fun validatePill() =
        pillUiState.name.isNotBlank() && pillUiState.days.isNotBlank() &&
                pillUiState.hour.isNotBlank() && pillUiState.daysRefill.isNotBlank() &&
                pillUiState.quantityPC != 0 && pillUiState.totalAmount != 0


    fun addNewPill(context: Context){
       try{
           if(!validatePill()){
               Toast.makeText(context, "All fields should be filled", Toast.LENGTH_SHORT).show()
           }else{
               val calendar = Calendar.getInstance()
               val sdf = SimpleDateFormat("HH:mm", Locale.ENGLISH)
               calendar.setTime(sdf.parse(pillUiState.hour))

               val pill = Pills(
                   userId = currentUser!!.uid,
                   name = pillUiState.name,
                   days = pillUiState.days,
                   hour = Timestamp(calendar.time),
                   daysRefill = pillUiState.daysRefill,
                   quantityPC = pillUiState.quantityPC,
                   totalAmount = pillUiState.totalAmount
               )
               pillRepository.addPillToFirestore(pill)
           }
       }catch (e:Exception){
           e.printStackTrace()
       }
    }

}

data class PillUiState(
    val userId: String = "",
    val name: String = "",
    val days: String = "",
    val hour: String = "",
    val daysRefill: String = "",
    val quantityPC: Int = 0,
    val totalAmount: Int = 0
)