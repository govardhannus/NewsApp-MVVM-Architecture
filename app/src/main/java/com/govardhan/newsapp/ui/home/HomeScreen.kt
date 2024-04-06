package com.govardhan.newsapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.govardhan.newsapp.ui.base.Route
import com.govardhan.newsapp.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(navController: NavController){

    Scaffold (topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME)})
    }, content = { padding->
        Column (modifier = Modifier.padding(10.dp,100.dp),){
            NewsHomeScreen(navController)
        }
    })
}

@Composable
fun NewsHomeScreen(navController: NavController){
    Column ( modifier = Modifier
        .fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {navController.navigate(Route.TopHeadline.name) }, shape = RectangleShape , modifier = Modifier.size(width = 340.dp, height = 40.dp)){
            Text(text = "Top Headline")
        }
        Button(onClick = {navController.navigate(Route.NewsSource.name)}, shape = RectangleShape, modifier = Modifier.size(width = 340.dp, height = 40.dp)){
            Text(text = "News Sources")
        }
        Button(onClick = {}, shape = RectangleShape, modifier = Modifier.size(width = 340.dp, height = 40.dp)){
            Text(text = "Countries")
        }
        Button(onClick = {}, shape = RectangleShape, modifier = Modifier.size(width = 340.dp, height = 40.dp)){
            Text(text = "Languages")
        }
        Button(onClick = {}, shape = RectangleShape, modifier = Modifier.size(width = 340.dp, height = 40.dp)){
            Text(text = "Search")
        }
    }
}
