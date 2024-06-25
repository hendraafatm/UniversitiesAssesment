package com.universities.details.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.universities.details.R
import com.universities.details.data.UniversityDetails
import com.universities.details.databinding.ActivityDetailsScreenBinding

class DetailsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val universityDetails = intent.getParcelableExtra<UniversityDetails>("university")
        universityDetails?.let{details->
            binding.tvUniversityName.text=details.name
            binding.tvUniversityState.text=details.state
            binding.tvUniversityCountry.text=details.country
            binding.tvUniversityCountryCode.text=details.countryCode
            binding.tvUniversityWebPage.text = details.webPages.firstOrNull() ?: ""



        }

        binding.btnRefresh.setOnClickListener{
            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}