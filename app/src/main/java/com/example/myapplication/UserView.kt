package com.example.myapplication

import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun UserView(navController: NavHostController, userVM: UserViewModel) {
    OutlinedButton(onClick = {
        userVM.logout()
        navController.navigate(HOME_ROUTE)
    }) {
        Text(text = "Log out")
    }
    DessertView()
}