package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
    Column (
        modifier = Modifier.padding(10.dp, 60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        DessertView()
      Icon( painter = painterResource(R.drawable.ic_user),
          contentDescription = "",
          modifier = Modifier.clickable { navController.navigate(USER_INFO)}
      )

}}