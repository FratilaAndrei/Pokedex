package com.example.pokedex

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.pokedex.ViewModels.PokemonViewModel

@Composable
fun PokemonDetailsScreen(
    navController: NavController,
    pokeName: String,
    viewModel: PokemonViewModel,
) {

    val pokemon = viewModel.pokemonState.collectAsStateWithLifecycle().value
    val pokeHpValue = pokemon.stats.find { it.stat.name == "hp" }?.base_stat
    val pokeAttackValue = pokemon.stats.find { it.stat.name == "attack" }?.base_stat
    val pokeDefenseValue = pokemon.stats.find { it.stat.name == "defense" }?.base_stat
    viewModel.getPokemonData(pokeName)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(20.dp)
                .border(BorderStroke(2.dp, Color.Cyan), shape = RoundedCornerShape(20))
        ) {
            Column(modifier = Modifier
                .padding(40.dp).wrapContentSize(),
                verticalArrangement = Arrangement.SpaceAround
                ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "$pokeHpValue hp")
                    Text(
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        text = pokemon.name
                    )
                    Column {
                        pokemon.types.forEach { type ->
                            Text(fontSize = 16.sp, text = type.type.name)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                AsyncImage(
                        model = pokemon.sprites.front_default,
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        contentDescription = "$pokeName image",
                        alignment = Alignment.Center
                    )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Defense: $pokeDefenseValue ")
                        Text(text = "Attack Power: $pokeAttackValue ")
                    }
                    Column {
                        Text(text = "Height: ${pokemon.height}")
                        Text(text = "Weight: ${pokemon.weight}")
                    }
                }
            }
        }

    }
}





