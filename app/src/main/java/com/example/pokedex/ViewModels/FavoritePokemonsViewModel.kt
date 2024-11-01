package com.example.pokedex.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.Data.Database.PokemonDao
import com.example.pokedex.Data.Database.PokemonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritePokemonsViewModel(private val dao: PokemonDao): ViewModel() {

    private val _favoritePokemons = MutableStateFlow<List<PokemonEntity>>(emptyList())
//    val favoritePokemons: StateFlow<List<PokemonEntity>>  = _favoritePokemons

    val favoritePokemons: StateFlow<List<PokemonEntity>> get()  = _favoritePokemons


    fun addPokemonToFavorite(pokemon: PokemonEntity){
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertFavoritePokemon(pokemon)
            getFavoritesPokemons()
            // refreshing the list after we added
        }
    }

    fun getFavoritesPokemons(){
        viewModelScope.launch(Dispatchers.IO) {
           val favorites = dao.getAllFavoritePokemons()
            _favoritePokemons.value = favorites
        }
    }

    fun deleteFavoritePokemon(pokemon: PokemonEntity){
        viewModelScope.launch(Dispatchers.IO) {
         dao.deleteFavorite(pokemonName = pokemon.name)
            getFavoritesPokemons()
        }
    }

}