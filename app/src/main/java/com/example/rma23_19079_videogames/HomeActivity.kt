package com.example.rma23_19079_videogames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
        navView.setupWithNavController(navController)
    }

    /*private lateinit var gameListView: RecyclerView
    private lateinit var gameListAdapter: GameListAdapter
    private var gameList =  GameData.getAll()
    private lateinit var searchButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        gameListView = findViewById(R.id.game_list)

        gameListView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        gameListAdapter = GameListAdapter(listOf())  { game -> showMovieDetails(game) }
        gameListView.adapter = gameListAdapter
        gameListAdapter.updateMovies(gameList)

        val homeButton = findViewById<Button>(R.id.home_button)
        val detailsButton = findViewById<Button>(R.id.details_button)
        homeButton.isEnabled = false

        searchButton = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            val searchText = findViewById<EditText>(R.id.search_query_edittext)

            if(searchText.text.toString().isEmpty()){
                gameListAdapter.updateMovies(gameList)
            }
            else{
                val searchGame = GameData.getListOfGames(searchText.text.toString())

                if(searchGame.isEmpty()){
                    gameListAdapter.updateMovies(listOf())
                }
                else{
                    gameListAdapter.updateMovies(searchGame)
                }
            }
        }

        val extras = intent.extras

        if(extras != null){
            detailsButton.setOnClickListener {
                GameData.getDetails(extras.getString("game_title",""))
                    ?.let { it1 -> showMovieDetails(it1) }
            }
        }
        else{
            detailsButton.isEnabled = false
        }
    }

    private fun showMovieDetails(game: Game) {
        val intent = Intent(this, GameDetailsActivity::class.java).apply {
            putExtra("game_title", game.title)
        }
        startActivity(intent)
    }*/
}