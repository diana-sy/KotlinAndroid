package com.example.myapplication.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.DessertView
import com.example.myapplication.User
import com.example.myapplication.UserPage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore

    var successMessage = mutableStateOf("")
    var errorMessage = mutableStateOf("")

    fun logInUser(email: String, pw: String) {
        if (email.isNotEmpty() || pw.isNotEmpty()) {
            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    errorMessage.value = ""
                    successMessage.value = "Logged in successfully"
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

    fun signUpUser(email: String, pw: String, firstName: String, lastName: String, address: String, phoneNumber: String, route: String) {

        if (email.isNotEmpty() || pw.isNotEmpty()) {

            fAuth
                .createUserWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    errorMessage.value = ""
                    successMessage.value = "Registration completed successfully"

                    fireStore
                        .collection("users")
                        .document(it.user!!.uid)
                        .set( User(firstName, lastName, address, phoneNumber, route) )
                        .addOnSuccessListener {
                            Log.d("********", "User's information added successfully!")
                        }
                        .addOnFailureListener { error ->
                            Log.d("********", error.message.toString())
                        }
                }
                .addOnFailureListener {
                    errorMessage.value = "Something went wrong :("
                    successMessage.value = ""
                }

            logInUser(email, pw)

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

//@Composable
//fun LoginPage(){
//    val user= viewModel<UserPage>()

//    if (user.userid.value.isEmpty()){
//        LoginView(user)
//    } else{
//        Text(text = user.userid.value)
//    }
//}
//
//@Composable
//fun MainScaffoldView(){
//    Scaffold(
//        bottomBar = {},
//        topBar = {},
//        content = {}
//    )
//
//}
//
//
//@Composable
//fun LoginView(user: UserPage){
//    var email by remember {
//        mutableStateOf("")
//    }
//    var password by remember {
//        mutableStateOf("")
//    }
//
//    val fAuth= Firebase.auth
//
//    var info by remember {
//        mutableStateOf("")
//    }
//
//    Column (
//  modifier = Modifier
//      .fillMaxWidth()
//      .height(200.dp),
//      verticalArrangement = Arrangement.SpaceEvenly,
//      horizontalAlignment = Alignment.CenterHorizontally,
//
//            ){
//      OutlinedTextField(value = email,
//          onValueChange = {email= it},
//          label = { Text(text = "Email")})
//
//        OutlinedTextField(value = password,
//          onValueChange = {password= it},
//          label = { Text(text = "Password")},
//        visualTransformation = PasswordVisualTransformation())
//
//        OutlinedButton(onClick = {
////             fAuth
////            .signInWithEmailAndPassword(email,password)
////            .addOnSuccessListener {
////                info = "You are logged with account ${it.user!!.email.toString()}"
//
//                user.loginUser(email,password)
//
//
//        }) {
//            Text(text ="log in")
//        }
////        Text(text =info)
//    }
//}