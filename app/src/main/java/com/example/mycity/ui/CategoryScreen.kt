package com.example.mycity.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.ui.ImageIcon
import com.example.mycity.data.PlaceInfo

@Composable
fun CategoryScreen(
    placeInfo: List <PlaceInfo>,
    onClicked: (PlaceInfo) -> Unit = {},
    modifier: Modifier
){
    LazyColumn{
        items(placeInfo){placeInfo->
            CategoryItem(placeinfo = placeInfo, onClicked = onClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    placeinfo = PlaceInfo,
    onClicked: (PlaceInfo) -> Unit
    ){
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = { onClicked(placeinfo)}
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
        ){
            ImageIcon(imageicon = placeinfo.image)
            Text(
                text = stringResource(id = placeinfo.name),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}