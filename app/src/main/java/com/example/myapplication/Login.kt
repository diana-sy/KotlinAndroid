package com.example.myapplication.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.UserPage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun LoginPage(){
    val user= viewModel<UserPage>()
    
    if (user.userid.value.isEmpty()){
        LoginView(user)
    } else{
        Text(text = user.userid.value)
    }
}

@Composable
fun LoginView(user: UserPage){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val fAuth= Firebase.auth

    var info by remember {
        mutableStateOf("")
    }

    Column (
  modifier = Modifier
      .fillMaxWidth()
      .height(200.dp),
      verticalArrangement = Arrangement.SpaceEvenly,
      horizontalAlignment = Alignment.CenterHorizontally,

            ){
      OutlinedTextField(value = email,
          onValueChange = {email= it},
          label = { Text(text = "Email")})

        OutlinedTextField(value = password,
          onValueChange = {password= it},
          label = { Text(text = "Password")},
        visualTransformation = PasswordVisualTransformation())

        OutlinedButton(onClick = {
//             fAuth
//            .signInWithEmailAndPassword(email,password)
//            .addOnSuccessListener {
//                info = "You are logged with account ${it.user!!.email.toString()}"

                user.loginUser(email,password)


        }) {
            Text(text ="log in")

        }
//        Text(text =info)
    }
}