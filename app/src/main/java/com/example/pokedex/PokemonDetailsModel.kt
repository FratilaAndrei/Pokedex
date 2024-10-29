package com.example.pokedex

//data class PokemonStat(
//    val name: String,
//    val url: String
//)
//
//data class PokemonStats(
//    val base_stat: Int,
//    val effort: Int,
//    val stat: List<PokemonStat>
//)
//
//data class PokemonDetailsModel(
//    val id: Int,
//    val name: String,
//    val weight: Int,
//    val height: Int,
//    val type: String,
//    val stats: List<PokemonStats>
//)

data class PokemonDetailsModel(
    val height: Int,
    val id: Int,
    val name: String,
    val stats: List<PokemonStatsModel>,
    val types: List<PokemonTypesModel>
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

