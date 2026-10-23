package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.databinding.ActivityMainBinding
import com.example.ui.FilmListFragment
import com.example.ui.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val filmListFragment = FilmListFragment()
    private val profileFragment = ProfileFragment()
    private var activeFragment: Fragment = filmListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation(savedInstanceState)
    }

    private fun setupBottomNavigation(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, profileFragment, "PROFILE")
                .hide(profileFragment)
                .add(R.id.fragment_container, filmListFragment, "FILM_LIST")
                .commit()
            activeFragment = filmListFragment
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_movies -> {
                    supportFragmentManager.beginTransaction()
                        .hide(activeFragment)
                        .show(filmListFragment)
                        .commit()
                    activeFragment = filmListFragment
                    true
                }
                R.id.navigation_profile -> {
                    supportFragmentManager.beginTransaction()
                        .hide(activeFragment)
                        .show(profileFragment)
                        .commit()
                    activeFragment = profileFragment
                    true
                }
                else -> false
            }
        }
    }
}
