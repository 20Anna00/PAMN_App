package com.example.pillee.jetpackcompnavigation.model.repository
import com.example.pillee.jetpackcompnavigation.model.Pills
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PillRepository
@Inject
constructor(
    private val pillList: CollectionReference
){
    fun addNewPill (pill: Pills){
        try {
            pillList.document(pill.id).set(pill)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}

