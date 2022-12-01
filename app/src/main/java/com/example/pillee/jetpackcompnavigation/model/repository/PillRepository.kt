package com.example.pillee.jetpackcompnavigation.model.repository
import android.content.ContentValues.TAG
import android.util.Log
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class PillRepository (){

    var pills = mutableListOf<Pills>()

    private val pillList: CollectionReference = Firebase.firestore.collection("pills")

    fun addNewPill (pill: Pills){
        try {
            pillList.document(pill.id).set(pill)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getPillList(): List<Pills> {
        var pill1 = Pills("ja", "nein", "omg", "", "", "", 20, 20)

        try {
            pillList.get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                        var pill = document.toObject(Pills::class.java)
                        Log.d(TAG, pill.name)
                        pills.add(pill)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
        catch(e : Exception){
            Log.w(TAG, "Error getting documents: ", e)

        }

        return pills
    }


}

