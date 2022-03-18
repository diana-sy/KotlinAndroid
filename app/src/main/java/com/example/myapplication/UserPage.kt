package com.example.myapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class UserPage :ViewModel() {
    var userid = mutableStateOf("")

    fun loginUser( email:String, password: String){
        Firebase.auth
            .signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                userid.value = email
            }
    }

    fun logoutUser(){
        Firebase.auth.signOut()
        userid.value=""
    }
}