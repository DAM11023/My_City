package com.example.mycity

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.data.DataSource
import com.example.mycity.data.PlaceInfo
import com.example.mycity.ui.InfoScreen
import com.example.mycity.ui.MyCityViewModel
import com.example.mycity.ui.StartScreen
import com.example.mycity.ui.theme.CategoryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(){
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val currentScreen = backStackEntry?.destination?.route ?: MyCityScreen.Place.name

    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){  innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Place.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(MyCityScreen.Place.name){
                StartScreen(
                    places = DataSource.Placelist,
                    onClicked = {
                        viewModel.updatePlace(it)
                        navController.navigate(MyCityScreen.Place.name)
                    }
                )
            }
            composable(MyCityScreen.PlaceInfo.name){
                val categoryInfo = when (uiState.place){
                    DataSource.Placelist[0] -> DataSource.Cafe
                    DataSource.Placelist[1] -> DataSource.Mall
                    DataSource.Placelist[2] -> DataSource.Library
                    else -> DataSource.Cafe
                }
                CategoryScreen(
                    placeInfo = categoryInfo,
                    onClicked = {
                        viewModel.updatePlaceInfo(it)
                        navController.navigate(MyCityScreen.Info.name)
                    }
                )
            }
            composable(MyCityScreen.Info.name){
                val categoryInfo = when (uiState.place){
                    DataSource.Placelist[0] -> DataSource.Cafe
                    DataSource.Placelist[1] -> DataSource.Mall
                    DataSource.Placelist[2] -> DataSource.Library
                    else -> DataSource.Cafe
                }
                val placeInfo: PlaceInfo = categoryInfo.find { it ==  uiState.placeinfo } ?: DataSource.Cafe[0]
                InfoScreen(placeinfo = placeInfo)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {Text(currentScreen)},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp){
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}
enum class MyCityScreen(@StringRes val title: Int){
    Place(title = R.string.app_name),
    PlaceInfo(title = R.string.PlaceInfo),
    Info(title = R.string.Info)
}


