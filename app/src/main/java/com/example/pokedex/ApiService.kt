package com.example.pokedex

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
//    @GET("https://pokeapi.co/api/v2/pokemon")
    @GET("pokemon")
    suspend fun getAllPokemons(): Response<PokemonResult>
    // suspends is kind of like an await in React

//    @GET("https://pokeapi.co/api/v2/pokemon/ivysaur")
//    suspend fun getPokemon(): Response<PokemonDetailsModel>
}
