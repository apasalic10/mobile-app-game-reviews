package ba.etf.rma23.projekat.data.repositories

import android.annotation.SuppressLint
import ba.etf.rma23.projekat.Game
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GameDeserialization : JsonDeserializer<Game> {

    private val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN)

    @SuppressLint("SimpleDateFormat")
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Game {
        val jsonObject = json?.asJsonObject ?: JsonObject()

        return getGame(jsonObject)
    }

    private fun getGame(jsonObject: JsonObject): Game{
        val id = jsonObject.get("id")?.asInt ?: 0
        val title = jsonObject.get("name")?.asString ?: ""

        //Get platform
        val arrayOfPlatforms = jsonObject.getAsJsonArray("platforms")
        val platform = arrayOfPlatforms?.get(0)?.asJsonObject?.get("name")?.asString ?: ""


        //Get release date
        val releaseDate = getDateString(jsonObject.get("first_release_date")?.asLong ?: 0)


        //Get rating
        val rating = jsonObject.get("rating")?.asDouble ?: 0.0


        //Get cover image
        val coverObject = jsonObject.getAsJsonObject("cover")
        val coverImage = coverObject?.get("url")?.asString ?: ""


        //Get ESRB rating
        val ageRatingsArray = jsonObject.getAsJsonArray("age_ratings")
        val esrbObject = ageRatingsArray
            ?.get(0)?.asJsonObject

        var esrbRating : String = ""
        if((esrbObject?.get("category")?.asInt ?: 0) == 2){
            esrbRating = ESRBRating.getGameRating(esrbObject?.get("rating")?.asInt!!).toString()
        }
        else if((esrbObject?.get("category")?.asInt ?: 0) == 1){
            esrbRating = ESRBRating.getGameRating(esrbObject?.get("rating")?.asInt!!).toString()
        }


        //Get developer
        val involvedCompaniesArray = jsonObject.getAsJsonArray("involved_companies")
        val companyObject = involvedCompaniesArray
            ?.firstOrNull { it.asJsonObject.get("developer").asBoolean }
            ?.asJsonObject
            ?.getAsJsonObject("company")
        val developer = companyObject?.get("name")?.asString ?: ""


        //Get publisher
        val companyObject1 = involvedCompaniesArray
            ?.firstOrNull { it.asJsonObject.get("publisher").asBoolean }
            ?.asJsonObject
            ?.getAsJsonObject("company")
        val publisher = companyObject1?.get("name")?.asString ?: ""


        //Get genre
        val genresArray = jsonObject.getAsJsonArray("genres")
        val genre = genresArray?.get(0)?.asJsonObject?.get("name")?.asString ?: ""

        //Get description
        val description = jsonObject.get("summary")?.asString ?: ""

        return Game(id, title, platform, releaseDate, rating, coverImage, esrbRating,
            developer, publisher, genre, description, listOf())
    }

    private fun getDateString(time: Long) : String = simpleDateFormat.format(time * 1000L)
}