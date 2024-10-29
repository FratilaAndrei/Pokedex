package com.example.pokedex

import android.util.Log

class PokemonRepository(private val apiService: ApiService) {

    suspend fun getPokemonList(): PokemonResult{
        val res = apiService.getAllPokemons()
        Log.d("Pokemons", res.toString())
        return res.body()!!
    }

//    suspend fun getPokemon(): PokemonDetailsModel{
//        val res = apiService.getPokemon()
//        return res.body()!!
//    }
}