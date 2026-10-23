package com.example.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.DetailFilmActivity
import com.example.R
import com.example.adapter.FilmAdapter
import com.example.data.FilmRepository
import com.example.databinding.FragmentFilmListBinding
import com.example.model.Film

class FilmListFragment : Fragment() {

    private var _binding: FragmentFilmListBinding? = null
    private val binding get() = _binding!!

    private lateinit var filmAdapter: FilmAdapter
    private var currentFilterGenre: String = "Semua Film"
    private var currentSearchQuery: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        setupGenreFilterChips()
        loadFilms()
    }

    override fun onResume() {
        super.onResume()
        // Refresh list to reflect updates from DetailFilmActivity (like favorite status or reviews)
        loadFilms()
    }

    private fun setupRecyclerView() {
        filmAdapter = FilmAdapter(
            films = emptyList(),
            onFavoriteClickListener = { film, position ->
                val isNowFav = FilmRepository.toggleFavorite(film.id)
                val msg = if (isNowFav) {
                    "${film.title} ${getString(R.string.added_to_favorites)}"
                } else {
                    "${film.title} ${getString(R.string.removed_from_favorites)}"
                }
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                loadFilms()
            },
            onDetailClickListener = { film ->
                // Task d: Use Intent to navigate to DetailFilmActivity and pass data
                val intent = Intent(requireContext(), DetailFilmActivity::class.java).apply {
                    putExtra(DetailFilmActivity.EXTRA_FILM, film)
                }
                startActivity(intent)
            }
        )

        binding.rvFilms.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = filmAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupSearch() {
        binding.etSearchFilm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentSearchQuery = s?.toString()?.trim() ?: ""
                binding.btnClearSearch.visibility = if (currentSearchQuery.isNotEmpty()) View.VISIBLE else View.GONE
                loadFilms()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnClearSearch.setOnClickListener {
            binding.etSearchFilm.setText("")
        }
    }

    private fun setupGenreFilterChips() {
        binding.chipGroupGenres.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isEmpty()) {
                currentFilterGenre = "Semua Film"
                binding.chipAll.isChecked = true
            } else {
                val chipId = checkedIds.first()
                currentFilterGenre = when (chipId) {
                    R.id.chip_all -> "Semua Film"
                    R.id.chip_favorites -> "Favorit Saya"
                    R.id.chip_action -> "Action"
                    R.id.chip_scifi -> "Sci-Fi"
                    R.id.chip_drama -> "Drama"
                    R.id.chip_animation -> "Animasi"
                    R.id.chip_horror -> "Horor"
                    else -> "Semua Film"
                }
            }
            binding.tvActiveFilter.text = currentFilterGenre
            loadFilms()
        }
    }

    private fun loadFilms() {
        val filteredFilms = FilmRepository.searchAndFilter(
            query = currentSearchQuery,
            genreFilter = currentFilterGenre,
            onlyFavorites = (currentFilterGenre == "Favorit Saya")
        )

        filmAdapter.updateData(filteredFilms)

        binding.tvFilmCount.text = "Menampilkan ${filteredFilms.size} film"

        if (filteredFilms.isEmpty()) {
            binding.rvFilms.visibility = View.GONE
            binding.layoutEmptyState.visibility = View.VISIBLE
        } else {
            binding.rvFilms.visibility = View.VISIBLE
            binding.layoutEmptyState.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
