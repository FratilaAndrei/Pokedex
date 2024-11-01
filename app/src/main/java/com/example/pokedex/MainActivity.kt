package com.example.pokedex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.pokedex.Data.API.RetrofitClient
import com.example.pokedex.Data.Database.AppDatabase
import com.example.pokedex.Data.PokemonRepository
import com.example.pokedex.ViewModels.FavoritePokemonsViewModel
import com.example.pokedex.ViewModels.PokemonListViewModel
import com.example.pokedex.ViewModels.PokemonViewModel
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
fun PokedexApp() {
    val navController = rememberNavController()
    val repository = PokemonRepository(apiService = RetrofitClient.apiService)

    val viewModelPokeList = PokemonListViewModel(repository = repository)
    val viewModelUniquePoke = PokemonViewModel(repository = repository)

    val context = LocalContext.current
    val database = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "database-name"
    ).build()

    Log.d("TestDB", database.toString())

    val viewModelFavoritePoke = FavoritePokemonsViewModel(dao = database.pokemonDao())

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            Pokedex(
                navController = navController,
                viewModel = viewModelPokeList,
                viewModelFavoritePoke = viewModelFavoritePoke
            )
        }
        composable(Screen.PokemonDetails.route) { backStackEntry ->
            PokemonDetailsScreen(
                navController,
                backStackEntry.arguments?.getString("pokeName") ?: "",
                viewModelUniquePoke
            )
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(navController, viewModelFavoritePoke)
        }
    }
}





