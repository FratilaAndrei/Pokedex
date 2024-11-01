package com.example.pokedex.Data.Models

data class PokemonResult(val results: List<PokemonList> = listOf())

data class PokemonList(
    val name: String,
    val url: String
)
