package com.example.rma23_19079_videogames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var gameListView: RecyclerView
    private lateinit var gameListAdapter: GameListAdapter
    private var gameList =  getAll()
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


    }
}