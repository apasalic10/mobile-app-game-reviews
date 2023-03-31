package com.example.rma23_19079_videogames

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GameDetailsActivity : AppCompatActivity() {

    private lateinit var game : Game
    private lateinit var gameTitle : TextView
    private lateinit var  gameCoverImage : ImageView
    private lateinit var gamePlatform : TextView
    private lateinit var gameReleaseDate : TextView
    private lateinit var gameEsrbRating : TextView
    private lateinit var gameDeveloper : TextView
    private lateinit var gamePublisher : TextView
    private lateinit var gameGenre : TextView
    private lateinit var gameDescription : TextView
    private lateinit var homeButton: Button
    private lateinit var detailsButton: Button
    private lateinit var impressionListView : RecyclerView
    private lateinit var impressionListAdapter: GameImpressionAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_details_activity)

        gameTitle = findViewById(R.id.game_title_textview)
        gameCoverImage = findViewById(R.id.cover_imageview)
        gamePlatform = findViewById(R.id.platform_textview)
        gameReleaseDate = findViewById(R.id.release_date_textview)
        gameEsrbRating = findViewById(R.id.esrb_rating_textview)
        gameDeveloper = findViewById(R.id.developer_textview)
        gamePublisher = findViewById(R.id.publisher_textview)
        gameGenre = findViewById(R.id.genre_textview)
        gameDescription = findViewById(R.id.description_textview)

        homeButton = findViewById(R.id.home_button)
        detailsButton = findViewById(R.id.details_button)
        detailsButton.isEnabled = false

        homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("game_title",game.title)
            }
            startActivity(intent)
        }

        impressionListView = findViewById(R.id.impression_list)
        impressionListView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        impressionListAdapter = GameImpressionAdapter(listOf())
        impressionListView.adapter = impressionListAdapter

        val extras = intent.extras
        if(extras != null){
            game = GameData.getDetails(extras.getString("game_title",""))!!
            populateDetails()
        }
        else{
            finish()
        }
        impressionListAdapter.updateMovies(GameData.getImpressionsOfGame(game.title))

    }

    private fun populateDetails() {
        gameTitle.text = game.title
        gamePlatform.text = game.platform
        gameReleaseDate.text = game.releaseDate
        gameEsrbRating.text = game.esrbRating
        gameDeveloper.text = game.developer
        gamePublisher.text = game.publisher
        gameGenre.text = game.genre
        gameDescription.text = game.description
        Picasso.get().load(game.coverImage).into(gameCoverImage)
    }
}