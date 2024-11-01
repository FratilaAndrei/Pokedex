package com.example.pokedex.Data.API

import com.example.pokedex.Data.Models.PokemonDetailsModel
import com.example.pokedex.Data.Models.PokemonResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon")
    suspend fun getAllPokemons(): Response<PokemonResult>
    // suspends is kind of like an await in React

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name:String): Response<PokemonDetailsModel>
    // Response -> wrapper for the data returned by call, gives us acess to resDetails and the resBody
}
