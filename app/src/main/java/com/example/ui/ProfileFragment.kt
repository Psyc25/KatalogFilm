package com.example.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.R
import com.example.data.FilmRepository
import com.example.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val prefs by lazy {
        requireContext().getSharedPreferences("user_profile_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadProfileData()
        updateStatistics()

        binding.btnEditProfile.setOnClickListener {
            showEditProfileDialog()
        }

        binding.btnChangeProfileInfo.setOnClickListener {
            showEditProfileDialog()
        }
    }

    override fun onResume() {
        super.onResume()
        updateStatistics()
    }

    private fun loadProfileData() {
        val savedName = prefs.getString("KEY_NAME", "Alief Aditiyo Nasokha") ?: "Alief Aditiyo Nasokha"
        val savedNim = prefs.getString("KEY_NIM", "231011400929") ?: "231011400929"
        val savedClass = prefs.getString("KEY_CLASS", "06TPLE004") ?: "06TPLE004"

        // Ensure if previous default "Salvanando" was stored, migrate it to the user's updated name
        val finalName = if (savedName == "Salvanando") "Alief Aditiyo Nasokha" else savedName
        val finalNim = if (savedNim == "211011400892") "231011400929" else savedNim
        val finalClass = if (savedClass == "06TPLP001") "06TPLE004" else savedClass

        binding.tvProfileName.text = finalName
        binding.tvProfileNim.text = "NIM: $finalNim"
        binding.tvProfileClass.text = "Kelas: $finalClass"
    }

    private fun updateStatistics() {
        binding.tvStatTotalFilms.text = FilmRepository.getAllFilms().size.toString()
        binding.tvStatTotalFavorites.text = FilmRepository.getFavoriteCount().toString()
        binding.tvStatTotalReviews.text = FilmRepository.getReviewCount().toString()
    }

    private fun showEditProfileDialog() {
        val context = requireContext()
        val currentName = binding.tvProfileName.text.toString()
        val currentNim = binding.tvProfileNim.text.toString().removePrefix("NIM: ").trim()
        val currentClass = binding.tvProfileClass.text.toString().removePrefix("Kelas: ").trim()

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 24, 48, 24)
        }

        val etName = EditText(context).apply {
            hint = "Nama Mahasiswa"
            setText(currentName)
        }

        val etNim = EditText(context).apply {
            hint = "Nomor Induk Mahasiswa (NIM)"
            setText(currentNim)
        }

        val etClass = EditText(context).apply {
            hint = "Kelas (contoh: 06TPLE004)"
            setText(currentClass)
        }

        layout.addView(etName)
        layout.addView(etNim)
        layout.addView(etClass)

        AlertDialog.Builder(context)
            .setTitle("Ubah Profil Mahasiswa")
            .setView(layout)
            .setPositiveButton("Simpan") { _, _ ->
                val newName = etName.text.toString().trim()
                val newNim = etNim.text.toString().trim()
                val newClass = etClass.text.toString().trim()

                if (newName.isNotEmpty() && newNim.isNotEmpty()) {
                    prefs.edit()
                        .putString("KEY_NAME", newName)
                        .putString("KEY_NIM", newNim)
                        .putString("KEY_CLASS", if (newClass.isNotEmpty()) newClass else "06TPLE004")
                        .apply()

                    loadProfileData()
                    Toast.makeText(context, "Profil mahasiswa diperbarui!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Nama dan NIM tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
