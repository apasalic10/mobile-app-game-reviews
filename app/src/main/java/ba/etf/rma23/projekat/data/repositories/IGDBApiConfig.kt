package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object IGDBApiConfig {

    val baseUrl = "https://api.igdb.com/v4/"

    val retrofit : IGDBApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapter(Game::class.java, GameDeserialization()).create()))
        .build()
        .create(IGDBApi::class.java)
}