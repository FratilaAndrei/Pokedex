package com.example.pokedex

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokedex.ViewModels.FavoritePokemonsViewModel

@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritePokemonsViewModel) {

    val favorites = viewModel.favoritePokemons.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getFavoritesPokemons()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(onClick = { navController.navigate(Screen.Home.route) }) {
            Text(text = "Home")
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(favorites) { pokemons ->
                Box(modifier = Modifier.padding(8.dp)) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(BorderStroke(2.dp, Color.Cyan), shape = RoundedCornerShape(20))
                            .padding(horizontal = 20.dp)
                            .padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = pokemons.name,
                            modifier = Modifier
                                .padding(8.dp)
                                .wrapContentHeight()
                                .width(150.dp)
                                .clickable {
                                    navController.navigate(
                                        Screen.PokemonDetails.createRoute(
                                            pokemons.name
                                        )
                                    )
                                },
                            fontSize = 18.sp
                        )
                        Icon(
                            Icons.Default.Close, contentDescription = "AddFavoriteButton",
                            modifier = Modifier.clickable {
                                viewModel.deleteFavoritePokemon(pokemons)
                            },
                            tint = Color.Red
                        )
                    }
                }

            }
        }
    }
}

