package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DessertViewModel: ViewModel() {

    var dessert = mutableStateOf("")
    val api: DessertAPI by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://random-data-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    fun getRandomDessertData() {
        viewModelScope.launch {
            val resp = api.getRandomDessert().awaitResponse()

            if (resp.isSuccessful) {
                val data: JsonObject? = resp.body()
                dessert.value = data!!.get("flavor").asString

            } else {
                Log.d("***", "ei tanan")
            }

        }
    }
}