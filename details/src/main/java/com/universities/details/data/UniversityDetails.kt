package com.universities.details.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UniversityDetails(
    val name: String,
    val country: String,
    val countryCode: String,
    val state: String? = null,
    val webPages: List<String> = emptyList()
): Parcelable
