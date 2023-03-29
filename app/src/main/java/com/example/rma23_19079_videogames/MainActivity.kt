package com.example.rma23_19079_videogames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var gameListView: RecyclerView
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

        gameListAdapter = GameListAdapter(listOf())
        gameListView.adapter = gameListAdapter
        gameListAdapter.updateMovies(gameList)

        val homeButton = findViewById<Button>(R.id.home_button)
        homeButton.isEnabled = false

        searchButton = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            val searchText = findViewById<EditText>(R.id.search_query_edittext)

            if(searchText.text.toString().isEmpty()){
                gameListAdapter.updateMovies(gameList)
            }
            else{
                val searchGame = GameData.getDetails(searchText.text.toString())

                if(searchGame === null){
                    gameListAdapter.updateMovies(listOf())
                }
                else{
                    gameListAdapter.updateMovies(listOf(searchGame))
                }
            }
        }

    }
}