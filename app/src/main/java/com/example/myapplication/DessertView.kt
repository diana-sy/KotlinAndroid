package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable

fun DessertView() {
    val dessertVM = viewModel<DessertViewModel>()

    Column() {


        Button(onClick = { dessertVM.getRandomDessertData() }) {
            Text(text = "Press to chose your dessert for today")
        }
        Text(text = dessertVM.dessert.value)
    }

}