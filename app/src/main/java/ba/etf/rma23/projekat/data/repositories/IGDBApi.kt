package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import com.example.rma23_19079_videogames.BuildConfig
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface IGDBApi {
    @Headers(
        "Client-ID: ${BuildConfig.ClientID}",
        "Authorization: ${BuildConfig.Authorization}",
        "Content-Type: application/json"
    )
    @POST("games")
        suspend fun getGamesByName(
        @Query("search") name: String,
        @Query("fields") fields: String = "id, name, platforms.name, first_release_date, age_ratings.rating, age_ratings.category, cover.url, involved_companies, involved_companies.developer, involved_companies.publisher, involved_companies.company.name, genres.name, summary, rating"
        ): Response<List<Game>>


    @Headers(
        "Client-ID: ${BuildConfig.ClientID}",
        "Authorization: ${BuildConfig.Authorization}",
        "Content-Type: application/json"
    )
    @POST("games")
    suspend fun getGameById(
        @Body req: RequestBody
    ): Response<List<Game>>
}