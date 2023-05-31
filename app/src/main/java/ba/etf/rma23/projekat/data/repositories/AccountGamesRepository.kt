package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import kotlinx.coroutines.*
import okhttp3.RequestBody.Companion.toRequestBody

object AccountGamesRepository {
    var acHash: String = "230cb97e-fbfb-4298-87fa-50e1283b893b"
    var age: Int? = null

    fun setHash(acHash:String) : Boolean{
        this.acHash = acHash

        if(this.acHash != null){
            return true
        }

        return false
    }

    fun getHash() : String{
        return this.acHash
    }

    suspend fun getSavedGames()  :List<Game>{
        return withContext(Dispatchers.IO) {
            try{
                val response = AccountApiConfig.retrofit.getSavedGames(getHash())

                if (response.isSuccessful){
                    return@withContext response.body()!!
                }else{
                    return@withContext emptyList()
                }
            }catch (e: Exception){
                e.printStackTrace()
                return@withContext emptyList()
            }
        }
    }

    /*
        try{
                val response = AccountApiConfig.retrofit.getSavedGames(getHash())

                if (response.isSuccessful){
                    return@withContext response.body()!!
                }else{
                    return@withContext emptyList()
                }
            }catch (e: Exception){
                e.printStackTrace()
                return@withContext emptyList()
            }

     */

    suspend fun saveGame(game: Game): Game{
        return withContext(Dispatchers.IO){
            try {
                var gamePom = game
                val requestBody = GameResponse(game.id,game.title)
                val response = AccountApiConfig.retrofit.saveGame(GameRequest(requestBody))
                runBlocking{
                    val gameResponse = CoroutineScope(Dispatchers.IO).async {
                        GamesRepository.getGameById(response.id)
                    }
                    val response = gameResponse.await()
                    if(!response.isNullOrEmpty()){
                        gamePom = response[0]
                    }
                }

                return@withContext gamePom
            }catch (e : Exception){
                e.printStackTrace()
                return@withContext Game(0,"","","",0.0,"","","","","","", emptyList())
            }
        }
    }


    fun setAge(age:Int) : Boolean{
        if(age < 3 || age > 100){
            return false
        }
        else{
            this.age = age
            if(this.age != null){
                return true
            }
            return false
        }
    }


}