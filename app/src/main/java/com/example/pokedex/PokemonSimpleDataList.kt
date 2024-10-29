package com.example.pokedex

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonSimpleData(pokemonList: List<PokemonDetailsModel>){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            items(pokemonList) { pokemon ->
                Box(modifier = Modifier
                    .padding(8.dp)
                    .border(BorderStroke(2.dp, Color.Cyan), shape = RoundedCornerShape(30))) {
                    Text(
                        text = pokemon.name,
                        modifier = Modifier
                            .padding(8.dp)
                            .wrapContentSize(),
                        fontSize = 18.sp
                    )
                }
            }
        })
}