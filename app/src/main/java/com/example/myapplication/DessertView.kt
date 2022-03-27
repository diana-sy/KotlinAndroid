package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable

fun DessertView() {
    val dessertVM = viewModel<DessertViewModel>()
    val color1 = Color(0xffed4956)

    Column(

    ) {


        Button(
            modifier = Modifier
                .padding(5.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = color1),

            onClick = { dessertVM.getRandomDessertData() }) {
            Text(text = "Press to chose your dessert for today")
        }
        Text(text = dessertVM.dessert.value)
    }

}