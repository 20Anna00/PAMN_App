package com.example.pillee.jetpackcompnavigation.model.repository

import android.util.Log
import com.example.pillee.jetpackcompnavigation.model.Notification
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
        notificationRef.whereEqualTo("userID", currentUser).get()
            .addOnCompleteListener {
                    task  ->  if (task.isSuccessful) for (document in task.result) notificationList.add(document.toObject(Notification::class.java).toString())
            }
        Log.d("LISTSSS",notificationList.toString())
        return notificationList
    }
}