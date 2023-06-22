package ba.etf.rma23.projekat.data.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameReviewDao {
    @Query("SELECT * FROM gamereview WHERE online IS false")
    suspend fun getAllOfflineGames():List<GameReview>


    @Query("UPDATE gamereview SET online = true WHERE id = :id")
    suspend fun updateOfflineGameReview(id: Int)

    @Insert
    suspend fun insertGameReview(review: GameReview)

    @Query("SELECT * FROM gamereview")
    suspend fun getAllReviews():List<GameReview>

}