package com.example.pillee.jetpackcompnavigation.model.repository

import com.example.pillee.jetpackcompnavigation.model.Appointment
import com.example.pillee.jetpackcompnavigation.model.DataOrException
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.example.pillee.jetpackcompnavigation.model.Users
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

const val APPOINTMENT_COLLECTION_REF = "appointments"

class AppointmentRepository () {
    val currentUser = Firebase.auth.currentUser?.uid.orEmpty()


    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null
    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val userRef: CollectionReference = Firebase.firestore.collection(
        APPOINTMENT_COLLECTION_REF)

    @OptIn
    fun addAppointmentToFirestore(appointment: Appointment){
        try{
            userRef.add(appointment)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    suspend fun getAppointmentList(): DataOrException<List<Appointment>, Exception> {
        val dataOrException = DataOrException<List<Appointment>, Exception>()

        try{
            dataOrException.data = userRef.whereEqualTo("userId", currentUser)
                .get().await().map {document
                    -> document.toObject(Appointment::class.java)

                }
        }
        catch (e: FirebaseFirestoreException){
            dataOrException.e  = e
        }
        return dataOrException

    }
}