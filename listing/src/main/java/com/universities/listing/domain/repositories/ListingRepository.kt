package com.universities.listing.domain.repositories

import com.universities.listing.data.model.University
import com.universities.listing.data.utils.Resource

interface ListingRepository {
    suspend fun getUniversities(): Resource<List<University>>
}