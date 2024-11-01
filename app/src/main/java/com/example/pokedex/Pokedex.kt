package com.example.pokedex


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pokedex.Data.Database.PokemonEntity
import com.example.pokedex.ViewModels.FavoritePokemonsViewModel
import com.example.pokedex.ViewModels.PokemonListViewModel

@Composable
fun Pokedex(
    navController: NavController,
    viewModel: PokemonListViewModel,
    viewModelFavoritePoke: FavoritePokemonsViewModel
) {
    val pokemons = viewModel.pokemonListState.collectAsStateWithLifecycle().value.results
    Log.d("PokeAll", pokemons.toString())

    val searchQuery by viewModel.searchedPokemonInput.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pokedex", modifier = Modifier,
                textAlign = TextAlign.Center, fontSize = 24.sp
            )
            Icon(Icons.Default.Favorite, contentDescription = "Go to favorites", modifier = Modifier.clickable {
                navController.navigate(Screen.Favorites.route)
            })
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = searchQuery,
            singleLine = true,
            onValueChange = {
                    newSearch ->
                viewModel.emitSearchPokemon(newSearch)
            })
        Spacer(modifier = Modifier.height(12.dp))

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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        ) {
                            Text(
                                text = pokemon.name,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .wrapContentHeight()
                                    .width(150.dp)
                                    .clickable {
                                        navController.navigate(
                                            Screen.PokemonDetails.createRoute(
                                                pokemon.name
                                            )
                                        )
                                    },
                                fontSize = 18.sp
                            )
                            Icon(
                                Icons.Default.Favorite, contentDescription = "AddFavoriteButton",
                                modifier = Modifier.clickable {
                                    val pokemonEntity = PokemonEntity(pokemon.name)
                                    viewModelFavoritePoke.addPokemonToFavorite(pokemonEntity)
//                                    navController.navigate(Screen.Favorites.route)
                                },
                                tint = Color.Red
                            )
                        }
                    }

                }
            })
    }
}



