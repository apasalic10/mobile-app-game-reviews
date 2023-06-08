package ba.etf.rma23.projekat

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository
import com.bumptech.glide.Glide
import com.example.rma23_19079_videogames.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.runBlocking


class GameDetailsFragment : Fragment() {
    private lateinit var game: Game
    private lateinit var gameTitle: TextView
    private lateinit var gameCoverImage: ImageView
    private lateinit var gamePlatform: TextView
    private lateinit var gameReleaseDate: TextView
    private lateinit var gameEsrbRating: TextView
    private lateinit var gameDeveloper: TextView
    private lateinit var gamePublisher: TextView
    private lateinit var gameGenre: TextView
    private lateinit var gameDescription: TextView
    private lateinit var impressionListView: RecyclerView
    private lateinit var impressionListAdapter: GameImpressionAdapter
    private lateinit var navigation: BottomNavigationView
    private lateinit var favouriteButton: ImageButton
    private lateinit var deleteFavouriteButton: ImageButton


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_game_details, container, false)

        gameTitle = view.findViewById(R.id.game_title_textview)
        gameCoverImage = view.findViewById(R.id.cover_imageview)
        gamePlatform = view.findViewById(R.id.platform_textview)
        gameReleaseDate = view.findViewById(R.id.release_date_textview)
        gameEsrbRating = view.findViewById(R.id.esrb_rating_textview)
        gameDeveloper = view.findViewById(R.id.developer_textview)
        gamePublisher = view.findViewById(R.id.publisher_textview)
        gameGenre = view.findViewById(R.id.genre_textview)
        gameDescription = view.findViewById(R.id.description_textview)
        favouriteButton = view.findViewById(R.id.sortGamesButton)
        deleteFavouriteButton = view.findViewById(R.id.deleteFavouriteButton)





        impressionListView = view.findViewById(R.id.game_list)
        impressionListView.layoutManager = GridLayoutManager(activity, 1)
        impressionListAdapter = GameImpressionAdapter(listOf())
        impressionListView.adapter = impressionListAdapter


        val id: Int? = arguments?.getInt("game_id")

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (arguments == null) {
                runBlocking {
                    game = AccountGamesRepository.getSavedGames().get(0)
                }
                populateDetails()

                favouriteButton.setOnClickListener{
                    runBlocking {
                        AccountGamesRepository.saveGame(game)
                    }
                    val toast = Toast.makeText(context, "Game saved", Toast.LENGTH_SHORT)
                    toast.show()
                }

                deleteFavouriteButton.setOnClickListener {
                    runBlocking {
                        AccountGamesRepository.removeGame(game.id)
                    }
                    val toast = Toast.makeText(context, "Game deleted", Toast.LENGTH_SHORT)
                    toast.show()
                }


                impressionListAdapter.updateGames(GameData.getImpressionsOfGame(game.title))
            } else {
                game = GameData.getDetails(id!!)!!
                populateDetails()

                favouriteButton.setOnClickListener{
                    runBlocking {
                        AccountGamesRepository.saveGame(game)
                    }
                    val toast = Toast.makeText(context, "Game saved", Toast.LENGTH_SHORT)
                    toast.show()

                }

                deleteFavouriteButton.setOnClickListener {
                    runBlocking {
                        AccountGamesRepository.removeGame(game.id)
                    }
                    val toast = Toast.makeText(context, "Game deleted", Toast.LENGTH_SHORT)
                    toast.show()
                }

                impressionListAdapter.updateGames(GameData.getImpressionsOfGame(game.title))
            }

        } else {

            if (arguments != null) {
                game = GameData.getDetails(id!!)!!
                populateDetails()

                favouriteButton.setOnClickListener{
                    runBlocking {
                        AccountGamesRepository.saveGame(game)
                    }

                    val toast = Toast.makeText(context, "Game saved", Toast.LENGTH_SHORT)
                    toast.show()
                }

                deleteFavouriteButton.setOnClickListener {
                    runBlocking {
                        AccountGamesRepository.removeGame(game.id)
                    }
                    val toast = Toast.makeText(context, "Game deleted", Toast.LENGTH_SHORT)
                    toast.show()
                }



                impressionListAdapter.updateGames(GameData.getImpressionsOfGame(game.title))
            }

            navigation = requireActivity().findViewById(R.id.bottom_nav)
            navigation.menu.getItem(1).isEnabled = false
            navigation.menu.getItem(1).isCheckable = true
            navigation.menu.getItem(0).isEnabled = true
            navigation.menu.getItem(0).isCheckable = false

            navigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        showHomeScreen(GameData.getDetails(game.id)!!)
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
        }


        return view;
    }

    private fun showHomeScreen(game: Game) {
        val bundle = bundleOf("game_id" to game.id)
        requireView().findNavController()
            .navigate(R.id.action_gameDetailsFragment_to_homeFragment, bundle)
    }

    private fun populateDetails() {
        gameTitle.text = game.title
        gamePlatform.text = game.platform.toString()
        gameReleaseDate.text = game.releaseDate
        gameEsrbRating.text = game.esrbRating
        gameDeveloper.text = game.developer
        gamePublisher.text = game.publisher
        gameGenre.text = game.genre
        gameDescription.text = game.description
        val id = requireContext().resources.getIdentifier(
            "error",
            "drawable",
            gameCoverImage.context.packageName
        )
        Glide.with(gameCoverImage.context)
            .load("https://${game.coverImage}")
            .placeholder(R.drawable.game_control)
            .error(id)
            .fallback(id)
            .into(gameCoverImage)
    }
}




