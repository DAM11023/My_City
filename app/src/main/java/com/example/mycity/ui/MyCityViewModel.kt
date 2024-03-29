package com.example.mycity.ui


import androidx.lifecycle.ViewModel
import com.example.mycity.data.Place
import com.example.mycity.data.PlaceInfo
import com.example.mycity.model.MyCityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun updatePlace(item: Place) {
        _uiState.update { currentState ->
            currentState.copy(
                placeinfo = currentState.placeinfo,
                place = item
            )
        }
    }

    fun updatePlaceInfo(item: PlaceInfo) {
        _uiState.update { currentState ->
            currentState.copy(
                placeinfo = item,
                place = currentState.place
            )
        }
    }
}