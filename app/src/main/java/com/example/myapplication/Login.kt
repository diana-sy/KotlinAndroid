package com.example.myapplication

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserViewModel: ViewModel() {
  private val fAuth = Firebase.auth
  private val fireStore = Firebase.firestore

    var successMessage = mutableStateOf("")
    var errorMessage = mutableStateOf("")

    fun logInUser (email: String, password: String) {
        if (email.isNotEmpty() || password.isNotEmpty()) {
            fAuth
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    errorMessage.value = ""
                    successMessage.value = "Tervetuloa!"
                }
                .addOnFailureListener {
                    errorMessage.value = "Incorrect email or password"
                    successMessage.value = ""
                }
        } else {
            errorMessage.value = "Please, fill email and password fields"
            successMessage.value = ""
        }
    }

    fun signUpUser(email: String, password: String, firstName: String, lastName: String, phoneNumber: String, route: String) {

        if (email.isNotEmpty() || password.isNotEmpty()) {

            fAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    errorMessage.value = ""
                    successMessage.value = "Registration completed.Welcome!"

                    fireStore
                        .collection("users")
                        .document(it.user!!.uid)
                        .set( User(firstName, lastName, phoneNumber, route) )
                        .addOnSuccessListener {
                            Log.d("********", "Information added successfully!")
                        }
                        .addOnFailureListener { error ->
                            Log.d("********", error.message.toString())
                        }
                }
                .addOnFailureListener {
                    errorMessage.value = "Oops! try again!"
                    successMessage.value = ""
                }

            logInUser(email, password)

        } else {
            errorMessage.value = "Please, fill email and password fields"
            successMessage.value = ""
        }

    }

    fun logout() {
        fAuth.signOut()
        errorMessage.value = ""
        successMessage.value = ""
    }
}

