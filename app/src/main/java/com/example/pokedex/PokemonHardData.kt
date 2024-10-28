package com.example.pokedex

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class PokemonStat(
    val name: String,
    val url: String
    // val url: String - ask about this
)

data class PokemonStats(
    val base_stat: Int,
    val effort: Int,
    val stat: List<PokemonStat>
)

data class PokemonDetails(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val type: String,
    val stats: List<PokemonStats>
)

class PokemonData: ViewModel() {
private val _bulbasaur = PokemonDetails(
    id = 1,
    name = "Bulbasaur",
    weight = 69,
    height = 7,
    type = "Grass/Poison",
    stats = listOf(
        PokemonStats(
            base_stat = 45,
            effort = 0,
            stat = listOf(PokemonStat(name = "HP", url = "https://pokeapi.co/api/v2/stat/1/"))
        ),
        PokemonStats(
            base_stat = 49,
            effort = 0,
            stat = listOf(PokemonStat(name = "Attack", url = "https://pokeapi.co/api/v2/stat/2/"))
        )
    )
)
    val bulbasaur: PokemonDetails = _bulbasaur
    // Philip Lanker


val charmander = PokemonDetails(
    id = 4,
    name = "Charmander",
    weight = 85,
    height = 6,
    type = "Fire",
    stats = listOf(
        PokemonStats(
            base_stat = 39,
            effort = 0,
            stat = listOf(PokemonStat(name = "HP", url = "https://pokeapi.co/api/v2/stat/1/"))
        ),
        PokemonStats(
            base_stat = 52,
            effort = 0,
            stat = listOf(PokemonStat(name = "Attack", url = "https://pokeapi.co/api/v2/stat/2/"))
        )
    )
)

val squirtle = PokemonDetails(
    id = 7,
    name = "Squirtle",
    weight = 90,
    height = 5,
    type = "Water",
    stats = listOf(
        PokemonStats(
            base_stat = 44,
            effort = 0,
            stat = listOf(PokemonStat(name = "HP", url = "https://pokeapi.co/api/v2/stat/1/"))
        ),
        PokemonStats(
            base_stat = 48,
            effort = 0,
            stat = listOf(PokemonStat(name = "Defense", url = "https://pokeapi.co/api/v2/stat/3/"))
        )
    )
)
}

