package com.example

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import com.example.data.FilmRepository
import com.example.databinding.ActivityDetailFilmBinding
import com.example.model.Film

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "EXTRA_FILM"
    }

    private lateinit var binding: ActivityDetailFilmBinding
    private var film: Film? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve Film from Intent (Task d)
        val passedFilm = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_FILM, Film::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_FILM) as? Film
        }

        if (passedFilm == null) {
            Toast.makeText(this, "Data film tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Get latest film data from repository
        film = FilmRepository.getFilmById(passedFilm.id) ?: passedFilm

        initViews(film!!)
        setupWebViewTrailer(film!!.youtubeTrailerUrl)
        setupVideoViewTrailer(film!!.videoTrailerUrl)
        setupUserReviewSection(film!!)
    }

    private fun initViews(film: Film) {
        with(binding) {
            tvDetailAppbarTitle.text = film.title
            tvDetailTitle.text = film.title
            tvDetailYear.text = film.year
            tvDetailRating.text = "${film.rating} / 10"
            tvDetailGenre.text = film.genre
            tvDetailDirector.text = "Sutradara: ${film.director}"
            tvDetailDuration.text = "Durasi: ${film.duration}"
            tvDetailSynopsis.text = film.synopsis

            // Poster ImageView (Task e) using Coil
            ivDetailPoster.load(film.posterUrl) {
                crossfade(true)
                placeholder(R.drawable.bg_poster_placeholder)
                error(R.drawable.bg_poster_placeholder)
            }

            btnBack.setOnClickListener {
                finish()
            }

            updateFavoriteViews(film.isFavorite)

            val toggleFavAction = {
                val newStatus = FilmRepository.toggleFavorite(film.id)
                film.isFavorite = newStatus
                updateFavoriteViews(newStatus)
                val message = if (newStatus) {
                    "${film.title} ${getString(R.string.added_to_favorites)}"
                } else {
                    "${film.title} ${getString(R.string.removed_from_favorites)}"
                }
                Toast.makeText(this@DetailFilmActivity, message, Toast.LENGTH_SHORT).show()
            }

            btnTopFavorite.setOnClickListener { toggleFavAction() }
            btnDetailFavorite.setOnClickListener { toggleFavAction() }
        }
    }

    private fun updateFavoriteViews(isFav: Boolean) {
        if (isFav) {
            binding.btnTopFavorite.setImageResource(R.drawable.ic_favorite)
            binding.btnDetailFavorite.text = "Tersimpan di Favorit"
            binding.btnDetailFavorite.setIconResource(R.drawable.ic_favorite)
            binding.btnDetailFavorite.setIconTint(ContextCompat.getColorStateList(this, R.color.favorite_red))
        } else {
            binding.btnTopFavorite.setImageResource(R.drawable.ic_favorite_border)
            binding.btnDetailFavorite.text = getString(R.string.btn_add_favorite)
            binding.btnDetailFavorite.setIconResource(R.drawable.ic_favorite_border)
            binding.btnDetailFavorite.setIconTint(ContextCompat.getColorStateList(this, R.color.text_muted))
        }
    }

    /**
     * Task e: Gunakan WebView untuk menampilkan trailer film dari situs web seperti YouTube
     */
    private fun setupWebViewTrailer(youtubeEmbedUrl: String) {
        val webView = binding.wvTrailerYoutube

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            mediaPlaybackRequiresUserGesture = false
        }

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()

        // Responsive HTML container embedding YouTube iframe
        val htmlData = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
                <style>
                    * { margin: 0; padding: 0; box-sizing: border-box; }
                    body { background-color: #000; overflow: hidden; display: flex; justify-content: center; align-items: center; height: 100vh; }
                    .video-wrapper { position: relative; width: 100%; height: 100%; }
                    iframe { position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: 0; }
                </style>
            </head>
            <body>
                <div class="video-wrapper">
                    <iframe 
                        src="$youtubeEmbedUrl?enablejsapi=1&rel=0&playsinline=1" 
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                        allowfullscreen>
                    </iframe>
                </div>
            </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL("https://www.youtube.com", htmlData, "text/html", "UTF-8", null)
    }

    /**
     * Task e: Serta implementasikan VideoView untuk menampilkan video trailer film
     */
    private fun setupVideoViewTrailer(videoUrl: String) {
        val videoView = binding.vvTrailerVideo
        val btnPlay = binding.btnPlayVideo
        val progressBar = binding.pbVideoLoading

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        val videoUri = Uri.parse(videoUrl)
        videoView.setVideoURI(videoUri)

        progressBar.visibility = View.VISIBLE
        btnPlay.visibility = View.GONE

        videoView.setOnPreparedListener { mp ->
            progressBar.visibility = View.GONE
            btnPlay.visibility = View.VISIBLE

            mp.setOnVideoSizeChangedListener { _, _, _ ->
                mediaController.setAnchorView(videoView)
            }
        }

        videoView.setOnErrorListener { _, what, extra ->
            progressBar.visibility = View.GONE
            btnPlay.visibility = View.VISIBLE
            Toast.makeText(this, "Tidak dapat memuat video trailer", Toast.LENGTH_SHORT).show()
            true
        }

        videoView.setOnCompletionListener {
            btnPlay.visibility = View.VISIBLE
        }

        btnPlay.setOnClickListener {
            btnPlay.visibility = View.GONE
            videoView.start()
        }
    }

    /**
     * Task b: Gunakan Button, TextView, ImageView, dan EditText di halaman detail film
     */
    private fun setupUserReviewSection(film: Film) {
        if (film.userReview.isNotBlank()) {
            binding.etUserReview.setText(film.userReview)
            binding.tvSavedReviewDisplay.text = "Catatan Review Tersimpan:\n\"${film.userReview}\""
        } else {
            binding.tvSavedReviewDisplay.text = "Belum ada catatan review tersimpan untuk film ini."
        }

        binding.btnSaveReview.setOnClickListener {
            val reviewText = binding.etUserReview.text.toString().trim()
            if (reviewText.isNotEmpty()) {
                FilmRepository.updateReview(film.id, reviewText)
                film.userReview = reviewText
                binding.tvSavedReviewDisplay.text = "Catatan Review Tersimpan:\n\"$reviewText\""
                Toast.makeText(this, getString(R.string.note_saved), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tulis catatan review terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (binding.vvTrailerVideo.isPlaying) {
            binding.vvTrailerVideo.pause()
            binding.btnPlayVideo.visibility = View.VISIBLE
        }
        binding.wvTrailerYoutube.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.wvTrailerYoutube.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.vvTrailerVideo.stopPlayback()
        binding.wvTrailerYoutube.destroy()
    }
}
