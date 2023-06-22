package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AccountApi {

    @Headers(
        "Content-Type: application/json"
    )
    @GET("/account/{aid}/games")
    suspend fun getSavedGames(
        @Path("aid") aid : String = AccountGamesRepository.getHash()
    ) : Response<List<Game>>

    @Headers(
        "Content-Type: application/json"
    )
    @POST("/account/{aid}/game")
    suspend fun saveGame(
        @Body body: GameRequest,
        @Path("aid") aid: String = AccountGamesRepository.getHash(),
    ): GameResponse

    @DELETE("/account/{aid}/game/{gid}")
    suspend fun removeGame(
        @Path("aid") aid: String,
        @Path("gid") gid: Int
    )

    @GET("/game/{gid}/gamereviews")
    suspend fun getReviewsForGame(
        @Path("gid") gid:Int
    ):Response<List<GameReview>>

    @POST("/account/{aid}/game/{gid}/gamereview")
    suspend fun sendReview(
        @Path("gid") gid: Int,
        @Body body: GameReviewRequest,
        @Path("aid") aid: String = AccountGamesRepository.getHash()
    ):Response<GameReview>

}