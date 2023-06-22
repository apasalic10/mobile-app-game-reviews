package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GameReviewsRepository {
    suspend fun sendOfflineReviews(context: Context):Int{
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            val result = db.gameReviewDao().getAllOfflineGames()
            var brSpremljenihNaServis = 0

            for (game: GameReview in result) {
                val response = AccountApiConfig.retrofit.sendReview(game.igdb_id,GameReviewRequest(game.rating,game.review))
                if (response.isSuccessful) {
                    brSpremljenihNaServis += 1;
                    db.gameReviewDao().updateOfflineGameReview(game.id)
                } else {
                    return@withContext brSpremljenihNaServis
                }
            }

            return@withContext brSpremljenihNaServis;
        }
    }


    suspend fun getReviewsForGame(igdb_id:Int):List<GameReview>{
        return withContext(Dispatchers.IO){
            try{
                val response = AccountGameReviewApiConfig.retrofit.getReviewsForGame(igdb_id)
                if(response.isSuccessful){
                    val gameReviewList : ArrayList<GameReview> = ArrayList<GameReview>()
                    for(gameReview: GameReview in response.body()!!){
                        gameReview.igdb_id = igdb_id
                        gameReviewList.add(gameReview)
                    }
                    return@withContext gameReviewList
                } else{
                    return@withContext emptyList()
                }
            } catch (exception: Exception){
                exception.printStackTrace()
                return@withContext emptyList()
            }
        }

    }


    suspend fun sendReview(context: Context,gameReview: GameReview):Boolean{

        return withContext(Dispatchers.IO){
            try{
                val listaOmiljenjih = AccountGamesRepository.getSavedGames()
                val game = GamesRepository.getGameById(gameReview.igdb_id)?.get(0)
                if(!listaOmiljenjih.contains(game)){

                    if (game != null) {
                        AccountGamesRepository.saveGame(game)
                    }
                }

                val response = AccountApiConfig.retrofit.sendReview(gameReview.igdb_id,GameReviewRequest(gameReview.rating,gameReview.review))
                if(response.isSuccessful){
                    return@withContext true
                } else{

                    val db = AppDatabase.getInstance(context)
                    db.gameReviewDao().insertGameReview(gameReview)

                    return@withContext false

                }
            } catch (exception: Exception){
                val db = AppDatabase.getInstance(context)
                db.gameReviewDao().insertGameReview(gameReview)
                return@withContext false
            }
        }
    }

    /*
    return withContext(Dispatchers.IO){
            try{
                val response = AccountApiConfig.retrofit.sendReview(gameReview.igdb_id,GameReviewRequest(gameReview.rating,gameReview.review))
                if(response.isSuccessful){
                    return@withContext true
                } else{
                    val listaOmiljenjih = AccountGamesRepository.getSavedGames()
                    val game = GamesRepository.getGameById(gameReview.igdb_id)?.get(0)

                    if(!listaOmiljenjih.contains(game)){
                        if (game != null) {
                            AccountGamesRepository.saveGame(game)
                        }
                    }


                    return@withContext false

                }
            } catch (exception: Exception){
                exception.printStackTrace()
                return@withContext false
            }
        }
     */

    suspend fun getOfflineReviews(context: Context):List<GameReview>{

        return withContext(Dispatchers.IO){
            try {
                val db = AppDatabase.getInstance(context)
                val result =  db.gameReviewDao().getAllOfflineGames()
                return@withContext result
            } catch (e: Exception){
                return@withContext emptyList()
            }

        }
    }

}