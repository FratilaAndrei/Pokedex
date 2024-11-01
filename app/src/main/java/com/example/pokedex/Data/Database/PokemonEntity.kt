package com.example.pokedex.Data.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity -> table in Database
@Entity
data class PokemonEntity(
//    @PrimaryKey val uid:Int,
    @PrimaryKey val name:String
//    @ColumnInfo(name = "isFavorite") val isPokemonFavorite:Boolean
)
