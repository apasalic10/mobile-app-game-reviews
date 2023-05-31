package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody

data class GameRequest(
    @SerializedName("game") val game : GameResponse
)
