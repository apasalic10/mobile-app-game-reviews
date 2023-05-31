package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

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
}