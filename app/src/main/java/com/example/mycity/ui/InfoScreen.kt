package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.mycity.R
import com.example.mycity.data.PlaceInfo

@Composable
fun InfoScreen(
    placeinfo: PlaceInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            ) {
                Image(
                    painter = painterResource(id = placeinfo.image),
                    contentDescription = stringResource(id = placeinfo.name),
                    modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))
                Text(
                    stringResource(id = placeinfo.name),
                    style = MaterialTheme.typography.bodyMedium,


                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))
                Text(
                    text = stringResource(id = placeinfo.description),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
