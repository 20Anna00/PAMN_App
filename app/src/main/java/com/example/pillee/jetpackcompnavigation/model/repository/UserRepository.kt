package com.example.pillee.jetpackcompnavigation.model.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.auth.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject
constructor(val userList: CollectionReference) {
    fun addNewUser(user: User){
        try{

        }catch (e:Exception){

        }
    }
}
