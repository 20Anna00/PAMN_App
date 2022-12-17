package com.example.pillee.jetpackcompnavigation.model.repository

import android.util.Log
import com.example.pillee.jetpackcompnavigation.model.Appointment
import com.example.pillee.jetpackcompnavigation.model.DataOrException
import com.example.pillee.jetpackcompnavigation.model.Notification
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class NotificationRepository {

    val currentUser = Firebase.auth.currentUser?.uid.orEmpty()

    private val notificationRef: CollectionReference = Firebase.firestore.collection("notifications")

    @OptIn
    fun addNotificationToFirestore(notification: Notification){
        try{
            notificationRef.add(notification)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    fun getNotificationList(): ArrayList<String> {
        var notificationList : ArrayList<String> = ArrayList()
        notificationRef.get()
            .addOnCompleteListener {
                    task  ->  if (task.isSuccessful) for (document in task.result) if (document.get("userId") == currentUser) notificationList.add(document.toObject(Notification::class.java).toString())
            }
        Log.d("LISTSSS",notificationList.toString())
        return notificationList
    }
}