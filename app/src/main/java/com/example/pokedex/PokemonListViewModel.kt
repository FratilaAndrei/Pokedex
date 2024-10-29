package com.example.pokedex

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(private val repository: PokemonRepository): ViewModel() {
    private val _pokemonListState = MutableStateFlow<PokemonResult>(PokemonResult(listOf()))

    // Initialised this as null for empty object

    // unaccesible by View

    var pokemonListState: StateFlow<PokemonResult> = _pokemonListState
    // this copies the mutableState as an immutable

    // accesible by View

//    init {
//        CoroutineScope(Dispatchers.IO).launch {
//           val list = repository.getPokemonList()
//            _pokemonListState.value= list
//            Log.d("Andrei", list.toString() )
//        }
//    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list =  repository.getPokemonList()
                _pokemonListState.value = list
                Log.d("AndreiPokemons", list.toString())
            }catch(e:Exception){
                Log.e("EroarePokemons", "Erorr Fetching Pokemons ${e.message}")
            }
        }
    }
}