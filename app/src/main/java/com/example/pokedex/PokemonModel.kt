package com.example.pokedex

data class PokemonResult(val results: List<PokemonList> = listOf())
data class PokemonList(
    val name: String,
    val url: String
)
