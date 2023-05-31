package ba.etf.rma23_19079_videogames

import ba.etf.rma23.projekat.Game
import ba.etf.rma23.projekat.data.repositories.GamesRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun a4_getGamesByName() = runBlocking {
        var res = GamesRepository.getGamesByName("Age of Empires")
        assertThat(res, CoreMatchers.hasItem<Game>(Matchers.hasProperty("id",CoreMatchers.equalTo(24273))))
    }
}