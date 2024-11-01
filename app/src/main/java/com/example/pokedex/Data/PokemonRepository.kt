package com.example.pokedex.Data

import android.util.Log
import com.example.pokedex.Data.API.ApiService
import com.example.pokedex.Data.Models.PokemonDetailsModel
import com.example.pokedex.Data.Models.PokemonResult

class PokemonRepository(private val apiService: ApiService) {
    suspend fun getPokemonList(): PokemonResult {
        val res = apiService.getAllPokemons()
        Log.d("Pokemons", res.toString())
        return res.body()!!
        // !! tells that i am sure the outcome wont be empty because if its empty it will break
        // !! -> not null assertion operator

    }

    suspend fun getPokemon(name:String): PokemonDetailsModel {
        val res = apiService.getPokemon(name)
        return res.body()!!
    }
}