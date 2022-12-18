package com.example.pillee.jetpackcompnavigation.screens.appointment

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pillee.jetpackcompnavigation.model.Appointment
import com.example.pillee.jetpackcompnavigation.model.DataOrException
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.model.repository.AppointmentRepository
import com.example.pillee.jetpackcompnavigation.model.repository.AuthRepository
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.util.logging.Level.parse


class AppointmentViewModel(
    private val repository: AuthRepository = AuthRepository(),
    private val appointmentRepository: AppointmentRepository = AppointmentRepository(),
): ViewModel(){

    val currentUser = repository.currentUser
    val hasUser: Boolean
        get() = repository.hasUser()

    var appointmentUiState by mutableStateOf(AppointmentUiState())
        private set

    var loading = mutableStateOf(false)
    val data: MutableState<DataOrException<List<Appointment>, Exception>> = mutableStateOf(
        DataOrException(
            listOf(),
            Exception("")
        )
    )
    init{
        getAppointments()
    }

    private fun getAppointments(){
        viewModelScope.launch {
            loading.value = true
            data.value = appointmentRepository.getAppointmentList()
            loading.value = false
        }
    }

    fun onDateChange(date:String) {
        appointmentUiState = appointmentUiState.copy(date = date)
    }

    fun onTimeChange(time:String){
        appointmentUiState = appointmentUiState.copy(time = time)
    }

    fun onHospitalChange(hospital: String){
        appointmentUiState = appointmentUiState.copy(hospital = hospital)
    }

    fun onDoctorChange(doctor: String){
        appointmentUiState = appointmentUiState.copy(doctorName = doctor)
    }

    fun onConceptChange(concept: String){
        appointmentUiState = appointmentUiState.copy(concept = concept)
    }

    private fun validateAppointment() =
                appointmentUiState.concept.isNotBlank() && appointmentUiState.hospital.isNotBlank()
                        //&& appointmentUiState.doctorName.isNotBlank()

    fun addNewAppointment(context: Context){
        try {
            if (!validateAppointment()) {
                Toast.makeText(context, "All fields should be filled", Toast.LENGTH_SHORT).show()
            } else {

                val sdf3 = SimpleDateFormat("HH:mm", Locale.ENGLISH)
                val timeFormatted = sdf3.parse(appointmentUiState.time)
                val dateAndTime = appointmentUiState.date + " " + timeFormatted

                val appointment = Appointment(
                    currentUser!!.uid,
                    dateAndTime,
                    appointmentUiState.hospital,
                    appointmentUiState.doctorName,
                    appointmentUiState.concept
                )
                appointmentRepository.addAppointmentToFirestore(appointment)
                Toast.makeText(context, "Added succesfully", Toast.LENGTH_SHORT).show()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}

data class AppointmentUiState(
    val date: String = "",
    val time: String = "",
    val hospital: String = "",
    val doctorName: String = "",
    val concept: String = ""
)
