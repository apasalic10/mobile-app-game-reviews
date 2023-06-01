package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import com.example.rma23_19079_videogames.BuildConfig
import kotlinx.coroutines.*
import okhttp3.RequestBody.Companion.toRequestBody

object AccountGamesRepository {
    var acHash: String = BuildConfig.acHash
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

    suspend fun removeGame(id:Int):Boolean{
        return withContext(Dispatchers.IO){
            try{
                AccountApiConfig.retrofit.removeGame(getHash(),id)
                return@withContext true
            } catch (exception: Exception){
                exception.printStackTrace()
                return@withContext false
            }
        }

    }

    suspend fun removeNonSafe():Boolean{

        return withContext(Dispatchers.IO){
            try{
                val response = getSavedGames()
                for(g:Game in response){
                    if(g.esrbRating.equals("")) continue
                    if(age!! < AgeRating.valueOf(g.esrbRating).value){
                        removeGame(g.id)
                    }
                }
                return@withContext true
            }

            catch (exception: Exception){
                exception.printStackTrace()
                return@withContext false
            }
        }

    }

    suspend fun getGamesContainingString(query:String):List<Game>{
        return withContext(Dispatchers.IO){
            try{
                val response = getSavedGames()
                var new_response: ArrayList<Game> = ArrayList<Game>()
                for(g:Game in response){
                    if(g.title.contains(query,true)) {
                        new_response.add(g)
                    }
                }
                return@withContext new_response
            }

            catch (exception: Exception){
                exception.printStackTrace()
                return@withContext emptyList<Game>()
            }
        }

    }


    fun setAge(age:Int):Boolean{
        if(age < 3 || age > 100) return false

        this.age = age
        return true
    }


}