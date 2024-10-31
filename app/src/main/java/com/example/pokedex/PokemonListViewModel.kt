package com.example.pokedex

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PokemonListViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemonListState = MutableStateFlow<PokemonResult>(PokemonResult(listOf()))
    private val _searchedPokemonInput = MutableStateFlow<String>("")
    // MutableStateFlow are used in ViewModel and Repo because they allow multiple composable to listen to their change
    // MutableState is used inside Composable for basic changes of UI elements because it doesnt support multiple listeners
    // unaccesible by View

    private var originalPokemonList: List<PokemonList> = listOf()

    var pokemonListState: StateFlow<PokemonResult> = _pokemonListState
    var searchedPokemonInput: StateFlow<String> = _searchedPokemonInput
    // this copies the mutableState as an immutable
    // accesible by View

    private fun getPokemonListData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = repository.getPokemonList()
                originalPokemonList = list.results
                _pokemonListState.value = list
                Log.d("AndreiPokemons", list.toString())
            } catch (e: Exception) {
                Log.e("EroarePokemons", "Erorr Fetching Pokemons ${e.message}")
            }
        }
    }

    fun emitSearchPokemon(searchedPoke: String) {
        _searchedPokemonInput.value = searchedPoke
    }


    init {
        getPokemonListData()

        viewModelScope.launch {
            // creates a new coroutine
            // a coroutine is kind of like branch of the main thread, its used to not block the main thread
            _searchedPokemonInput.collectLatest {
                // collect -> collects each Value emited from the Flow, running the code for each new value
                // collectLatest -> it will cancel any previous unfinished collection if a new value is emited

                // example for collect would be b-u-l-b  -> will have Colect_b, collect_u, collect_l, collect_b
                // collectLatest would be b-u-l-b -> ending in latest collect which is bulb

                    searchInput ->
//                delay(1000)
                if (searchInput.isEmpty()) {
//                    _pokemonListState.value = repository.getPokemonList()
                    _pokemonListState.value = PokemonResult(originalPokemonList)
                } else {
                    _pokemonListState.value =
                        PokemonResult(originalPokemonList.filter { pokemonFound ->
                            pokemonFound.name.lowercase().contains(searchInput.lowercase())
                        })
                }
            }
        }
    }
}