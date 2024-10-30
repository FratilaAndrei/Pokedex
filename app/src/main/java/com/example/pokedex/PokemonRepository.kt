package com.example.pokedex

import android.util.Log

class PokemonRepository(private val apiService: ApiService) {
    suspend fun getPokemonList(): PokemonResult{
        val res = apiService.getAllPokemons()
        Log.d("Pokemons", res.toString())
        return res.body()!!
        // !! tells that i am sure the outcome wont be empty because if its empty it will break
    }

    suspend fun getPokemon(name:String): PokemonDetailsModel{
        val res = apiService.getPokemon(name)
        return res.body()!!
    }
}