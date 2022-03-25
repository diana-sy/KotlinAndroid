package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class UserPage (){



//    var userid = mutableStateOf("")
//
//
//    fun loginUser( email:String, password: String){
//        Firebase.auth
//            .signInWithEmailAndPassword(email,password)
//            .addOnSuccessListener {
//
//
//                userid.value = (" You arte logged with account $email")
//            }


    fun logoutUser(){
        Firebase.auth.signOut()
//        userid.value=""
    }
}