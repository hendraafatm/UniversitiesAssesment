package com.universities.listing.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.universities.listing.data.model.University

@Dao
interface UniversityDao {
    @Query("SELECT * FROM universities")
    suspend fun getAllUniversities(): List<University>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<University>)
}