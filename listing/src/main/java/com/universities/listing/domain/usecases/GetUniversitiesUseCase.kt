package com.universities.listing.domain.usecases

import com.universities.listing.data.model.University
import com.universities.listing.data.utils.Resource
import com.universities.listing.domain.repositories.ListingRepository
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(
    private val listingRepository: ListingRepository
) {
    suspend operator fun invoke(): Resource<List<University>> {
        return try {
            listingRepository.getUniversities()
        } catch (e: Exception) {
            Resource.Error("Exception occurred: ${e.message}")
        }
    }
}
