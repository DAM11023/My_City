package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mycity.R


class Place(image: Int, @StringRes name: Int,) {
    val name = name
    val image = image
}

class PlaceInfo(image: Int, @StringRes name: Int, @StringRes description: Int ) {
    val image = image
    val name = name
    val description = description
}

object DataSource {
    val Placelist = listOf(
        Place(name = R.string.Sitio1, image = R.drawable.alcampo),
        Place(name = R.string.Sitio2, image = R.drawable.alcampo),
        Place(name = R.string.Sitio3, image = R.drawable.alcampo)
    )

    val Cafe = listOf(
        PlaceInfo(R.drawable.cafeteria_aromas, R.string.Cafe1, R.string.Cafe1desc),
        PlaceInfo(R.drawable.cafeteria_avenida55, R.string.Cafe2, R.string.Cafe2desc),
        PlaceInfo(R.drawable.cafeteria_granvia, R.string.Cafe3, R.string.Cafe3desc),
        PlaceInfo(R.drawable.cafeteria_ohana, R.string.Cafe4, R.string.Cafe4desc),
    )

    val Mall = listOf(
        PlaceInfo(R.drawable.berceo, R.string.Mall1, R.string.Mall1desc),
        PlaceInfo(R.drawable.alcampo, R.string.Mall2, R.string.Mall2desc),
        PlaceInfo(R.drawable.lascanas, R.string.Mall3, R.string.Mall3desc)
    )

    val Library = listOf(
        PlaceInfo(R.drawable.casadellibro, R.string.Library1, R.string.Library1desc),
        PlaceInfo(R.drawable.picasso, R.string.Library2, R.string.Library2desc),
        PlaceInfo(R.drawable.traperiadeklaus, R.string.Library3, R.string.Library3desc),
        PlaceInfo(R.drawable.ochoa, R.string.Library4, R.string.Library4desc)
    )
}