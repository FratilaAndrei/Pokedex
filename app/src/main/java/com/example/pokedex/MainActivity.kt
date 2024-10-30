package com.example.pokedex

import android.net.Uri
import android.net.http.UrlRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokedexApp()
                }
            }
        }
    }
}


@Composable
fun PokedexApp(){
    val navController = rememberNavController()
    val repository = PokemonRepository(apiService = RetrofitClient.apiService)
    val viewModelPokeList = PokemonListViewModel(repository = repository)
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
//            Pokedex(){
//                navController.navigate(Screen.PokemonDetails.route)
//            }
                Pokedex(navController, viewModel = viewModelPokeList)
        }
        composable(Screen.PokemonDetails.route) { backStackEntry ->
            PokemonDetailsScreen(navController, backStackEntry.arguments?.getString("pokeName"))
        }
    }
}



