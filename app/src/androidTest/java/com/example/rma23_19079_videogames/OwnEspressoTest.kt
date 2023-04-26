package com.example.rma23_19079_videogames

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class OwnEspressoTest {

    @get:Rule
    var homeRule:ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun testLandscapeMode() {

        //Postavlja uređaj u landscape mode
        val device: UiDevice = UiDevice.getInstance(getInstrumentation())
        device.setOrientationRight()

        //Provjerava da li se oba vragmenta nalaze u landscape modu
        onView(withId(R.id.fragment_container_home)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_container_details)).check(matches(isDisplayed()))

        //Provjerava da li je naziv igrice identičan onome nazivu igrice koja se nalazi prva na listi
        onView(withId(R.id.game_title_textview)).check(matches(withText(GameData.getAll().get(0).title)))
    }
}