package com.example.pillee.jetpackcompnavigation.model.repository
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PillRepository (){

    private val pillList: CollectionReference = Firebase.firestore.collection("pills")

    fun addPillToFirestore (pill: Pills){
        try {
            pillList.add(pill)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}

