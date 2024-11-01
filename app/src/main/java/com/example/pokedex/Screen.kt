package com.example.pokedex

sealed class Screen(val route:String) {
    // sealed -> all subclasses like Home / Favorites must be defined in same class
    // Ensures navigation is closed and prevents introduction of other subclasses elsewhere
    object Home : Screen("home")
    object PokemonDetails : Screen("pokemonDetails/{pokeName}"){
        fun createRoute(pokeName:String) = "pokemonDetails/$pokeName"
    }
    object Favorites : Screen("favorites")
}