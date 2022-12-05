package com.example.pillee.jetpackcompnavigation.model.repository
import android.content.ContentValues.TAG
import android.util.Log
import com.example.pillee.jetpackcompnavigation.model.DataOrException
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class PillRepository (){

    val currentUser = Firebase.auth.currentUser?.uid.orEmpty()


    private val pillList: CollectionReference = Firebase.firestore.collection("pills")

    fun addNewPill (pill: Pills){
        try {
            pillList.document(pill.id).set(pill)
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


}

