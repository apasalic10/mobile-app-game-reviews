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

        val rating: Int? = if (!jsonObject.get("rating").isJsonNull) {
            jsonObject.get("rating")?.asInt ?: 0
        } else {
            null
        }

        val review: String? = if (!jsonObject.get("review").isJsonNull) {
            jsonObject.get("review")?.asString ?: ""
        } else {
            null
        }

        val username : String = jsonObject.get("student")?.asString ?: ""

        val timestamp : String = jsonObject.get("timestamp")?.asString ?: ""

        return GameReview(rating, review ,0,true, username, timestamp)
    }

}