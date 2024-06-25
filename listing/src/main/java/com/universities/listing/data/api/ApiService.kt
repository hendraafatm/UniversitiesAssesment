package com.universities.listing.data.api


import com.universities.listing.data.model.University
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getUniversities(@Query("country") country: String): Response<List<University>>
}
