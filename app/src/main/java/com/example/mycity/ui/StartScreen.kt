package com.example.mycity.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.data.Place

@Composable
fun StartScreen(
    places: List<Place>,
    onClicked: (Place) -> Unit = {},

) {
    LazyColumn {
        items(places) { place ->
            PlaceItem(place = place, onClicked = onClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceItem(
    place: Place,
    onClicked: (Place) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
        onClick = { onClicked(place) }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
        ) {
            ImageIcon(imageIcon = place.image)
            Text(
                text = stringResource(id = place.name),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun ImageIcon(
    @DrawableRes imageIcon: Int, modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.image_size))
            .padding(dimensionResource(id = R.dimen.padding_small)),
        contentScale = ContentScale.Fit,
        painter = painterResource(id = imageIcon),
        contentDescription = null
    )
}
