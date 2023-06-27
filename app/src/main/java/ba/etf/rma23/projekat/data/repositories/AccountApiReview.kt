package ba.etf.rma23.projekat.data.repositories

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AccountApiReview {
    @Headers(
        "Content-Type: application/json"
    )
    @GET("/game/{gid}/gamereviews")
    suspend fun getReviewsForGame(
        @Path("gid") gid:Int
    ): Response<List<GameReview>>
}