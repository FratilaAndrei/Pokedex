package com.example.pokedex


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pokedex(viewModel: PokemonData) {
    val pokemonList = remember {
        mutableStateOf(
            listOf<PokemonDetails>(
                viewModel.bulbasaur,
                viewModel.squirtle,
                viewModel.charmander
            )
        )
    }
    
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
        PokemonSimpleData(pokemonList = pokemonList.value)
    }
}


