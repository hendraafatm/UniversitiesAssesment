package com.universities.listing.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universities.listing.data.model.University
import com.universities.listing.data.utils.Resource
import com.universities.listing.domain.usecases.GetUniversitiesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListingViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase
) : ViewModel() {

    private val _universitiesLiveData = MutableLiveData<List<University>>()
    val universitiesLiveData: LiveData<List<University>> = _universitiesLiveData

    fun fetchUniversities() {
        viewModelScope.launch {
            val result = getUniversitiesUseCase()
            if (result is Resource.Success) {
                Log.d("data success","data is success ${result.data}")
                _universitiesLiveData.value = result.data ?: emptyList()
            } else if (result is Resource.Error){
                Log.d("data success","data is error ${result.message}")
                _universitiesLiveData.value = emptyList()
            }
            // Handle Resource.Error case if needed, but for simplicity we'll skip it here
        }
    }
}
