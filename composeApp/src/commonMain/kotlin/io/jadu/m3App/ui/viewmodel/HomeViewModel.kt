package io.jadu.m3App.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = HomeUiState.Success(
                message = "Welcome to m3App!",
                items = listOf(
                    "Compose Multiplatform",
                    "Koin DI",
                    "Navigation3"
                )
            )
        }
    }

    val featuredListings = listOf(
        PropertyListing(
            id = 1,
            name = "Sea Breeze Villa",
            location = "Beachside Bliss",
            pricePerNight = "₹7,899",
            rating = 4.8f,
            reviewCount = 42,
            placardLabel = "Beachside Bliss",
            cardGradient = listOf(Color(0xFFE8C99A), Color(0xFFD4A87A), Color(0xFF8B6B4A)),
        ),
        PropertyListing(
            id = 2,
            name = "Hillside Cabin",
            location = "Manali, India",
            pricePerNight = "₹5,299",
            rating = 4.6f,
            reviewCount = 28,
            placardLabel = "Mountain Retreat",
            cardGradient = listOf(Color(0xFF9BB5A0), Color(0xFF6B9E7A), Color(0xFF4A7A5A)),
        ),
        PropertyListing(
            id = 3,
            name = "Heritage Have",
            location = "Jaipur, India",
            pricePerNight = "₹5,499",
            rating = 4.7f,
            reviewCount = 35,
            placardLabel = "City Escape",
            cardGradient = listOf(Color(0xFFD4A87A), Color(0xFFC4896A), Color(0xFF8B5A3A)),
        ),
    )

}

data class PropertyListing(
    val id: Int,
    val name: String,
    val location: String,
    val pricePerNight: String,
    val rating: Float,
    val reviewCount: Int,
    val placardLabel: String,
    val cardGradient: List<Color>,
    val isFavorited: Boolean = false,
)



sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(
        val message: String,
        val items: List<String>
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}