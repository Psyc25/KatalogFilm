package com.example.data

import com.example.model.Film

object FilmRepository {

    private val films = mutableListOf(
        Film(
            id = 1,
            title = "Inception",
            year = "2010",
            genre = "Sci-Fi",
            rating = 8.8f,
            duration = "148 menit",
            director = "Christopher Nolan",
            synopsis = "Dom Cobb adalah seorang pencuri terampil, yang terbaik dalam seni ekstraksi berbahaya: mencuri rahasia berharga dari kedalaman alam bawah sadar selama keadaan mimpi, ketika pikiran berada pada kondisi paling rentan. Keterampilan langka Cobb telah menjadikannya pemain yang didambakan dalam spionase korporat, namun juga menjadikannya buronan internasional. Kini Cobb ditawari kesempatan penebusan dosa melalui misi penanaman ide (inception).",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=500&q=80",
            youtubeTrailerUrl = "https://www.youtube.com/embed/YoHD9XEInc0",
            videoTrailerUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            isFavorite = true,
            userReview = "Salah satu film sci-fi terbaik sepanjang masa dengan plot twist yang luar biasa."
        ),
        Film(
            id = 2,
            title = "Interstellar",
            year = "2014",
            genre = "Sci-Fi",
            rating = 8.7f,
            duration = "169 menit",
            director = "Christopher Nolan",
            synopsis = "Ketika bumi di masa depan tidak lagi dapat dihuni akibat badai debu dan krisis pangan, sekelompok penjelajah menggunakan lubang cacing yang baru ditemukan untuk melampaui batas perjalanan ruang angkasa manusia dan menaklukkan jarak yang sangat jauh dalam perjalanan antar bintang demi menyelamatkan kelangsungan hidup umat manusia.",
            posterUrl = "https://images.unsplash.com/photo-1451187580459-43490279c0fa?w=500&q=80",
            youtubeTrailerUrl = "https://www.youtube.com/embed/zSWdZVtXT7E",
            videoTrailerUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            isFavorite = false
        ),
        Film(
            id = 3,
            title = "The Dark Knight",
            year = "2008",
            genre = "Action",
            rating = 9.0f,
            duration = "152 menit",
            director = "Christopher Nolan",
            synopsis = "Dengan bantuan Letnan Jim Gordon dan Jaksa Harvey Dent, Batman berhasil menjaga keamanan di Gotham City. Namun, ketika dalang kriminal psikopat yang dikenal sebagai Joker muncul dan mendatangkan malapetaka di Gotham, Batman harus menerima salah satu ujian psikologis dan fisik terbesar dari kemampuannya untuk melawan ketidakadilan.",
            posterUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=500&q=80",
            youtubeTrailerUrl = "https://www.youtube.com/embed/EXeTwQWrcwY",
            videoTrailerUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            isFavorite = true
        ),
        Film(
            id = 4,
            title = "Spider-Man: Across the Spider-Verse",
            year = "2023",
            genre = "Animasi",
            rating = 8.7f,
            duration = "140 menit",
            director = "Joaquim Dos Santos, Kemp Powers",
            synopsis = "Miles Morales terlempar melintasi Multiverse, di mana ia bertemu dengan tim Spider-People yang ditugaskan untuk melindungi keberadaannya. Ketika para pahlawan berselisih tentang cara menangani ancaman baru, Miles mendapati dirinya diadu melawan Spider lainnya dan harus mendefinisikan kembali arti menjadi seorang pahlawan.",
            posterUrl = "https://images.unsplash.com/photo-1635805737707-575885ab0820?w=500&q=80",
            youtubeTrailerUrl = "https://www.youtube.com/embed/cqGjhVJWtEg",
            videoTrailerUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
            isFavorite = false
        ),
        Film(
            id = 5,
            title = "Pengabdi Setan 2: Communion",
            year = "2022",
            genre = "Horor",
            rating = 7.0f,
            duration = "119 menit",
            director = "Joko Anwar",
            synopsis = "Beberapa tahun setelah berhasil menyelamatkan diri dari kejadian mengerikan yang membuat mereka kehilangan ibu dan si bungsu Ian, Rini dan adik-adiknya, Toni dan Bondi, beserta sang ayah tinggal di rumah susun. Mereka percaya tinggal di rumah susun aman karena ada banyak orang jika terjadi sesuatu. Namun, mereka segera menyadari bahaya yang jauh lebih mengerikan menanti di balik dinding-dinding rusun tersebut.",
            posterUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=500&q=80",
            youtubeTrailerUrl = "https://www.youtube.com/embed/U3l0M4T9m38",
            videoTrailerUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
            isFavorite = false
        ),
        Film(
            id = 6,
            title = "Agak Laen",
            year = "2024",
            genre = "Komedi",
            rating = 7.8f,
            duration = "119 menit",
            director = "Muhadkly Acho",
            synopsis = "Empat sekawan pengelola wahana rumah hantu di pasar malam yang hampir bangkrut berusaha mencari cara untuk menarik pengunjung. Mereka merombak wahana menjadi jauh lebih menyeramkan hingga seorang politisi berpenyakit jantung masuk ke wahana mereka dan meninggal karena jantungan. Demi menutupi kejadian itu, mereka terpaksa mengubur mayat tersebut di dalam rumah hantu yang ironisnya malah membuat wahana menjadi viral dan sangat ramai.",
            posterUrl = "https://images.unsplash.com/photo-1514306191717-452ec28c7814?w=500&q=80",
            youtubeTrailerUrl = "https://www.youtube.com/embed/kYgW5-N_mXg",
            videoTrailerUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackSeeTheWorld.mp4",
            isFavorite = true
        ),
        Film(
            id = 7,
            title = "Oppenheimer",
            year = "2023",
            genre = "Drama",
            rating = 8.9f,
            duration = "180 menit",
            director = "Christopher Nolan",
            synopsis = "Kisah fisikawan teoretis Amerika J. Robert Oppenheimer, yang memimpin Proyek Manhattan di Laboratorium Los Alamos selama Perang Dunia II, dan perannya dalam pengembangan bom atom pertama di dunia yang mengubah jalannya peradaban manusia selamanya.",
            posterUrl = "https://images.unsplash.com/photo-1446776811953-b23d57bd21aa?w=500&q=80",
            youtubeTrailerUrl = "https://www.youtube.com/embed/uYPbbksJxIg",
            videoTrailerUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            isFavorite = false
        ),
        Film(
            id = 8,
            title = "Coco",
            year = "2017",
            genre = "Animasi",
            rating = 8.4f,
            duration = "105 menit",
            director = "Lee Unkrich, Adrian Molina",
            synopsis = "Miguel, seorang anak laki-laki berusia 12 tahun yang bercita-cita menjadi musisi handal seperti idolanya Ernesto de la Cruz, tiba-tiba terlempar ke Negeri Orang Mati (Land of the Dead). Di sana, ia memulai perjalanan luar biasa bersama penipu ramah bernama Héctor untuk mengungkap rahasia nyata di balik sejarah keluarganya.",
            posterUrl = "https://images.unsplash.com/photo-1514525253161-7a46d19cd819?w=500&q=80",
            youtubeTrailerUrl = "https://www.youtube.com/embed/Rvr68u635JE",
            videoTrailerUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            isFavorite = true
        )
    )

    fun getAllFilms(): List<Film> {
        return films.toList()
    }

    fun getFilmById(id: Int): Film? {
        return films.find { it.id == id }
    }

    fun searchAndFilter(query: String = "", genreFilter: String = "Semua Film", onlyFavorites: Boolean = false): List<Film> {
        return films.filter { film ->
            val matchesQuery = query.isBlank() ||
                    film.title.contains(query, ignoreCase = true) ||
                    film.director.contains(query, ignoreCase = true) ||
                    film.genre.contains(query, ignoreCase = true)

            val matchesGenre = when (genreFilter) {
                "Semua Film" -> true
                "Favorit Saya" -> film.isFavorite
                else -> film.genre.contains(genreFilter, ignoreCase = true)
            }

            val matchesFavorites = !onlyFavorites || film.isFavorite

            matchesQuery && matchesGenre && matchesFavorites
        }
    }

    fun toggleFavorite(id: Int): Boolean {
        val film = films.find { it.id == id } ?: return false
        film.isFavorite = !film.isFavorite
        return film.isFavorite
    }

    fun setFavorite(id: Int, isFav: Boolean) {
        val film = films.find { it.id == id } ?: return
        film.isFavorite = isFav
    }

    fun updateReview(id: Int, review: String) {
        val film = films.find { it.id == id } ?: return
        film.userReview = review
    }

    fun getFavoriteCount(): Int {
        return films.count { it.isFavorite }
    }

    fun getReviewCount(): Int {
        return films.count { it.userReview.isNotBlank() }
    }
}
