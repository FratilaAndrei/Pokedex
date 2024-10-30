package com.example.pokedex


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pokedex(navController: NavController, viewModel: PokemonListViewModel) {
    val pokemons = viewModel.pokemonListState.collectAsStateWithLifecycle().value.results

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Pokedex", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp), textAlign = TextAlign.Center, fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        SearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            placeholder = {
                Text(
                    text = "Search for a Pokemon",
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        ) {}
        Spacer(modifier = Modifier.height(12.dp))
         LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(pokemons) { pokemon ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .border(BorderStroke(2.dp, Color.Cyan), shape = RoundedCornerShape(30))
                    ) {
                        Text(
                            text = pokemon.name,
                            modifier = Modifier
                                .padding(8.dp)
                                .wrapContentSize().clickable { navController.navigate(Screen.PokemonDetails.createRoute(pokemon.name))  },
                            fontSize = 18.sp
                        )
                    }

                }
            })
    }


}


