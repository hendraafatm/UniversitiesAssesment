package com.universities.listing.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.universities.details.data.UniversityDetails
import com.universities.details.presentation.DetailsScreen
import com.universities.listing.MyApplication
import com.universities.listing.databinding.ActivityListingBinding
import javax.inject.Inject

class ListingActivity : AppCompatActivity() {
    @Inject
    lateinit var listingViewModel: ListingViewModel
    private lateinit var binding: ActivityListingBinding
    private lateinit var adapter: UniversityAdapter

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupResultLauncher()
        observeViewModel()
        listingViewModel.fetchUniversities()
    }

    private fun setupResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    listingViewModel.fetchUniversities()
                }
            }
    }

    private fun observeViewModel() {
        listingViewModel.universitiesLiveData.observe(this) { universities ->
            adapter.updateData(universities)
        }
    }

    private fun setupRecyclerView() {
        adapter = UniversityAdapter(emptyList()) { university ->
            val intent = Intent(this, DetailsScreen::class.java)
            intent.putExtra(
                "university", UniversityDetails(
                    name = university.name,
                    country = university.country,
                    countryCode = university.countryCode,
                    state = university.state,
                    webPages = university.webPages
                )
            )
            resultLauncher.launch(intent)
        }
        binding.rvUniversities.adapter = adapter
    }
}