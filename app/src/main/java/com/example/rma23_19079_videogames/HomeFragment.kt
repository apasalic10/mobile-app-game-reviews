package com.example.rma23_19079_videogames

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rma23_19079_videogames.GameData.Companion.getDetails
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {
    private lateinit var gameListView: RecyclerView
    private lateinit var gameListAdapter: GameListAdapter
    private var gameList =  GameData.getAll()
    private lateinit var searchButton : Button
    private lateinit var navigation : BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        gameListView = view.findViewById(R.id.game_list)
        gameListView.layoutManager = GridLayoutManager(activity, 1)
        gameListAdapter = GameListAdapter(listOf())  { game -> showGameDetails(game) }


        gameListView.adapter = gameListAdapter
        gameListAdapter.updateMovies(gameList)

        searchButton = view.findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            val searchText = view.findViewById<EditText>(R.id.search_query_edittext)

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


        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            val title = arguments?.getString("game_title").toString()
            navigation = requireActivity().findViewById(R.id.bottom_nav)
            navigation.menu.getItem(0).isEnabled = false
            navigation.menu.getItem(0).isCheckable = true

            if (arguments != null) {
                navigation.menu.getItem(1).isEnabled = true
                navigation.menu.getItem(1).isCheckable = true
                navigation.setOnItemSelectedListener {
                    when (it.itemId) {
                        R.id.gameDetailsItem -> {
                            showGameDetails(GameData.getDetails(title)!!)
                            true
                        }
                        else -> {
                            true
                        }
                    }
                }
            } else {
                navigation.menu.getItem(1).isEnabled = false
            }

            return view;
        }




        return view
    }


    private fun showGameDetails(game: Game) {

        val bundle = bundleOf("game_title" to game.title)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val gameDetailsFragment = GameDetailsFragment()
            gameDetailsFragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_details, gameDetailsFragment)
                .commit()
        } else {
            requireView().findNavController().navigate(R.id.action_homeFragment_to_gameDetailsFragment, bundle)
        }
    }

}