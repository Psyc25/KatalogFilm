package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.FilmRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Katalog Film", appName)
  }

  @Test
  fun `test film repository getAllFilms and search`() {
    val films = FilmRepository.getAllFilms()
    assertTrue(films.isNotEmpty())

    val searchResult = FilmRepository.searchAndFilter("Inception")
    assertEquals(1, searchResult.size)
    assertEquals("Inception", searchResult[0].title)

    val film = FilmRepository.getFilmById(1)
    assertNotNull(film)
  }
}
