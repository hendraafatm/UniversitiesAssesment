package com.universities.listing.data.repositories

import com.universities.listing.data.api.ApiService
import com.universities.listing.data.db.UniversityDao
import com.universities.listing.data.model.University
import com.universities.listing.data.utils.Resource
import com.universities.listing.domain.repositories.ListingRepository
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val universityDao: UniversityDao
) : ListingRepository {

    override suspend fun getUniversities(): Resource<List<University>> {
        return try {
            // Fetch data from API
            val response = apiService.getUniversities("United Arab Emirates")

            if (response.isSuccessful) {
                // Cache data in Room
                response.body()?.let { universities ->
                    universityDao.insertAll(universities)
                }
                // Return success response
                Resource.Success(response.body())
            } else {
                // API request failed, try fetching from Room
                val cachedData = universityDao.getAllUniversities()
                if (cachedData.isNotEmpty()) {
                    Resource.Success(cachedData)
                } else {
                    Resource.Error("API request failed and no cached data available.")
                }
            }
        } catch (e: Exception) {
            // Exception occurred, handle accordingly
            val cachedData = universityDao.getAllUniversities()
            if (cachedData.isNotEmpty()) {
                Resource.Success(cachedData)
            } else {
                Resource.Error("Exception occurred: ${e.message}")
            }
        }
    }
}
