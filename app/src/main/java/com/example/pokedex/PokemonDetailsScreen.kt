package com.example.pokedex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun PokemonDetailsScreen(navController: NavController, pokeName: String?){
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Pokemon details for $pokeName")
        Button(onClick =  { navController.navigateUp() }){
            Text(text = "Go HomeScreen")
        }
    }
}