package com.example.pokedex

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PokemonListViewModel(private val repository: PokemonRepository): ViewModel() {
    private val _pokemonListState = MutableStateFlow<PokemonResult>(PokemonResult(listOf()))
    private val _searchedPokemonInput = MutableStateFlow<String>("")
    // unaccesible by View

    private var originalPokemonList: List<PokemonList> = listOf()

    var pokemonListState: StateFlow<PokemonResult> = _pokemonListState
    var searchedPokemonInput: StateFlow<String> = _searchedPokemonInput
    // this copies the mutableState as an immutable
    // accesible by View

     private fun getPokemonListData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list =  repository.getPokemonList()
                originalPokemonList = list.results
                _pokemonListState.value = list
                Log.d("AndreiPokemons", list.toString())
            }catch(e:Exception){
                Log.e("EroarePokemons", "Erorr Fetching Pokemons ${e.message}")
            }
        }
    }

    fun emitSearchPokemon(searchedPoke:String){
        _searchedPokemonInput.value = searchedPoke
    }


    init {
        getPokemonListData()

        viewModelScope.launch{
            _searchedPokemonInput.collect {
//                input ->
//                delay(1000)
//                _pokemonListState.value.results.find {
//                    pokemonFound -> pokemonFound.name.lowercase().contains(input.lowercase())
//                }
                    searchInput ->
                // Use with Filter
                if (searchInput.isEmpty()) {
//                    _pokemonListState.value = repository.getPokemonList()
                    _pokemonListState.value = PokemonResult(originalPokemonList)
                } else {
                    _pokemonListState.value =
//                        PokemonResult(pokemonListState.value.results.filter { pokemonFound ->
//                            pokemonFound.name.lowercase().contains(searchInput.lowercase())
//                        })
                    PokemonResult(originalPokemonList.filter {
                        pokemonFound -> pokemonFound.name.lowercase().contains(searchInput.lowercase())
                    })
                }
            }
        }
    }
}