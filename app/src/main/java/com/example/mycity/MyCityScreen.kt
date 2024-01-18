package com.example.mycity

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.mycity.data.DataSource
import com.example.mycity.data.PlaceInfo
import com.example.mycity.ui.InfoScreen
import com.example.mycity.ui.MyCityViewModel
import com.example.mycity.ui.StartScreen
import com.example.mycity.ui.theme.CategoryScreen



enum class MyCityScreen(@StringRes val title: Int) {
    Start(title = R.string.Place),
    Category(title = R.string.PlaceInfo),
    Info(title = R.string.Info)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(

){
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val currentScreen = backStackEntry?.destination?.route ?: MyCityScreen.Start.name

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
            startDestination = MyCityScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(MyCityScreen.Start.name){
                StartScreen(
                    places = DataSource.Placelist,
                    modifier = Modifier.fillMaxSize(),
                    onClicked = {
                        viewModel.updateCategory(it)
                        navController.navigate(MyCityScreen.Category.name)
                    }
                )
            }
            composable(MyCityScreen.Category.name){
                val categoryInfo = when (uiState.place){
                    DataSource.Placelist[0] -> DataSource.Cafe
                    DataSource.Placelist[1] -> DataSource.Mall
                    DataSource.Placelist[2] -> DataSource.Library
                    else -> DataSource.Cafe
                }
                CategoryScreen(
                    placeInfo = categoryInfo,
                    onClicked = {
                        viewModel.updateCategory(it)
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
                val placeInfo: PlaceInfo = categoryInfo.find { it = uiState.placeinfo } ?: DataSource.Cafe[0]
                InfoScreen(placeinfo = placeInfo)
            }
        }
    }
}

