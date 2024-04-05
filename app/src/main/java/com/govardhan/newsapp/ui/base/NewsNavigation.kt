package com.govardhan.newsapp.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.govardhan.newsapp.ui.home.HomeRoute

sealed class Route(val name:String){
    object Home: Route("Home")
}
@Composable
fun NewsNavHost(){
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Route.Home.name){
        composable(route = Route.Home.name){
            HomeRoute()
        }
    }
}