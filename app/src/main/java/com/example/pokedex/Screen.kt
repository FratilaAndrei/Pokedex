package com.example.pokedex

sealed class Screen(val route:String) {
    object Home : Screen("home")
    object PokemonDetails : Screen("pokemonDetails/{pokeName}"){
        fun createRoute(pokeName:String) = "pokemonDetails/$pokeName"
    }
}