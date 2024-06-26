package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AccountApiConfig {
    var baseUrl = "https://rma23ws.onrender.com"

    val retrofit : AccountApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapter(Game::class.java, AccountDeseralization()).create()))
        .build()
        .create(AccountApi::class.java)
}