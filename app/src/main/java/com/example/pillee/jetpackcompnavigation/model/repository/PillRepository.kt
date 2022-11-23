package com.example.pillee.jetpackcompnavigation.model.repository
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PillRepository (){

    private val pillList: CollectionReference = Firebase.firestore.collection("pills")

    fun addNewPill (pill: Pills){
        try {
            pillList.document(pill.id).set(pill)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}

