package com.example.myapplication

import retrofit2.http.GET

interface DessertAPI {
    @GET("/api/dessert/random_dessert")
    fun getRandomDessert(){

    }
}