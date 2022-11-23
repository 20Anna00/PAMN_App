package com.example.pillee.jetpackcompnavigation.model.repository

import com.example.pillee.jetpackcompnavigation.model.Appointment
import com.example.pillee.jetpackcompnavigation.model.Users
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

const val APPOINTMENT_COLLECTION_REF = "appointments"

class AppointmentRepository () {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null
    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val userRef: CollectionReference = Firebase.firestore.collection(
        APPOINTMENT_COLLECTION_REF)

    @OptIn
    fun addNewAppointment(appointment: Appointment){
        try{
            userRef.document(user()!!.uid).set(appointment)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}