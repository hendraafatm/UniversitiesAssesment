package com.universities.listing.data.modules

import android.app.Application
import com.universities.listing.presentation.ListingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(application: Application)
    fun inject(activity: ListingActivity)

    // Add more injection methods as needed for other components
}
