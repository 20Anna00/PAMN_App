package com.example.pillee.jetpackcompnavigation.model.repository

import android.app.NotificationManager
import android.app.PendingIntent
import android.media.RingtoneManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.test.core.app.ApplicationProvider
import com.example.pillee.R
import com.example.pillee.jetpackcompnavigation.model.DataOrException
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

class PillRepository (){

    val currentUser = Firebase.auth.currentUser?.uid.orEmpty()


    private val pillList: CollectionReference = Firebase.firestore.collection("pills")

    fun addNewPill (pill: Pills, context: Context){
        try {
            pillList.document(pill.id).set(pill)
            Toast.makeText(context, "Added pill successfully", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    suspend fun getPillList(): DataOrException<List<Pills>, Exception> {
        val dataOrException = DataOrException<List<Pills>, Exception>()

        try{
            dataOrException.data = pillList.whereEqualTo("userID", currentUser)
                .get().await().map {document
                -> document.toObject(Pills::class.java)

            }
        }
        catch (e: FirebaseFirestoreException){
            dataOrException.e  = e
        }
        return dataOrException

    }

    fun updatePill(newNumber : String, pillId: String){

        try{
            val map = mapOf(
                "daysRefill" to newNumber
            )
            pillList.document(pillId).update(map)

        }
        catch(e : Exception){
            e.printStackTrace()
        }
    }

    fun deletePill(pillId : String){
        try{
            pillList.document(pillId).delete()
        } catch(e: Exception){
            e.printStackTrace()
        }
    }

}

