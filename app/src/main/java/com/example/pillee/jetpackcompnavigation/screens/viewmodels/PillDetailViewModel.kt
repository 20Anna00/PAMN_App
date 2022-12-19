package com.example.pillee.jetpackcompnavigation.screens.viewmodels
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pillee.jetpackcompnavigation.model.DataOrException
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.model.repository.AuthRepository
import com.example.pillee.jetpackcompnavigation.model.repository.PillRepository
import kotlinx.coroutines.launch
import java.util.*


class PillDetailViewModel
constructor(
    private val pillRepository: PillRepository = PillRepository(),
    private val authRepository: AuthRepository = AuthRepository()
): ViewModel(){
    val currentUser = authRepository.currentUser
    val hasUser: Boolean
        get() = authRepository.hasUser()

    var loading = mutableStateOf(false)
    val data: MutableState<DataOrException<List<Pills>, Exception>> = mutableStateOf(
        DataOrException(
            listOf(),
            Exception("")
        )
    )
    init{
        getPills()
    }

    private fun getPills(){
        viewModelScope.launch {
            loading.value = true
            data.value = pillRepository.getPillList()
            loading.value = false
        }
    }


    fun addNewPill(userId : String, name : String, days : String , hour : String, daysRefill : String, context: Context){
        Log.d("TAG", "Horaaaa: $hour")
        val pill = Pills(
            id=UUID.randomUUID().toString(),
            userID = currentUser!!.uid,
            name = name,
            days = days,
            hour = hour,
            daysRefill = daysRefill,
        )
        pillRepository.addNewPill(pill, context)
        Toast.makeText(context, "Pill added correctly", Toast.LENGTH_SHORT).show()
}

   fun updatePill(pillId: String, newNumber : String){
       pillRepository.updatePill(newNumber, pillId)
   }

    fun deletePill(pillId: String){
        pillRepository.deletePill(pillId)
    }

}