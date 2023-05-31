package ba.etf.rma23.projekat.data.repositories

import android.annotation.SuppressLint
import ba.etf.rma23.projekat.Game
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.lang.reflect.Type

class AccountDeseralization : JsonDeserializer<Game> {

    @SuppressLint("SimpleDateFormat")
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Game {
        val jsonObject = json?.asJsonObject ?: JsonObject()
        val igdb_id = jsonObject.get("igdb_id")?.asInt ?: 0

        var game = Game(igdb_id,"", "","",0.0,"","","","","","", emptyList())

        runBlocking{
            val gameFromResponseBody = CoroutineScope(Dispatchers.IO).async {
                GamesRepository.getGameById(igdb_id)
            }
            val response = gameFromResponseBody.await()

            if(!response.isNullOrEmpty()){
                game = response[0]
            }

        }

        return game

    }


}