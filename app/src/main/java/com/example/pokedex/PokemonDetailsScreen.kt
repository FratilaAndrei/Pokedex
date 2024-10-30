package com.example.pokedex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun PokemonDetailsScreen(navController: NavController, pokeName: String, viewModel: PokemonViewModel){

    val pokemon = viewModel.pokemonState.collectAsStateWithLifecycle().value


    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick =  { navController.navigateUp() }){
            Text(text = "Back")
        }
        Text(text = "Pokemon details for ${pokemon.name}")
        Text(text = "Height: ${pokemon.height}")
        Text(text = "Weight: ${pokemon.weight}")
        Column {
            pokemon.types.forEach{
                type ->
                Text(text = "Type: ${type.type.name}")
            }
        }
        Column {
            pokemon.stats.forEach{
                    stat ->
                Column{
                    Text(text = "Stat: ${stat.stat.name}")
                    Text(text = "BaseStat: ${stat.base_stat} ")
                    Text(text = "Effort: ${stat.effort} ")
                }
            }
        }
    }
}