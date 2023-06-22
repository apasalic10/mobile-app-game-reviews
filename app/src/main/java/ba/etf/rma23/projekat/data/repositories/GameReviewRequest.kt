package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName

data class GameReviewRequest(
    @SerializedName("rating") val rating: Int?,
    @SerializedName("review") val review: String?
)
