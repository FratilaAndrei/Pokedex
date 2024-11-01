package com.example.pokedex.Data.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePokemon(pokemon: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity")
    fun getAllFavoritePokemons(): List<PokemonEntity>

    @Query("DELETE FROM PokemonEntity WHERE PokemonEntity.name = :pokemonName")
    suspend fun deleteFavorite(pokemonName: String)
}

// Dao defined methods to interact with the db