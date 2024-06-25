package com.universities.listing.data.modules

import com.universities.listing.data.repositories.ListingRepositoryImpl
import com.universities.listing.domain.repositories.ListingRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindListingRepository(listingRepoImpl: ListingRepositoryImpl): ListingRepository
}
