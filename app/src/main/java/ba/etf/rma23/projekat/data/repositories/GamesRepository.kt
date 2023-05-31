package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody.Companion.toRequestBody

object GamesRepository {

    var listOfGames : List<Game>? = null

    suspend fun getGamesByName(name: String) : List<Game>?{
        return withContext(Dispatchers.IO) {
            try{
                val response = IGDBApiConfig.retrofit.getGamesByName(name)

                if (response.isSuccessful){
                    listOfGames = response.body()
                    return@withContext response.body()
                }else{
                    return@withContext emptyList()
                }
            }catch (e: Exception){
                e.printStackTrace()
                return@withContext emptyList()
            }
        }
    }

    suspend fun getGameById(id: Int) : List<Game>?{
        return withContext(Dispatchers.IO) {
            try{
                val req = "fields id, name, platforms.name, first_release_date, age_ratings.rating, age_ratings.category, cover.url, involved_companies, involved_companies.developer, involved_companies.publisher, involved_companies.company.name, genres.name, summary, rating; where id = $id;"

                val response = IGDBApiConfig.retrofit.getGameById(req.toRequestBody())

                if (response.isSuccessful){
                    return@withContext response.body()
                }else{
                    return@withContext emptyList()
                }
            }catch (e: Exception){
                e.printStackTrace()
                return@withContext emptyList()
            }
        }
    }
}