package com.universities.listing.domain.usecases

import com.universities.listing.data.model.University
import com.universities.listing.data.utils.Resource
import com.universities.listing.domain.repositories.ListingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetUniversitiesUseCaseTest {

    @Mock
    private lateinit var listingRepository: ListingRepository

    private lateinit var getUniversitiesUseCase: GetUniversitiesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getUniversitiesUseCase = GetUniversitiesUseCase(listingRepository)
    }

    @Test
    fun `invoke returns success when listingRepository returns success`() = runBlockingTest {
        // Arrange
        val expectedUniversities = listOf(
            University(
                1,
                "Test University", "UAE", "AE", "DXB", emptyList()
            )
        )
        `when`(listingRepository.getUniversities()).thenReturn(Resource.Success(expectedUniversities))

        // Act
        val result = getUniversitiesUseCase()

        // Assert
        assert(result is Resource.Success)
        assert((result as Resource.Success).data == expectedUniversities)
        verify(listingRepository, times(1)).getUniversities()
    }

    @Test
    fun `invoke returns error when listingRepository returns error`() = runBlockingTest {
        // Arrange
        val expectedErrorMessage = "Error"
        `when`(listingRepository.getUniversities()).thenReturn(Resource.Error(expectedErrorMessage))

        // Act
        val result = getUniversitiesUseCase()

        // Assert
        assert(result is Resource.Error)
        assert((result as Resource.Error).message == expectedErrorMessage)
        verify(listingRepository, times(1)).getUniversities()
    }

    @Test
    fun `invoke returns error when listingRepository throws exception`() = runBlockingTest {
        // Arrange
        val exceptionMessage = "Exception occurred"
        `when`(listingRepository.getUniversities()).thenThrow(RuntimeException(exceptionMessage))

        // Act
        val result = getUniversitiesUseCase()

        // Assert
        assert(result is Resource.Error)
        assert((result as Resource.Error).message == "Exception occurred: $exceptionMessage")
        verify(listingRepository, times(1)).getUniversities()
    }
}
