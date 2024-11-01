package com.example.pokedex.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {

    }

    // Implementarea e pe MainActivity!

//    companion object{
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext, AppDatabase::class.java,
//                    "pokemons_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }







//    val db = Room.databaseBuilder(
//        applicationContext,
//        AppDatabase::class.java, "database-name"
//    ).build()
}
