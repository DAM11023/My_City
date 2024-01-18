package com.example.mycity.model

import com.example.mycity.data.DataSource
import com.example.mycity.data.Place
import com.example.mycity.data.PlaceInfo

data class MyCityUiState(
    val place: Place = DataSource.Placelist[0],
    val placeinfo: PlaceInfo = DataSource.Cafe[0]
)
