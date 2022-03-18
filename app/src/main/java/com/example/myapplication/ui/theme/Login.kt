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


@Composable
fun LoginView(){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
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

        OutlinedButton(onClick = { /*login to backend*/}) {
            Text(text ="Login")

        }
    }
}