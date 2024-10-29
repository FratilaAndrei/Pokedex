package com.example.pokedex

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemonState = MutableStateFlow<PokemonDetailsModel>(
        PokemonDetailsModel(
            0, 0, "", listOf(), listOf()
        )
    )

    var pokemonState: StateFlow<PokemonDetailsModel> = _pokemonState

//    init {
//        CoroutineScope(Dispatchers.IO).launch {
//            val pokemon = repository.getPokemon()
//            _pokemonState.value = pokemon
//            Log.d("Pokemon", pokemon.toString())
//        }
//    }

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val pokemon = repository.getPokemon()
//                _pokemonState.value = pokemon
//                Log.d("Pokemon", pokemon.toString())
//            } catch (e: Exception) {
//                Log.e("PokemonViewModel", "Error fetching pokemon ${e.message}")
//            }
//        }
//    }

}