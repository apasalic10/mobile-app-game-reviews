package com.example.rma23_19079_videogames

import android.content.pm.ActivityInfo
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class OwnEspressoTest {

    @get:Rule
    var homeRule:ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)

    /* Scenario testa 1:
    * Test prvo postavlja uređaj u landscape mode.
    * Zatim provjerava da li se u landscape modu nalaze dva fragmenta, i to "Home" i "Details" fragment.
    * Nakon te provjere, provjerava se da li se naziv igrice koji je u TextView u Details fragmentu identican
    * onome nazivu koji ima prva igra u listu igara.
    *
    * Provjera ovoga se vrši iz razloga što je naglašeno da pri prvom prebacivanju u landscape mode igra koja
    * treba da bude u "Details" fragmentu je prva igra sa liste igara koja se nalazi u RecyclerView u "Home" fragmentu.
    *
    * Metode koje su korištene u testu osiguravaju da test provjerava gore navedeno.*/
    @Test
    fun testLandscapeMode() {

        //Postavlja uređaj u landscape mode
        homeRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        //Provjerava da li se oba vragmenta nalaze u landscape modu
        onView(withId(R.id.fragment_container_home)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_container_details)).check(matches(isDisplayed()))

        //Provjerava da li je naziv igrice identičan onome nazivu igrice koja se nalazi prva na listi
        onView(withId(R.id.game_title_textview)).check(matches(withText(GameData.getAll().get(0).title)))
    }


    /* Scenario testa 2:
    * Test prvo stisne item koji ima indeks 2 u RecyclerView, koji ga nakon toga prebacuje
    * na "Details" fragment. Nakon toga test provjerava da li se informacije igrice poklapaju
    * sa informacijama igrice sa istim indeksom u listi igrica. Takođe provjerava se da li su enabled
    * ili nisu dugmad na navigacijskoj traci u svakom fragmentu.
    *
    * Provjera ovoga se vrši iz razloga što je neophodno da nakon klika na item u
    * RecyclerView se otvori "Details" fragment sa podacima o igri.
    *
    * Metode koje su korištene u testu osiguravaju da test provjerava gore navedeno.*/
    @Test
    fun testClickOnRecyclerViewItem() {

        var trecaIgra = GameData.getAll().get(2)
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            CoreMatchers.allOf(
                hasDescendant(withText(trecaIgra.title)),
                hasDescendant(withText(trecaIgra.releaseDate)),
                hasDescendant(withText(trecaIgra.rating.toString()))
            ),click()))

        onView(withId(R.id.game_title_textview)).check(matches(withText(trecaIgra.title)))
        onView(withId(R.id.platform_textview)).check(matches(withText(trecaIgra.platform)))
        onView(withId(R.id.release_date_textview)).check(matches(withText(trecaIgra.releaseDate)))
        onView(withId(R.id.homeItem)).check(matches(isEnabled()))
        onView(withId(R.id.gameDetailsItem)).check(matches(isNotEnabled()))
    }


    /* Scenario testa 3:
    * Test prvo stisne item koji ima indeks 2 u RecyclerView, koji ga nakon toga prebacuje
    * na "Details" fragment. Nakon toga se stisne homeItem na navigacijskom meniju, koji
    * vodi na "Home" fragment. Nakon toga se stisne gameDetailsItem na navigacijskom meniju,
    * koji voi na "Details" fragment.Nakon toga test provjerava da li se informacije igrice poklapaju
    * sa informacijama igrice sa istim indeksom u listi igrica. Takođe provjerava se da li su enabled
    * ili nisu dugmad na navigacijskoj traci u svakom fragmentu.
    *
    * Provjera ovoga se vrši iz razloga što je neophodno da nakon klika na item u
    * RecyclerView se otvori "Details" fragment sa podacima o igri. Te da nakon ponovnog
    * vraćanja sa "Home" fragmenta na "Details" fragment se zapamti igra koja je bila zadnja otvorena.
    *
    * Metode koje su korištene u testu osiguravaju da test provjerava gore navedeno.*/
    @Test
    fun testClickOnGameDetailsItem() {

        var trecaIgra = GameData.getAll().get(2)
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            CoreMatchers.allOf(
                hasDescendant(withText(trecaIgra.title)),
                hasDescendant(withText(trecaIgra.releaseDate)),
                hasDescendant(withText(trecaIgra.rating.toString()))
            ),click()))

        onView(withId(R.id.homeItem)).check(matches(isEnabled()))
        onView(withId(R.id.gameDetailsItem)).check(matches(isNotEnabled()))

        onView(withId(R.id.homeItem)).perform(click())
        onView(withId(R.id.homeItem)).check(matches(isNotEnabled()))
        onView(withId(R.id.gameDetailsItem)).check(matches(isEnabled()))

        onView(withId(R.id.gameDetailsItem)).perform(click())
        onView(withId(R.id.game_title_textview)).check(matches(withText(trecaIgra.title)))
        onView(withId(R.id.platform_textview)).check(matches(withText(trecaIgra.platform)))
        onView(withId(R.id.release_date_textview)).check(matches(withText(trecaIgra.releaseDate)))

    }



}