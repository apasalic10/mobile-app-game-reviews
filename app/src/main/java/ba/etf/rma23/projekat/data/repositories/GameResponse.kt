package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody

data class GameResponse(
    @SerializedName("igdb_id") val id: Int,
    @SerializedName("name") val name: String,
)
