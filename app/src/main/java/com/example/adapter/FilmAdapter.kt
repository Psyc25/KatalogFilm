package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.R
import com.example.databinding.ItemFilmBinding
import com.example.model.Film

class FilmAdapter(
    private var films: List<Film>,
    private val onFavoriteClickListener: (Film, Int) -> Unit,
    private val onDetailClickListener: (Film) -> Unit
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = films[position]
        val context = holder.itemView.context

        with(holder.binding) {
            tvFilmTitle.text = film.title
            tvFilmYear.text = film.year
            tvFilmDuration.text = film.duration
            tvFilmGenre.text = film.genre
            tvFilmRating.text = String.format("%.1f", film.rating)
            tvFilmSynopsisPreview.text = film.synopsis

            // ImageView for poster (Task e) using Coil
            ivFilmPoster.load(film.posterUrl) {
                crossfade(true)
                placeholder(R.drawable.bg_poster_placeholder)
                error(R.drawable.bg_poster_placeholder)
            }

            // Update Favorite button appearance
            updateFavoriteButtonState(holder.binding, film.isFavorite)

            // onClickListener for Tambah ke Favorit button (Task c)
            btnItemFavorite.setOnClickListener {
                onFavoriteClickListener(film, position)
            }

            // onClickListener for Lihat Detail button (Task d - Intent)
            btnItemDetail.setOnClickListener {
                onDetailClickListener(film)
            }

            // Click entire card to view details
            cardFilmItem.setOnClickListener {
                onDetailClickListener(film)
            }
        }
    }

    private fun updateFavoriteButtonState(binding: ItemFilmBinding, isFavorite: Boolean) {
        val context = binding.root.context
        if (isFavorite) {
            binding.btnItemFavorite.text = "Favorit"
            binding.btnItemFavorite.setIconResource(R.drawable.ic_favorite)
            binding.btnItemFavorite.setIconTint(ContextCompat.getColorStateList(context, R.color.favorite_red))
        } else {
            binding.btnItemFavorite.text = context.getString(R.string.btn_add_favorite)
            binding.btnItemFavorite.setIconResource(R.drawable.ic_favorite_border)
            binding.btnItemFavorite.setIconTint(ContextCompat.getColorStateList(context, R.color.text_muted))
        }
    }

    override fun getItemCount(): Int = films.size

    fun updateData(newFilms: List<Film>) {
        this.films = newFilms
        notifyDataSetChanged()
    }
}
