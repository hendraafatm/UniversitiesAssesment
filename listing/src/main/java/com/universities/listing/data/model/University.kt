package com.universities.listing.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.universities.listing.data.db.Converters

@Entity(tableName = "universities")
data class University(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("alpha_two_code") val countryCode: String,
    @SerializedName("state-province") val state: String? = null,
    @SerializedName("web_pages") @TypeConverters(Converters::class) val webPages: List<String>
)
