package com.example.pokedex.Data.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val apiService: ApiService by lazy {
        // by delegates the initialization fo apiService to lazy
        // lazy keep track of the where the property has been intitialized
        // by lazy -> ensures that the instance is created only the first time is accesed

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    // Retrofit builds connection to the pokeApi using the connection string with .baseUrl
    // using addConverterFactory with gson we convert the json into a java kind of interface
}