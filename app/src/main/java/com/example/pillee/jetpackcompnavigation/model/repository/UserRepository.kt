package com.example.pillee.jetpackcompnavigation.model.repository

import com.example.pillee.jetpackcompnavigation.model.Users
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

const val USER_COLLECTION_REF = "users"

class UserRepository () {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null
    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val userRef: CollectionReference = Firebase.firestore.collection(USER_COLLECTION_REF)

    @OptIn
    fun addNewUser(user: Users){
        try{
            userRef.document(user.id).set(user)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
