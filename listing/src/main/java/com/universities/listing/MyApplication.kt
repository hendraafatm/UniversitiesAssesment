package com.universities.listing

import android.app.Application
import com.universities.listing.data.modules.AppModule
import com.universities.listing.data.modules.DaggerApplicationComponent

class MyApplication : Application() {

    val appComponent = DaggerApplicationComponent.builder()
        .appModule(AppModule(this))
        .build()

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

    }
}