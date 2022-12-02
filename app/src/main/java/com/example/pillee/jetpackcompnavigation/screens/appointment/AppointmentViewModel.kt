package com.example.pillee.jetpackcompnavigation.screens.appointment

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pillee.jetpackcompnavigation.model.Appointment
import com.example.pillee.jetpackcompnavigation.model.repository.AppointmentRepository
import com.example.pillee.jetpackcompnavigation.model.repository.AuthRepository
import com.google.firebase.Timestamp
import com.google.type.DateTime
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.logging.Level.parse


class AppointmentViewModel(
    private val authRepository: AuthRepository = AuthRepository(),
    private val appointmentRepository: AppointmentRepository = AppointmentRepository(),
): ViewModel(){

    val currentUser = authRepository.currentUser
    val hasUser: Boolean
        get() = authRepository.hasUser()

    var appointmentUiState by mutableStateOf(AppointmentUiState())
        private set

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
                val calendar = Calendar.getInstance()
                val dateTime = appointmentUiState.date + " " + appointmentUiState.time
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH)
                calendar.setTime(sdf.parse(dateTime))

                val appointment = Appointment(
                    currentUser!!.uid,
                    Timestamp(calendar.time),
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
