package com.example.pokedex


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun Pokedex(navController: NavController, viewModel: PokemonListViewModel) {
    val pokemons = viewModel.pokemonListState.collectAsStateWithLifecycle().value.results
    Log.d("PokeAll", pokemons.toString())
//    var searchInput by remember { mutableStateOf("") }
//    var search = viewModel.searchedPokemonInput.collectAsStateWithLifecycle().value

    val searchQuery by viewModel.searchedPokemonInput.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Pokedex", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp), textAlign = TextAlign.Center, fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
//            value = search,
            value = searchQuery,
            singleLine = true,
            onValueChange = {
//                search = it
                newSearch -> viewModel.emitSearchPokemon(newSearch)
            })
        Spacer(modifier = Modifier.height(12.dp))

//        Text(text = "Found Pokemon: ${} ")
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(pokemons) { pokemon ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .border(
                                BorderStroke(2.dp, Color.Cyan),
                                shape = RoundedCornerShape(30)
                            )
                    ) {
                        Text(
                            text = pokemon.name,
                            modifier = Modifier
                                .padding(8.dp)
                                .wrapContentSize()
                                .clickable {
                                    navController.navigate(
                                        Screen.PokemonDetails.createRoute(
                                            pokemon.name
                                        )
                                    )
                                },
                            fontSize = 18.sp
                        )
                    }

                }
            })
    }
}


