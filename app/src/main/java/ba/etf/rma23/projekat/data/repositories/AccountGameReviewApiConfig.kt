package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AccountGameReviewApiConfig {
    var baseUrl = "https://rma23ws.onrender.com"

    val retrofit : AccountApiReview = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapter(GameReview::class.java, GameReviewDeserialization()).create()))
        .build()
        .create(AccountApiReview::class.java)

}