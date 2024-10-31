package com.example.pokedex

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon")
    suspend fun getAllPokemons(): Response<PokemonResult>
    // suspends is kind of like an await in React

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name:String): Response<PokemonDetailsModel>
}
