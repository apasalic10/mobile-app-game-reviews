package ba.etf.rma23.projekat.data.repositories

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class GameReviewDeserialization : JsonDeserializer<GameReview>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GameReview {
        val jsonObject = json?.asJsonObject ?: JsonObject()
        val id = jsonObject.get("id")?.asInt ?: 0
        val rating = jsonObject.get("rating")?.asInt ?: 0
        val review = jsonObject.get("review")?.asString ?: ""
        val username = jsonObject.get("student")?.asString ?: ""
        val timestamp = jsonObject.get("timestamp")?.asString ?: ""


        return GameReview(id,rating,review,0,true,username,timestamp)
    }
}