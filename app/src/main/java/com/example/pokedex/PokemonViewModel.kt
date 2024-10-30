package com.example.pokedex

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemonState = MutableStateFlow<PokemonDetailsModel>(
        PokemonDetailsModel(
            0, 0, "", listOf(), listOf(), 0
        )
    )
    var pokemonState: StateFlow<PokemonDetailsModel> = _pokemonState

    private fun getPokemonData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pokemon = repository.getPokemon()
                _pokemonState.value = pokemon
                Log.d("UniqPokemon", pokemon.toString())
            } catch (e: Exception) {
                Log.e("PokemonViewModel", "Error fetching pokemon ${e.message}")
            }
        }
    }



    init {
        getPokemonData()
    }

}