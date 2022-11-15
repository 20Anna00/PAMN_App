package com.example.pillee.jetpackcompnavigation.model.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ThrowOnExtraProperties
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

const val PILLS_COLLECTION_REF = "pills"

class StorageRepository() {
    val user = Firebase.auth.currentUser
    fun hasUser():Boolean = Firebase.auth.currentUser != null
    fun getUser():String = Firebase.auth.currentUser?.uid.orEmpty()

    private val pillsRef:CollectionReference = Firebase.firestore.collection(PILLS_COLLECTION_REF)


}



sealed class Resources<T>(val data: T? = null, throwable: Throwable? = null){
    class Loading<T>: Resources<T>()
    class Success<T>(data: T?): Resources<T>(data = data)
    class Error<T>(throwable: Throwable?): Resources<T>(throwable = throwable)




}
