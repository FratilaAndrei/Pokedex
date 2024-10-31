package com.example.pokedex

data class PokemonDetailsModel(
    val height: Int,
    val id: Int,
    val name: String,
    val stats: List<PokemonStatsModel>,
    val types: List<PokemonTypesModel>,
    val weight: Int,
    val sprites: PokemonSpriteModel
)


data class PokemonStatsModel(
    val base_stat: Int,
    val effort: Int,
    val stat: PokemonStatModel
)

data class PokemonStatModel(
    val name: String,
    val url: String,
)

data class PokemonTypesModel(
    val slot: Int,
    val type: PokemonTypeModel
)

data class PokemonTypeModel(
    val name: String,
    val url: String,
)

data class PokemonSpriteModel(
    val front_default:String
)

